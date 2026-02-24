package com.gestionDeportiva.test;

import java.util.*;

import com.gestionDeportiva.modulos.deportes.modelo.Deporte;
import com.gestionDeportiva.modulos.deportes.modelo.GestorDeportes;
import com.gestionDeportiva.modulos.notificaciones.modelo.MedioContacto;
import com.gestionDeportiva.modulos.partidos.modelo.Partido;
import com.gestionDeportiva.modulos.usuarios.BusquedaPartidos;
import com.gestionDeportiva.modulos.usuarios.interfaces.IStrategyEmparejamiento;
import com.gestionDeportiva.modulos.usuarios.modelo.Administrador;
import com.gestionDeportiva.modulos.usuarios.modelo.Jugador;
import com.gestionDeportiva.modulos.usuarios.modelo.Usuario;
import com.gestionDeportiva.modulos.usuarios.strategies.Avanzado;
import com.gestionDeportiva.modulos.usuarios.strategies.EmparejamientoPorCercania;
import com.gestionDeportiva.modulos.usuarios.strategies.EmparejamientoPorHistorial;
import com.gestionDeportiva.modulos.usuarios.strategies.EmparejamientoPorNivel;
import com.gestionDeportiva.modulos.usuarios.strategies.Principiante;

import java.time.LocalDateTime;

public class TestApp {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Partido> partidosGlobales = new ArrayList<>();
    private static GestorDeportes gestorDeportes = new GestorDeportes();

    public static void main(String[] args) {
        // Inicialización de datos
        gestorDeportes.crearNuevoDeporte("FUTBOL");
        gestorDeportes.crearNuevoDeporte("TENIS");

        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n========================================");
            System.out.println("   SISTEMA DE GESTIÓN DEPORTIVA");
            System.out.println("========================================");
            System.out.println("1. Registrar JUGADOR (Suscripción automática)");
            System.out.println("2. Registrar ADMINISTRADOR");
            System.out.println("3. [ADMIN] Crear Partido (Notifica seguidores)");
            System.out.println("4. [JUGADOR] Buscar y Confirmar Partido");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion = leerEntero();

            switch (opcion) {
                case 1: registrarJugador(); break;
                case 2: registrarAdmin(); break;
                case 3: crearPartidoFlow(); break;
                case 4: buscarYAnotarseFlow(); break;
                case 0: System.out.println("Cerrando..."); break;
                default: System.out.println("Opción inválida.");
            }
        }
    }

    // 1. REGISTRO DE JUGADOR
    private static void registrarJugador() {
        System.out.print("Nombre: "); String nom = scanner.nextLine();
        System.out.print("Email: "); String email = scanner.nextLine();
        
        System.out.println("Elija Deporte Favorito:");
        List<Deporte> deps = gestorDeportes.getDeportes();
        for (int i = 0; i < deps.size(); i++) System.out.println(i + ". " + deps.get(i).getNombre());
        
        int sel = leerEntero();
        Deporte fav = deps.get(sel);

        // El constructor de Jugador ya tiene la lógica de activar el Observer
        Jugador j = new Jugador(nom, email, "123", "San Isidro", fav, new Principiante());
        j.agregarMedio(new MedioContacto("EMAIL", email));
        
        usuarios.add(j);
        System.out.println("✓ Jugador registrado. Recibirás avisos de " + fav.getNombre());
    }

    // 2. REGISTRO DE ADMIN
    private static void registrarAdmin() {
        System.out.print("Nombre Admin: "); String nom = scanner.nextLine();
        System.out.print("Email Admin: "); String email = scanner.nextLine();
        usuarios.add(new Administrador(nom, email, "admin123"));
        System.out.println("✓ Administrador registrado.");
    }

    // 3. CREAR PARTIDO (Dispara Notificación de Creación)
    private static void crearPartidoFlow() {
        Administrador admin = (Administrador) usuarios.stream().filter(u -> u instanceof Administrador).findFirst().orElse(null);
        if (admin == null) {
            System.out.println("X Error: Primero registre un Admin.");
            return;
        }

        System.out.println("Deporte (0: FUTBOL, 1: TENIS):");
        int sel = leerEntero();
        Deporte dep = gestorDeportes.getDeportes().get(sel);

        Partido p = new Partido(dep, 60, "San Isidro", LocalDateTime.now().plusDays(2), new Principiante(), new Avanzado(), admin);
        
        // Esta llamada dispara el mail a los seguidores del deporte
        admin.crearPartido(p);
        partidosGlobales.add(p);
        System.out.println("✓ Partido creado y notificado.");
    }

    // 4. BUSCAR Y CONFIRMAR (Dispara Notificación de Cambio de Estado)
    private static void buscarYAnotarseFlow() {
        System.out.print("Email del jugador: "); String email = scanner.nextLine();
        Jugador j = (Jugador) usuarios.stream().filter(u -> u instanceof Jugador && u.getEmail().equals(email)).findFirst().orElse(null);

        if (j == null ) {
            System.out.println("X No se encontró el jugador.");
            return;
        }

        if (partidosGlobales.isEmpty()) {
            System.out.println("No hay partidos creados en el sistema");
            return;
        }

        // --- NUEVA LÓGICA DE BÚSQUEDA CON STRATEGY ---
        System.out.println("\n¿Cómo desea buscar su partido?");
        System.out.println("1. Por Cercanía (Misma zona)");
        System.out.println("2. Por Nivel (Rango adecuado para usted)");
        System.out.println("3. Por Historial (Recomendar lo que ya jugó)");
        int tipoBusqueda = leerEntero();

        IStrategyEmparejamiento estrategia;
        switch (tipoBusqueda) {
            case 1: estrategia = new EmparejamientoPorCercania(); break;
            case 2: estrategia = new EmparejamientoPorNivel(); break;
            case 3: estrategia = new EmparejamientoPorHistorial(); break;
            default: 
                System.out.println("Opción inválida. Usando búsqueda por Cercanía por defecto.");
                estrategia = new EmparejamientoPorCercania();
        }

        BusquedaPartidos buscador = new BusquedaPartidos(estrategia);
        List<Partido> filtrados = buscador.buscarPartido(j, partidosGlobales);

        if (filtrados.isEmpty()) {
            System.out.println("No se encontraron partidos que coincidan con su criterio.");
            return;
        }

        /*for (int i = 0; i < partidosGlobales.size(); i++) {
            System.out.println(i + ". " + partidosGlobales.get(i).getDeporte().getNombre() + " en " + partidosGlobales.get(i).getZona());
        }
        
        int sel = leerEntero();
        Partido p = partidosGlobales.get(sel);*/
        

        // --- MOSTRAR RESULTADOS FILTRADOS ---
        System.out.println("\nPartidos Encontrados:");
        for (int i = 0; i < filtrados.size(); i++) {
            System.out.println(i + ". " + filtrados.get(i).getDeporte().getNombre() + 
                            " | Zona: " + filtrados.get(i).getZona() + 
                            " | Niveles: " + filtrados.get(i).getNvlMin().getValorNivel() + 
                            "-" + filtrados.get(i).getNvlMax().getValorNivel());
        }

        System.out.print("Elija el número de partido para anotarse (o -1 para cancelar): ");
        int sel = leerEntero();
        if (sel >= 0 && sel < filtrados.size()) {
            Partido p = filtrados.get(sel);
            j.seleccionarPartido(p);
            
            System.out.print("¿Desea confirmar asistencia ahora? (S/N): ");
            if (scanner.nextLine().equalsIgnoreCase("S")) {
                j.confirmarAsistencia(p); 
            }
        }
        
    }

    private static int leerEntero() {
        try { return Integer.parseInt(scanner.nextLine()); } catch (Exception e) { return -1; }
    }
}
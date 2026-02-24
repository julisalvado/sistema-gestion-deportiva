package com.gestionDeportiva.test;

import java.time.LocalDateTime;

import com.gestionDeportiva.modulos.deportes.modelo.Deporte;
import com.gestionDeportiva.modulos.deportes.modelo.GestorDeportes;
import com.gestionDeportiva.modulos.notificaciones.modelo.MedioContacto;
import com.gestionDeportiva.modulos.partidos.modelo.Partido;
import com.gestionDeportiva.modulos.usuarios.modelo.Administrador;
import com.gestionDeportiva.modulos.usuarios.modelo.Jugador;
import com.gestionDeportiva.modulos.usuarios.strategies.Avanzado;
import com.gestionDeportiva.modulos.usuarios.strategies.Principiante;

public class TestCambioEstado {
    public static void main(String[] args) {
        // 1. Setup inicial
        GestorDeportes gestor = new GestorDeportes();
        
        // Creamos los objetos de Deporte y Niveles primero
        
        Deporte tenis = gestor.crearNuevoDeporte("TENIS");
        
        Principiante principiante = new Principiante();
        Avanzado avanzado = new Avanzado();

        Administrador admin = new Administrador("Admin Juan", "admin@test.com", "123");

        // 2. Creamos dos jugadores
        // Corregido: Pasamos el objeto 'tenis' y el objeto 'avanzado'
        Jugador j1 = new Jugador("Julie", "julie@test.com", "123", "Vicente Lopez", tenis, avanzado);
        j1.agregarMedio(new MedioContacto("EMAIL", "julietasalvad@gmail.com"));

        // Corregido: Marcos también necesita objetos reales, no Strings
        Jugador j2 = new Jugador("Marcos", "marcos@test.com", "123", "Vicente Lopez", tenis, avanzado);
        j2.agregarMedio(new MedioContacto("EMAIL", "OTRO_CORREO@gmail.com"));

        // 3. Creamos el partido
        // IMPORTANTE: Si es un partido de FUTBOL, pero tus jugadores favoritos son de TENIS,
        // no recibirán la notificación inicial de creación, pero SÍ la de cambio de estado
        // porque están metidos dentro de la lista 'jugadores' del partido.
        Partido partido = new Partido(
            tenis, 60, "Cancha 5", 
            LocalDateTime.now().plusDays(2), 
            principiante, avanzado, 
            admin
        );

        admin.crearPartido(partido);

        System.out.println("\n--- PASO 1: Agregando jugadores al partido ---");
        j1.seleccionarPartido(partido);
        j2.seleccionarPartido(partido);

        System.out.println("\n--- PASO 2: Primera Confirmación ---");
        j1.confirmarAsistencia(partido);

        System.out.println("\n--- PASO 3: Segunda Confirmación (Cambio de Estado) ---");
        // Al confirmar el último necesario, se dispara el cambiarEstado en Partido.java
        j2.confirmarAsistencia(partido);

        System.out.println("\n--- PRUEBA FINALIZADA ---");
    }
}
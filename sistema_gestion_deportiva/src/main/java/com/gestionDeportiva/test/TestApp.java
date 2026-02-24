package com.gestionDeportiva.test;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDateTime;

import com.gestionDeportiva.modulos.deportes.modelo.Deporte;
import com.gestionDeportiva.modulos.deportes.modelo.GestorDeportes;
import com.gestionDeportiva.modulos.notificaciones.modelo.MedioContacto;
import com.gestionDeportiva.modulos.partidos.partidoEstados.EstadoFinalizado;
import com.gestionDeportiva.modulos.usuarios.interfaces.IStrategyNivelJuego;
import com.gestionDeportiva.modulos.partidos.modelo.BusquedaPartidos;
import com.gestionDeportiva.modulos.partidos.modelo.Partido;
import com.gestionDeportiva.modulos.partidos.modelo.Estadistica;
import com.gestionDeportiva.modulos.partidos.strategies.*;
import com.gestionDeportiva.modulos.usuarios.modelo.*;
import com.gestionDeportiva.modulos.usuarios.strategies.*;

public class TestApp {

    private static Scanner scanner = new Scanner(System.in);
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Partido> partidosGlobales = new ArrayList<>();
    private static GestorDeportes gestorDeportes = new GestorDeportes();

    public static void main(String[] args) {

        gestorDeportes.crearNuevoDeporte("FUTBOL");
        gestorDeportes.crearNuevoDeporte("TENIS");

        cargarDatosIniciales(); //para pruebas futuras, ya que el sistema no tiene persistencia

        int opcion = -1;

        while (opcion != 0) {

            System.out.println("\n=========== SISTEMA ==========");
            System.out.println("1. Registrar JUGADOR");
            System.out.println("2. Registrar ADMINISTRADOR");
            System.out.println("3. [ADMIN] Crear Partido");
            System.out.println("4. [JUGADOR] Buscar Partido");
            System.out.println("5. [ADMIN] Ver mis partidos");
            System.out.println("6. [JUGADOR] Mi perfil");
            System.out.println("0. Salir");

            opcion = leerEntero();

            switch (opcion) {
                case 1 -> registrarJugador();
                case 2 -> registrarAdmin();
                case 3 -> crearPartidoFlow();
                case 4 -> buscarYAnotarseFlow();
                case 5 -> verPartidosAdminFlow();
                case 6 -> menuJugador();
                case 0 -> System.out.println("Hasta luego.");
                default -> System.out.println("Opción inválida.");
            }
        }
    }


    // REGISTRO JUGADOR

    private static void registrarJugador() {

        System.out.print("Nombre: "); String nom = scanner.nextLine();
        System.out.print("Email: "); String email = scanner.nextLine();
        System.out.print("Contraseña: "); String pass = scanner.nextLine();
        System.out.print("Zona: "); String zona = scanner.nextLine();

        if (zona.isEmpty()) {
            System.out.println("La zona no puede estar vacía.");
            return;
        }

        if (gestorDeportes.getDeportes().isEmpty()) {
            System.out.println("No hay deportes disponibles. Contacte a un administrador.");
            return;
        }

        System.out.println("Elija Deporte Favorito:");
        List<Deporte> deps = gestorDeportes.getDeportes();
        for (int i = 0; i < deps.size(); i++)
            System.out.println(i + ". " + deps.get(i).getNombre());

        Deporte fav = deps.get(leerEntero());

        System.out.println("Seleccione su nivel:");
        System.out.println("\n0. Principiante \n1. Intermedio\n2. Avanzado\n");

        IStrategyNivelJuego nivel = obtenerNivel(leerEntero());

        Jugador j = new Jugador(nom, email, pass, zona, fav, nivel);
        j.agregarMedio(new MedioContacto("EMAIL", email));

        usuarios.add(j);

        System.out.println("Jugador registrado.");
    }


    // REGISTRO ADMIN

    private static void registrarAdmin() {

        System.out.print("Nombre Admin: "); String nom = scanner.nextLine();
        System.out.print("Email Admin: "); String email = scanner.nextLine();

        usuarios.add(new Administrador(nom, email, "admin123"));
        System.out.println("Administrador registrado.");
    }


    // CREAR PARTIDO

    private static void crearPartidoFlow() {

        System.out.print("Email administrador: ");
        String email = scanner.nextLine();


        Administrador admin = (Administrador) usuarios.stream()
                .filter(u -> u instanceof Administrador && u.getEmail().equals(email))
                .findFirst().orElse(null);
                
        if (admin == null) {
            System.out.println("Debe existir un administrador.");
            return;
        }

        List<Deporte> deps = gestorDeportes.getDeportes();

        if (deps.isEmpty()) {
            System.out.println("No hay deportes cargados.");
            return;
        }

        System.out.println("Seleccione deporte:");
        for (int i = 0; i < deps.size(); i++) {
            System.out.println(i + ". " + deps.get(i).getNombre());
        }

        int depIndex = leerEntero();
        if (depIndex < 0 || depIndex >= deps.size()) {
            System.out.println("Deporte inválido.");
            return;
        }

        Deporte dep = deps.get(depIndex);

        // -------- ZONA --------
        String zona;
        do {
            System.out.print("Zona: ");
            zona = scanner.nextLine().trim();
            if (zona.isEmpty()) {
                System.out.println("La zona no puede estar vacía.");
            }
        } while (zona.isEmpty());

        // -------- DURACIÓN --------
        int duracion;
        do {
            System.out.print("Duración en minutos: ");
            duracion = leerEntero();
            if (duracion <= 0) {
                System.out.println("La duración debe ser mayor a 0.");
            }
        } while (duracion <= 0);

        LocalDateTime fechaHora = null;

        while (fechaHora == null) {
            try {
                System.out.print("Fecha (dd/MM/yyyy): ");
                String fechaStr = scanner.nextLine().trim();

                System.out.print("Hora (HH:mm): ");
                String horaStr = scanner.nextLine().trim();

                DateTimeFormatter formatter = DateTimeFormatter
                        .ofPattern("dd/MM/uuuu HH:mm")
                        .withResolverStyle(java.time.format.ResolverStyle.STRICT);

                fechaHora = LocalDateTime.parse(fechaStr + " " + horaStr, formatter);

                if (!fechaHora.isAfter(LocalDateTime.now())) {
                    System.out.println("La fecha debe ser futura.");
                    fechaHora = null;
                }

            } catch (Exception e) {
                System.out.println("Fecha u hora inválida. Intente nuevamente. (Formato: 16/05/2026 y 16:00)");
            }
        }

        // -------- NIVELES --------
        System.out.println("Nivel mínimo: \n0. Principiante \n1. Intermedio\n2. Avanzado\n");
        int minSel = leerEntero();
        if (minSel < 0 || minSel > 2) {
            System.out.println("Nivel mínimo inválido.");
            return;
        }

        System.out.println("Nivel máximo: \n0. Principiante \n1. Intermedio\n2. Avanzado\n");
        int maxSel = leerEntero();
        if (maxSel < 0 || maxSel > 2) {
            System.out.println("Nivel mínimo inválido.");
            return;
        }

        IStrategyNivelJuego min = obtenerNivel(minSel);
        IStrategyNivelJuego max = obtenerNivel(maxSel);

        if (min.getValorNivel() > max.getValorNivel()) {
            System.out.println("El nivel mínimo no puede ser mayor al máximo.");
            return;
        }

        try {
            Partido p = new Partido(dep, duracion, zona, fechaHora, min, max, admin);

            admin.crearPartido(p);
            partidosGlobales.add(p);

            System.out.println("Partido creado correctamente.");

        } catch (Exception e) {
            System.out.println("No se pudo crear el partido: " + e.getMessage());
        }
    }

    // MENU JUGADOR

    private static void menuJugador() {

        System.out.print("Email jugador: ");
        String email = scanner.nextLine();

        Jugador j = (Jugador) usuarios.stream()
                .filter(u -> u instanceof Jugador && u.getEmail().equals(email))
                .findFirst().orElse(null);

        if (j == null) {
            System.out.println("\nLo sentimos, el jugador no pudo ser encontrado.\n");
            return;
        }

        int op = -1;

        while (op != 0) {

            System.out.println("\n===== MI PERFIL =====");
            System.out.println("1. Ver mi información");
            System.out.println("2. Cambiar nivel de juego");
            System.out.println("3. Ver historial");
            System.out.println("0. Volver");

            op = leerEntero();

            switch (op) {

                case 1 -> System.out.println(j.getInformacion());

                case 2 -> {
                    System.out.println("Nuevo nivel \n0. Principiante \n1. Intermedio\n2. Avanzado\n");
                    j.cambiarNivelJuego(obtenerNivel(leerEntero()));
                    System.out.println("Nivel actualizado.");
                }

                case 3 -> verHistorialJugador(j);
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void verHistorialJugador(Jugador j) {

        List<Partido> historial = j.getHistorialPartidos().getPartidos();

        if (historial.isEmpty()) {
            System.out.println("\nNo tiene partidos en historial.\n");
            return;
        }

        for (int i = 0; i < historial.size(); i++) {
            System.out.println("\nÍndice: " + i);
            System.out.println(historial.get(i).getInfo());
        }

        System.out.print("Seleccionar partido (-1 salir): ");
        int sel = leerEntero();

        if (sel < 0 || sel >= historial.size()) return;

        Partido p = historial.get(sel);

        System.out.println("\n----- COMENTARIOS -----");
        p.getComentarios().forEach(c ->
                System.out.println(c.getContenido() + " (por " + c.getAdministrador().getNombre() + " - A las: " + c.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm")) + "\n") );

        System.out.println("\n----- ESTADÍSTICA -----");
        p.getEstadistica().ifPresentOrElse(
                e -> {
                    System.out.println("Amonestaciones: " + e.getAmonestaciones());
                    System.out.println("Expulsiones: " + e.getExpulsiones());
                    System.out.println("MVP: " + (e.getMvp() != null ? e.getMvp().getNombre() : "No definido"));
                },
                () -> System.out.println("Sin estadísticas.")
        );
    }

    // BUSCAR PARTIDO

    private static void buscarYAnotarseFlow() {

        System.out.print("Email jugador: ");
        String email = scanner.nextLine();

        Jugador j = (Jugador) usuarios.stream()
                .filter(u -> u instanceof Jugador && u.getEmail().equals(email))
                .findFirst().orElse(null);

        if (j == null) {
            System.out.println("Jugador no encontrado.");
            return;
        }

        if (partidosGlobales.isEmpty()) {
            System.out.println("No hay partidos.");
            return;
        }

        System.out.println("\nBuscar por: \n1. Cercanía \n2. Nivel \n3. Historial \n4. Ver TODOS los partidos\n");

        int tipo = leerEntero();

        List<Partido> resultados;

        if (tipo == 4) {
            resultados = partidosGlobales;
        } else {
            IStrategyEmparejamiento estrategia = switch (tipo) {
                case 2 -> new EmparejamientoPorNivel();
                case 3 -> new EmparejamientoPorHistorial();
                default -> new EmparejamientoPorCercania();
            };

            BusquedaPartidos buscador = new BusquedaPartidos(estrategia);
            resultados = buscador.buscarPartido(j, partidosGlobales);
        }

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron partidos.");
            return;
        }

        System.out.println("\n===== PARTIDOS DISPONIBLES =====");

        for (int i = 0; i < resultados.size(); i++) {
            System.out.println("\nÍndice: " + i);
            System.out.println(resultados.get(i).getInfo());
        }

        System.out.print("Seleccione partido (-1 para cancelar): ");
        int sel = leerEntero();

        if (sel >= 0 && sel < resultados.size()) {

            Partido p = resultados.get(sel);

            try {
                j.seleccionarPartido(p);
                System.out.println("Jugador anotado correctamente.");

                System.out.print("¿Confirmar asistencia? (S/N): ");
                if (scanner.nextLine().equalsIgnoreCase("S")) {
                    j.confirmarAsistencia(p);
                }

            } catch (Exception e) {
                System.out.println("No se pudo seleccionar el partido: " + e.getMessage());
            }
        }
    }

    // VER PARTIDOS ADMIN

    private static void verPartidosAdminFlow() {

        System.out.print("Email administrador: ");
        String email = scanner.nextLine();

        Administrador admin = obtenerAdmin(email);
        if (admin == null) return;

        List<Partido> misPartidos = partidosGlobales.stream()
                .filter(p -> p.getAdmin().equals(admin))
                .toList();

        if (misPartidos.isEmpty()) {
            System.out.println("No tiene partidos creados.");
            return;
        }

        System.out.println("\n===== MIS PARTIDOS =====");

        for (int i = 0; i < misPartidos.size(); i++) {
            System.out.println("\nÍndice: " + i);
            System.out.println(misPartidos.get(i).getInfo());
        }

        System.out.print("Seleccione partido (-1 para salir): ");
        int sel = leerEntero();

        if (sel < 0 || sel >= misPartidos.size()) return;

        Partido partido = misPartidos.get(sel);

        if (partido.getEstado() instanceof EstadoFinalizado) {

            System.out.println("0. Atrás \n1. Dejar comentario \n2. Dejar / Modificar estadística\n");

            int op = leerEntero();

            try {

                if (op == 1) {
                    System.out.print("Comentario: ");
                    String contenido = scanner.nextLine();
                    admin.dejarComentario(partido, contenido);
                    System.out.println("Comentario agregado correctamente.");
                }

                if (op == 2) {

                    Estadistica estadistica;

                    if (partido.getEstadistica().isPresent()) {
                        estadistica = partido.getEstadistica().get();
                        System.out.println("Editando estadística existente...");
                    } else {
                        estadistica = new Estadistica();
                        System.out.println("Creando nueva estadística...");
                    }

                    boolean salirEstadistica = false;
                    boolean huboCambios = false;

                    while (!salirEstadistica) {

                        System.out.println("\n--- MENÚ ESTADÍSTICA ---");
                        System.out.println("1. Agregar amonestación");
                        System.out.println("2. Agregar expulsión");
                        System.out.println("3. Definir MVP");
                        System.out.println("0. Guardar y salir");

                        int opcionEst = leerEntero();

                        switch (opcionEst) {

                            case 1 -> {
                                estadistica.agregarAmonestacion();
                                huboCambios = true;
                                System.out.println("Amonestación agregada.");
                            }

                            case 2 -> {
                                estadistica.agregarExpulsion();
                                huboCambios = true;
                                System.out.println("Expulsión agregada.");
                            }

                            case 3 -> {

                                if (partido.getJugadores().isEmpty()) {
                                    System.out.println("No hay jugadores en el partido.");
                                    break;
                                }

                                System.out.println("Jugadores disponibles:");
                                partido.getJugadores()
                                        .forEach(j -> System.out.println("- " + j.getNombre()));

                                System.out.print("Ingrese nombre del MVP: ");
                                String nombreMvp = scanner.nextLine();

                                Optional<Jugador> jugadorOpt = partido.getJugadores()
                                        .stream()
                                        .filter(j -> j.getNombre().equalsIgnoreCase(nombreMvp))
                                        .findFirst();

                                if (jugadorOpt.isPresent()) {
                                    estadistica.definirMVP(jugadorOpt.get());
                                    huboCambios = true;
                                    System.out.println("MVP definido correctamente.");
                                } else {
                                    System.out.println("El jugador no existe en este partido.");
                                }
                            }

                            case 0 -> salirEstadistica = true;

                            default -> System.out.println("Opción inválida.");
                        }
                    }

                    if (huboCambios) {
                        admin.registrarEstadistica(partido, estadistica);
                        System.out.println("Estadística guardada correctamente.");
                    } else {
                        System.out.println("No se realizaron cambios en la estadística.");
                    }
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        }
        else if (!(partido.getEstado() instanceof com.gestionDeportiva.modulos.partidos.partidoEstados.EstadoEnJuego)
                && !(partido.getEstado() instanceof com.gestionDeportiva.modulos.partidos.partidoEstados.EstadoCancelado)) {

            System.out.println("0. Atrás");
            System.out.println("1. Cancelar partido");

            if (leerEntero() == 1) {
                try {
                    partido.cancelarPorAdmin(admin);
                    System.out.println("Partido cancelado.");
                } catch (Exception e) {
                    System.out.println("No se pudo cancelar: " + e.getMessage());
                }
            }
        }
        else {
            System.out.println("No hay acciones disponibles para este estado.");
        }
    }


    // DATOS INICIALES

    private static void cargarDatosIniciales() {

        Administrador admin = new Administrador("AdminDemo", "admin@demo.com", "123");
        usuarios.add(admin);

        Deporte futbol = gestorDeportes.getDeportes().get(0);

        Jugador jugadorDemo = new Jugador(
                "Juan",
                "juan@mail.com",
                "123",
                "Palermo",
                futbol,
                new Intermedio()
        );

        usuarios.add(jugadorDemo);

        // Partido 1 finalizado
        Partido p1 = new Partido(
                futbol,
                LocalDateTime.now().minusDays(5),
                90,
                "Palermo",
                new Principiante(),
                new Avanzado(),
                admin,
                new ArrayList<>(),
                new HashSet<>(),
                new EstadoFinalizado()
        );

        p1.agregarComentario(admin, "Gran partido.");
        Estadistica est1 = new Estadistica();
        est1.agregarAmonestacion();
        p1.registrarEstadistica(admin, est1);

        jugadorDemo.agregarAlHistorial(p1);
        partidosGlobales.add(p1);

        // Partido 2 finalizado
        Partido p2 = new Partido(
                futbol,
                LocalDateTime.now().minusDays(2),
                60,
                "San Isidro",
                new Intermedio(),
                new Avanzado(),
                admin,
                new ArrayList<>(),
                new HashSet<>(),
                new EstadoFinalizado()
        );

        p2.agregarComentario(admin, "Partido muy parejo.");
        Estadistica est2 = new Estadistica();
        est2.agregarAmonestacion();
        est2.agregarAmonestacion();
        p2.registrarEstadistica(admin, est2);

        jugadorDemo.agregarAlHistorial(p2);
        partidosGlobales.add(p2);
        System.out.println("=== USUARIOS CARGADOS ===");
        usuarios.forEach(u -> System.out.println(u.getEmail()));
        System.out.println("=========================");
    }

    // HELPERS

    private static Administrador obtenerAdmin(String email) {
        return (Administrador) usuarios.stream()
                .filter(u -> u instanceof Administrador && u.getEmail().equals(email))
                .findFirst().orElse(null);
    }
   

    private static IStrategyNivelJuego obtenerNivel(int sel) {
        return switch (sel) {
            case 2 -> new Avanzado();
            case 1 -> new Intermedio();
            default -> new Principiante();
        };
    }

    private static int leerEntero() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            return -1;
        }
    }
}
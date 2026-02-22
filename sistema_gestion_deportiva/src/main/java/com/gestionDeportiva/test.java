package com.gestionDeportiva;
import java.sql.*;

import com.gestionDeportiva.modulos.notificaciones.ConexionDB;
import com.gestionDeportiva.modulos.notificaciones.dao.JugadorDAO;
import com.gestionDeportiva.modulos.notificaciones.facade.FacadeNotificador;

public class test {
    public static void main(String[] args) {
        crearTablasSiNoExisten();
        JugadorDAO dao = new JugadorDAO();
        FacadeNotificador facade = new FacadeNotificador();

        // 1. REGISTRO (Usamos cualquier texto como token)
        Jugador messi = new Jugador("Lionel", "leo@afa.com", "123");
        String tokenPrueba = "token_simulado_12345"; 

        dao.guardarJugadorCompleto(messi, tokenPrueba);
        
        // 2. RECUPERACIÓN (Para demostrar que el DAO funciona)
        Jugador jugadorEnDB = dao.obtenerJugadorCompleto(messi.getIdUsuario());

        // 3. NOTIFICACIÓN
        System.out.println("\n=== INICIANDO NOTIFICACIÓN ESTRATÉGICA ===");
        facade.notificar(jugadorEnDB, "¡Tu partido empieza en 15 minutos!");
    }

    // El método que ya tenías para crear las tablas
    public static void crearTablasSiNoExisten() {
        // ... aquí va el código que te pasé antes ...
        String sqlUsuarios = "CREATE TABLE IF NOT EXISTS usuarios (" +
                            "id_usuario INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "username TEXT NOT NULL, " +
                            "email TEXT NOT NULL, " +
                            "password TEXT NOT NULL);";

        String sqlJugadores = "CREATE TABLE IF NOT EXISTS jugadores (" +
                            "id_usuario INTEGER PRIMARY KEY, " +
                            "zona TEXT, " +
                            "deporte_favorito TEXT, " +
                            "FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE);";

        String sqlMedios = "CREATE TABLE IF NOT EXISTS medios_contacto (" +
                        "id_contacto INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "id_usuario INTEGER, " +
                        "tipo_medio TEXT, " +
                        "valor TEXT, " +
                        "FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE);";

        try (Connection conn = ConexionDB.getConexion(); 
            Statement stmt = conn.createStatement()) {
            
            stmt.execute(sqlUsuarios);
            stmt.execute(sqlJugadores);
            stmt.execute(sqlMedios);
            System.out.println("Tablas creadas o verificadas con éxito.");
            
        } catch (Exception e) {
            System.err.println("Error al crear tablas: " + e.getMessage());
        }
    }
}

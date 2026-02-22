package com.gestionDeportiva.modulos.notificaciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionDB {
    private static final String URL = "jdbc:sqlite:gestion_deportiva.db";

    public static Connection getConexion() throws SQLException {
        Connection conn = DriverManager.getConnection(URL);
        
        // IMPORTANTE: SQLite tiene las llaves foráneas apagadas por defecto. 
        // Las activamos cada vez que abrimos una conexión.
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("PRAGMA foreign_keys = ON;");
        }
        
        return conn;
    }

    public void crearTablas() {
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

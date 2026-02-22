package com.gestionDeportiva.modulos.notificaciones;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.gestionDeportiva.modulos.Jugador;

public class JugadorDAO {

    // 1. Instanciamos el DAO especializado
    private MedioContactoDAO medioDAO = new MedioContactoDAO();

    public Jugador obtenerJugadorCompleto(int idUsuario) {
        Jugador j = null;
        String sql = "SELECT u.username, u.email, u.password, j.zona FROM usuarios u " +
                    "JOIN jugadores j ON u.id_usuario = j.id_usuario WHERE u.id_usuario = ?";

        try (Connection conn = ConexionDB.getConexion(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                j = new Jugador(rs.getString("username"), rs.getString("email"), rs.getString("password"));
                j.setIdUsuario(idUsuario);
                j.setZona(rs.getString("zona"));
                
                // 2. EN LUGAR DE HACER EL SELECT AQUÍ, USAMOS EL OTRO DAO
                // Le pedimos al especialista que nos dé la lista de medios
                List<MedioContacto> medios = medioDAO.obtenerPorUsuario(idUsuario);
                
                // 3. Se los asignamos al jugador (que es un Usuario)
                j.setMediosContacto(medios);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return j;
    }

    public void guardarJugadorCompleto(Jugador jugador, String tokenFirebase) {
        // Definimos las consultas
        String sqlUsuario = "INSERT INTO usuarios (username, email, password) VALUES (?, ?, ?)";
        String sqlJugador = "INSERT INTO jugadores (id_usuario, zona) VALUES (?, ?)";
        String sqlMedio = "INSERT INTO medios_contacto (id_usuario, tipo_medio, valor) VALUES (?, 'FIREBASE', ?)";

        Connection conn = null;
        try {
            conn = ConexionDB.getConexion();
            conn.setAutoCommit(false); // Iniciamos la transacción

            // 1. Insertar en la tabla USUARIOS y obtener el ID generado
            int idGenerado = 0;
            try (PreparedStatement pstmtUser = conn.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS)) {
                pstmtUser.setString(1, jugador.getNombre());
                pstmtUser.setString(2, jugador.getEmail());
                pstmtUser.setString(3, jugador.getContrasenia());
                pstmtUser.executeUpdate();

                ResultSet rs = pstmtUser.getGeneratedKeys();
                if (rs.next()) {
                    idGenerado = rs.getInt(1);
                    jugador.setIdUsuario(idGenerado); // Actualizamos el objeto en memoria
                }
            }

            // 2. Insertar en la tabla JUGADORES usando el ID obtenido
            try (PreparedStatement pstmtJugador = conn.prepareStatement(sqlJugador)) {
                pstmtJugador.setInt(1, idGenerado);
                pstmtJugador.setString(2, jugador.getZona());
                pstmtJugador.executeUpdate();
            }

            // 3. Insertar el Token en MEDIOS_CONTACTO (Automatización)
            try (PreparedStatement pstmtMedio = conn.prepareStatement(sqlMedio)) {
                pstmtMedio.setInt(1, idGenerado);
                pstmtMedio.setString(2, tokenFirebase);
                pstmtMedio.executeUpdate();
            }

            conn.commit(); // Si todo salió bien, guardamos los cambios permanentemente
            System.out.println("LOG: Jugador y Token guardados correctamente. ID: " + idGenerado);

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Si algo falló, deshacemos todo para no dejar basura
                    System.err.println("LOG: Error detectado. Se realizó un Rollback.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }

}

package com.gestionDeportiva.modulos.notificaciones;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedioContactoDAO {
    public List<MedioContacto> obtenerPorUsuario(int idUsuario) {
        List<MedioContacto> medios = new ArrayList<>();
        String sql = "SELECT tipo_medio, valor FROM medios_contacto WHERE id_usuario = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                medios.add(new MedioContacto(rs.getString("tipo_medio"), rs.getString("valor")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medios;
    }
}

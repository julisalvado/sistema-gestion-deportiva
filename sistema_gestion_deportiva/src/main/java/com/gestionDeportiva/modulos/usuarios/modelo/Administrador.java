package com.gestionDeportiva.modulos.usuarios.modelo;

import com.gestionDeportiva.modulos.partidos.modelo.Partido;

public class Administrador extends Usuario{
    public Administrador(String nombre, String email, String contrasenia) {
        super(nombre, email, contrasenia);
    }

    public Partido crearPartido(Partido partido) {
        if (partido == null){
            throw new IllegalArgumentException("El partido no puede ser nulo");
        }
        
        String mensaje = "NUEVO PARTIDO DISPONIBLE\n" + partido.getInfo();
    
        partido.getDeporte().notificar(mensaje);

        return partido;

        
    }
}

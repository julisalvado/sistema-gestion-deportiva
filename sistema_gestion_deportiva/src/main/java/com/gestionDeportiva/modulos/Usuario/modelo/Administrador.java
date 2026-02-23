package com.gestionDeportiva.modulos.Usuario.modelo;

import com.gestionDeportiva.Partido;

public class Administrador extends Usuario{
    public Administrador(String nombre, String email, String contrasenia) {
        super(nombre, email, contrasenia);
    }

    public Partido crearPartido(Partido partido) {
        if (partido == null){
            throw new IllegalArgumentException("El partido no puede ser nulo");
        }
        return partido;
    }
}

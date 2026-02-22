package com.gestionDeportiva;

public class Administrador extends Usuario{
    public Administrador(String nombre, String email, String contrasenia) {
        super(nombre, email, contrasenia);
    }

    public Partido CrearPartido(Partido partido) {
        return partido;
    }
}

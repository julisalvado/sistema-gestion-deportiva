package com.gestionDeportiva;

public class Jugador extends Usuario {
    private String zona;
    // Historial, Nivel, etc. (lo que agregarás después)

    public Jugador(String nombre, String email, String contrasenia) {
        super(nombre, email, contrasenia); // Llama al constructor de Usuario
    }

    public void setZona(String zona) { this.zona = zona; }
    public String getZona() { return zona; }

    public String getNombre() {
        return this.nombre;
    }

}

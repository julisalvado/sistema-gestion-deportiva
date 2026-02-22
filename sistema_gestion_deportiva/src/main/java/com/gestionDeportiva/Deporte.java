package com.gestionDeportiva;

public class Deporte {
    private String nombre;
    private int jugadoresNecesarios;

    public Deporte(String nombre, int jugadoresNecesarios) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del deporte no puede ser nulo o vacío.");
        }
        if (jugadoresNecesarios <= 0) {
            throw new IllegalArgumentException("La cantidad de jugadores necesarios debe ser mayor a 0.");
        }
        this.nombre = nombre;
        this.jugadoresNecesarios = jugadoresNecesarios;
    }

    public String getNombre() {
        return nombre;
    }

    public int getJugadoresNecesarios() {
        return jugadoresNecesarios;
    }

}

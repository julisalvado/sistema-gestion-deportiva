package com.gestionDeportiva;

public class Futbol extends Deporte {
    private static final int JUGADORES_POR_PARTIDO = 22;

    public Futbol() {
        super("Futbol", JUGADORES_POR_PARTIDO);
    }
}

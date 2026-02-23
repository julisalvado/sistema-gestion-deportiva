package com.gestionDeportiva;

public class Handball extends Deporte {
    private static final int JUGADORES_POR_PARTIDO = 14;

    public Handball() {
        super("Handball", JUGADORES_POR_PARTIDO);
    }
}

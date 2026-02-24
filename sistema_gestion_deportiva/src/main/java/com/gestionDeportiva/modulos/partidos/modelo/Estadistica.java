package com.gestionDeportiva.modulos.partidos.modelo;

import com.gestionDeportiva.modulos.usuarios.modelo.Jugador;

public class Estadistica {
    private int amonestaciones;
    private int expulsiones;
    private Jugador mvp;


    public void agregarAmonestacion() {
        amonestaciones++;
    }

    public void definirMVP(Jugador jugador) {
        if (jugador == null) {
            throw new IllegalArgumentException("Jugador inválido para MVP.");
        }
        this.mvp = jugador;
    }

    public int getAmonestaciones() {
        return amonestaciones;
    }

    public int getExpulsiones() {
        return expulsiones;
    }

    public Jugador getMvp() {
        return mvp;
    }

    public void agregarExpulsion() {
        expulsiones++;
    }
}

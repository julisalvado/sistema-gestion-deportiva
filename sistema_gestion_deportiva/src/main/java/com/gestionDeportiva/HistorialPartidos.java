package com.gestionDeportiva;

import java.util.List;
import java.util.ArrayList;

public class HistorialPartidos {
    private List<Partido> partidos;

    public HistorialPartidos() {
        this.partidos = new ArrayList<>();
    }

    public void agregarPartido(Partido partido) {
        partidos.add(partido);
    }

    public List<Partido> obtenerPartidos() {
        return new ArrayList<>(partidos);
    }
}

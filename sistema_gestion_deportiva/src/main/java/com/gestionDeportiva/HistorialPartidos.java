package com.gestionDeportiva;

import java.util.List;
import java.util.ArrayList;

public class HistorialPartidos {
    private List<Partido> partidos;

    public HistorialPartidos(List<Partido> partidos) {
        this.partidos = new ArrayList<>(partidos);
    }

    public void agregarPartido(Partido partido) {
        partidos.add(partido);
    }

    public List<Partido> obtenerPartidos() {
        return new ArrayList<>(partidos);
    }
}

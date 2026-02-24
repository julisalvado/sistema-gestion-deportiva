package com.gestionDeportiva.modulos.usuarios.modelo;

import java.util.List;

import com.gestionDeportiva.modulos.partidos.modelo.Partido;

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

    public List<Partido> getPartidos() {
        return partidos;
    }
}

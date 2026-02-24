package com.gestionDeportiva.modulos.usuarios.strategies;

import java.util.ArrayList;
import java.util.List;

import com.gestionDeportiva.modulos.partidos.partidoEstados.EstadoEsperandoJugadores;
import com.gestionDeportiva.modulos.partidos.modelo.Partido;
import com.gestionDeportiva.modulos.usuarios.interfaces.IStrategyEmparejamiento;
import com.gestionDeportiva.modulos.usuarios.modelo.Jugador;

public class EmparejamientoPorCercania implements IStrategyEmparejamiento {

    @Override
    public List<Partido> filtrar(Jugador jugador, List<Partido> partidosDisponibles) {
        if (jugador == null || partidosDisponibles == null) return new ArrayList<>();

        String zonaJugador = jugador.getZona();
        List<Partido> partidosFiltrados = new ArrayList<>();

        for (Partido partido : partidosDisponibles) {
            if (partido.getZona().equalsIgnoreCase(zonaJugador) &&
                partido.getEstado() instanceof EstadoEsperandoJugadores &&
                !partido.estaLleno() &&
                !partido.getJugadores().contains(jugador)) {
                partidosFiltrados.add(partido);
            }
        }

        return partidosFiltrados;
    }
}

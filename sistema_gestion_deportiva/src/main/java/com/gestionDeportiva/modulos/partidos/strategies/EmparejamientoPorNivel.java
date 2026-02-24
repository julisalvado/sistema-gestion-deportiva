package com.gestionDeportiva.modulos.partidos.strategies;

import java.util.ArrayList;
import java.util.List;

import com.gestionDeportiva.modulos.partidos.modelo.Partido;
import com.gestionDeportiva.modulos.partidos.partidoEstados.EstadoEsperandoJugadores;
import com.gestionDeportiva.modulos.usuarios.modelo.Jugador;

public class EmparejamientoPorNivel implements IStrategyEmparejamiento {

    @Override
    public List<Partido> filtrar(Jugador jugador, List<Partido> partidosDisponibles) {
        if (jugador == null || partidosDisponibles == null) return new ArrayList<>();

        int nivelJugador = jugador.getNivelJuego().getValorNivel();

        List<Partido> partidosAptos = new ArrayList<>();

        for (Partido partido : partidosDisponibles) {
            int min = partido.getNvlMin().getValorNivel();
            int max = partido.getNvlMax().getValorNivel();

            if (nivelJugador >= min && nivelJugador <= max &&
                partido.getEstado() instanceof EstadoEsperandoJugadores &&
                !partido.estaLleno() &&
                !partido.getJugadores().contains(jugador)) {
                partidosAptos.add(partido);
            }
        }

        return partidosAptos;
    }
}

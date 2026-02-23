package com.gestionDeportiva.modulos.Usuario.strategies;

import java.util.ArrayList;
import java.util.List;

import com.gestionDeportiva.Partido;
import com.gestionDeportiva.modulos.Usuario.interfaces.IStrategyEmparejamiento;
import com.gestionDeportiva.modulos.Usuario.modelo.Jugador;

public class EmparejamientoPorNivel implements IStrategyEmparejamiento {

    @Override
    public List<Partido> filtrar(Jugador jugador, List<Partido> partidosDisponibles) {
        if (jugador == null || partidosDisponibles == null) return new ArrayList<>();

        int nivelJugador = jugador.getNivelJuego().getValorNivel();

        List<Partido> partidosAptos = new ArrayList<>();

        for (Partido partido : partidosDisponibles) {
            int min = partido.getNvlMin().getValorNivel();
            int max = partido.getNvlMax().getValorNivel();

            if (nivelJugador >= min && nivelJugador <= max) {
                partidosAptos.add(partido);
            }
        }

        return partidosAptos;
    }
}

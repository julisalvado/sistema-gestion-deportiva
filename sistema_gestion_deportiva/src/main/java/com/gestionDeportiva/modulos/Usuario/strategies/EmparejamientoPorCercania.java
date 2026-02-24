package com.gestionDeportiva.modulos.Usuario.strategies;

import java.util.ArrayList;
import java.util.List;

import com.gestionDeportiva.Partido;
import com.gestionDeportiva.modulos.Usuario.interfaces.IStrategyEmparejamiento;
import com.gestionDeportiva.modulos.Usuario.modelo.Jugador;
import com.gestionDeportiva.modulos.notificaciones.partidoEstados.EstadoEsperandoJugadores;

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

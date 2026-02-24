package com.gestionDeportiva.modulos.partidos.strategies;

import java.util.ArrayList;
import java.util.List;

import com.gestionDeportiva.modulos.partidos.partidoEstados.EstadoEsperandoJugadores;
import com.gestionDeportiva.modulos.partidos.modelo.Partido;
import com.gestionDeportiva.modulos.usuarios.modelo.Jugador;

public class EmparejamientoPorHistorial implements IStrategyEmparejamiento {

    @Override
    public List<Partido> filtrar(Jugador jugador, List<Partido> partidosDisponibles) {
        if (jugador == null || partidosDisponibles == null) return new ArrayList<>();

        List<String> nombresDeportesJugados = new ArrayList<>();
        
        for (Partido p : jugador.getHistorialPartidos().obtenerPartidos()) {
            String nombreDep = p.getDeporte().getNombre();
            
            if (!nombresDeportesJugados.contains(nombreDep)) {
                nombresDeportesJugados.add(nombreDep);
            }
        }

        List<Partido> recomendados = new ArrayList<>();
        List<Partido> otros = new ArrayList<>();

        for (Partido partido : partidosDisponibles) {
            if (partido.getEstado() instanceof EstadoEsperandoJugadores &&
                !partido.estaLleno() &&
                !partido.getJugadores().contains(jugador)) {
                
                String nombreDeporteActual = partido.getDeporte().getNombre();
                
                if (nombresDeportesJugados.contains(nombreDeporteActual)) {
                    recomendados.add(partido);
                } else {
                    otros.add(partido);
                }
            }
        }

        recomendados.addAll(otros);
        return recomendados;
    }
}

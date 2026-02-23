package com.gestionDeportiva.modulos.Usuario.strategies;

import java.util.ArrayList;
import java.util.List;

import com.gestionDeportiva.Partido;
import com.gestionDeportiva.modulos.Usuario.interfaces.IStrategyEmparejamiento;
import com.gestionDeportiva.modulos.Usuario.modelo.Jugador;

public class EmparejamientoPorCercania implements IStrategyEmparejamiento {

    @Override
    public List<Partido> filtrar(Jugador jugador, List<Partido> partidosDisponibles) {
        if (jugador == null || partidosDisponibles == null) return new ArrayList<>();

        String zonaJugador = jugador.getZona();
        List<Partido> enMiZona = new ArrayList<>();
        List<Partido> otrasZonas = new ArrayList<>();

        for (Partido partido : partidosDisponibles) {
            if (partido.getZona().equalsIgnoreCase(zonaJugador)) {
                enMiZona.add(partido);
            } else {
                otrasZonas.add(partido);
            }
        }

        enMiZona.addAll(otrasZonas);
        
        return enMiZona;
    }
}

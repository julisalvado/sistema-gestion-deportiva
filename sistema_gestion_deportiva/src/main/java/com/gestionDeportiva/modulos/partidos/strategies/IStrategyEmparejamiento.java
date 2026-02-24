package com.gestionDeportiva.modulos.partidos.strategies;

import java.util.List;

import com.gestionDeportiva.modulos.partidos.modelo.Partido;
import com.gestionDeportiva.modulos.usuarios.modelo.Jugador;

public interface IStrategyEmparejamiento {
    List<Partido> filtrar(Jugador jugador, List<Partido> partidos);
}

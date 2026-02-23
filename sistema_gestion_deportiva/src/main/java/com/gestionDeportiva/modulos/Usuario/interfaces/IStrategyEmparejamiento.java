package com.gestionDeportiva.modulos.Usuario.interfaces;

import java.util.List;

import com.gestionDeportiva.Partido;
import com.gestionDeportiva.modulos.Usuario.modelo.Jugador;

public interface IStrategyEmparejamiento {
    List<Partido> filtrar(Jugador jugador, List<Partido> partidos);
}

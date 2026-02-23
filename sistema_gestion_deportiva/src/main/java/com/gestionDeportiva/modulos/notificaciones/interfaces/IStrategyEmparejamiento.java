package com.gestionDeportiva.modulos.notificaciones.interfaces;

import java.util.List;
import com.gestionDeportiva.Jugador;
import com.gestionDeportiva.Partido;

public interface IStrategyEmparejamiento {
    List<Partido> filtrar(Jugador jugador, List<Partido> partidos);
}

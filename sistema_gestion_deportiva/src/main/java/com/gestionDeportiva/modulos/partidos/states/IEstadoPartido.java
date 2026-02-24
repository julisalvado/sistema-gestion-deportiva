package com.gestionDeportiva.modulos.partidos.states;

import com.gestionDeportiva.modulos.partidos.modelo.Partido;
import com.gestionDeportiva.modulos.usuarios.modelo.Jugador;

public interface IEstadoPartido {
    String nombre();

    void seleccionar(Partido partido, Jugador jugador);

    void confirmarParticipacion(Partido partido, Jugador jugador);

    void cancelarPorAdmin(Partido partido, com.gestionDeportiva.modulos.usuarios.modelo.Administrador administrador);

    void tick(Partido partido, java.time.LocalDateTime ahora);

}

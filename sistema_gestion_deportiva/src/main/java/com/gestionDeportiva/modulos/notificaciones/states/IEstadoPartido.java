package com.gestionDeportiva.modulos.notificaciones.states;

import com.gestionDeportiva.Administrador;
import com.gestionDeportiva.Jugador;
import com.gestionDeportiva.Partido;

import java.time.LocalDateTime;

public interface IEstadoPartido {
    String nombre();

    void seleccionar(Partido partido, Jugador jugador);

    void confirmarParticipacion(Partido partido, Jugador jugador);

    void cancelarPorAdmin(Partido partido, Administrador administrador);

    void tick(Partido partido, LocalDateTime ahora);

}

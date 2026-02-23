package com.gestionDeportiva.modulos.notificaciones.states;

import com.gestionDeportiva.Partido;
import com.gestionDeportiva.modulos.Usuario.modelo.Administrador;
import com.gestionDeportiva.modulos.Usuario.modelo.Jugador;

import java.time.LocalDateTime;

public interface IEstadoPartido {
    String nombre();

    void seleccionar(Partido partido, Jugador jugador);

    void confirmarParticipacion(Partido partido, Jugador jugador);
}

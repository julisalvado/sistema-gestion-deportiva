package com.gestionDeportiva.modulos.notificaciones.states;

import com.gestionDeportiva.Partido;
import com.gestionDeportiva.modulos.Usuario.modelo.Administrador;
import com.gestionDeportiva.modulos.Usuario.modelo.Jugador;

import java.time.LocalDateTime;

public interface IEstadoPartido {
    String nombre();

    void seleccionar(Partido partido, Jugador jugador);

    void confrimar(Partido partido, Jugador jugador);

    void cancelarPorAdmin(Partido partido, Administrador administrador);

    void tick(Partido partido, LocalDateTime ahora);

}

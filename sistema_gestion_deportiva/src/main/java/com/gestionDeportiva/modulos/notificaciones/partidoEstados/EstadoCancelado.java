package com.gestionDeportiva.modulos.notificaciones.partidoEstados;

import com.gestionDeportiva.Administrador;
import com.gestionDeportiva.Jugador;
import com.gestionDeportiva.Partido;
import com.gestionDeportiva.modulos.notificaciones.states.IEstadoPartido;

import java.time.LocalDateTime;

public class EstadoCancelado implements IEstadoPartido {
    @Override
    public String nombre() {
        return "Cancelado.";
    }

    @Override
    public void seleccionar(Partido partido, Jugador jugador) {
        throw new IllegalStateException("No es posible seleciconar el partido, el mismo se encuentra cancelado.");
    }

    @Override
    public void confirmarParticipacion(Partido partido, Jugador jugador) {
        throw new IllegalStateException("No es posible confirmar el partido, el mismo se encuentra cancelado.");
    }

    @Override
    public void cancelarPorAdmin(Partido partido, Administrador administrador) {
        // No es necesario realizar ninguna acción en el metodo cancelarPorAdmin, ya que el partido ya se encuentra cancelado.
    }

    @Override
    public void tick(Partido partido, LocalDateTime ahora) {
        // No es necesario realizar ninguna acción en el metodo tick, ya que el partido ya se encuentra cancelado.
    }
}

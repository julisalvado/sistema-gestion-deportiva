package com.gestionDeportiva.modulos.partidos.partidoEstados;

import java.time.LocalDateTime;

import com.gestionDeportiva.modulos.partidos.modelo.Partido;
import com.gestionDeportiva.modulos.partidos.states.IEstadoPartido;
import com.gestionDeportiva.modulos.usuarios.modelo.Administrador;
import com.gestionDeportiva.modulos.usuarios.modelo.Jugador;

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
        throw new IllegalStateException("No es posible cancelar el partido, el mismo se encuentra cancelado.");
    }

    @Override
    public void tick(Partido partido, LocalDateTime ahora) {
        throw new IllegalStateException("No es posible ejecutar el tick del partido, el mismo se encuentra cancelado.");
    }

}

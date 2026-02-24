package com.gestionDeportiva.modulos.partidos.partidoEstados;

import com.gestionDeportiva.modulos.partidos.modelo.Partido;
import com.gestionDeportiva.modulos.partidos.states.IEstadoPartido;
import com.gestionDeportiva.modulos.usuarios.modelo.Administrador;
import com.gestionDeportiva.modulos.usuarios.modelo.Jugador;

import java.time.LocalDateTime;

public class EstadoConfirmado implements IEstadoPartido {
    @Override
    public String nombre() {
        return "Confirmado.";
    }
    @Override
    public void seleccionar(Partido partido, Jugador jugador) {
        throw new IllegalStateException("No se pueden añadir más jugadores, el mismo se enceuntra completo.");
    }

    @Override
    public void confirmarParticipacion(Partido partido, Jugador jugador) {
        throw new IllegalStateException("El partido ya se ha confirmado.");
    }

    public void cancelarPorAdmin(Partido partido, Administrador administrador) {
        partido.cambiarEstado(new EstadoCancelado());
    }

    public void tick(Partido partido, LocalDateTime ahora) {
        if(!ahora.isBefore(partido.getFechaHoraInicio())){
            partido.cambiarEstado(new EstadoEnJuego());
        }
    }
}

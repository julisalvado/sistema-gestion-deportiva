package com.gestionDeportiva.modulos.notificaciones.partidoEstados;

import com.gestionDeportiva.Administrador;
import com.gestionDeportiva.Jugador;
import com.gestionDeportiva.Partido;
import com.gestionDeportiva.modulos.notificaciones.states.IEstadoPartido;

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
    public void confrimar(Partido partido, Jugador jugador) {
        throw new IllegalStateException("El partido ya se ha confirmado.");
    }

    @Override
    public void cancelarPorAdmin(Partido partido, Administrador administrador) {
        partido.cambiarEstado(new EstadoCancelado());
    }

    @Override
    public void tick(Partido partido, LocalDateTime ahora) {
        if(!ahora.isBefore(partido.getFechaHoraInicio())){
            partido.cambiarEstado(new EstadoEnJuego());
        }
    }
}

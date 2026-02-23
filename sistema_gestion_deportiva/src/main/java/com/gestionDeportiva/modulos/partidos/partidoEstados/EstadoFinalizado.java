package com.gestionDeportiva.modulos.notificaciones.partidoEstados;

import com.gestionDeportiva.Partido;
import com.gestionDeportiva.modulos.Usuario.modelo.Administrador;
import com.gestionDeportiva.modulos.Usuario.modelo.Jugador;
import com.gestionDeportiva.modulos.notificaciones.states.IEstadoPartido;

import java.time.LocalDateTime;

public class EstadoFinalizado implements IEstadoPartido {
    @Override
    public String nombre() {
        return "Finalizado.";
    }

    @Override
    public void seleccionar(Partido partido, Jugador jugador) {
        throw new IllegalStateException("El partido ya finalizó, no es posible agregar más jugadores.");
    }

    @Override
    public void confirmarParticipacion(Partido partido, Jugador jugador) {
        throw new IllegalStateException("El partido ya finalizó, no es posible confirmar la asistencia.");
    }

    public void cancelarPorAdmin(Partido partido, Administrador administrador) {
        throw new IllegalStateException("El partido ya finalizó, no es posible cancelar el mismo.");
    }

}

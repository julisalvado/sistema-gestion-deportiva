package com.gestionDeportiva.modulos.partidos.partidoEstados;

import java.time.LocalDateTime;

import com.gestionDeportiva.modulos.partidos.modelo.Partido;
import com.gestionDeportiva.modulos.partidos.states.IEstadoPartido;
import com.gestionDeportiva.modulos.usuarios.modelo.Administrador;
import com.gestionDeportiva.modulos.usuarios.modelo.Jugador;

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

    @Override
    public void cancelarPorAdmin(Partido partido, Administrador administrador) {
        throw new IllegalStateException("No es posible cancelar el partido, el mismo ya está finalizado.");
    }

    @Override
    public void tick(Partido partido, LocalDateTime ahora) {
        throw new IllegalStateException("No es posible ejecutar el tick del partido, el mismo ya está finalizado.");
    }
}

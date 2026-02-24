package com.gestionDeportiva.modulos.partidos.partidoEstados;

import java.time.LocalDateTime;

import com.gestionDeportiva.modulos.partidos.modelo.Partido;
import com.gestionDeportiva.modulos.partidos.states.IEstadoPartido;
import com.gestionDeportiva.modulos.usuarios.modelo.Administrador;
import com.gestionDeportiva.modulos.usuarios.modelo.Jugador;

public class EstadoEnJuego implements IEstadoPartido {
    @Override
    public String nombre() {
        return "En Juego.";
    }

    @Override
    public void seleccionar(Partido partido, Jugador jugador) {
        throw new IllegalStateException("No es posible seleciconar el partido, el mismo ya está en juego.");
    }

    @Override
    public void confirmarParticipacion(Partido partido, Jugador jugador) {
        throw new IllegalStateException("No es posible confirmar el partido, el mismo ya está en juego.");
    }

    @Override
    public void cancelarPorAdmin(Partido partido, Administrador administrador) {
        throw new IllegalStateException("No es posible cancelar el partido, el mismo ya está en juego.");
    }

    @Override
    public void tick(Partido partido, LocalDateTime ahora) {
        if (!ahora.isBefore(partido.getFechaHoraFin())){
            partido.registrarEnHistorial();
            partido.cambiarEstado(new EstadoFinalizado());
        }
    }
}

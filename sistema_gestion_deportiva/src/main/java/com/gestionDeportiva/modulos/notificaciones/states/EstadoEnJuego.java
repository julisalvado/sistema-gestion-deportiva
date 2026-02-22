package com.gestionDeportiva.modulos.notificaciones.states;

import com.gestionDeportiva.Administrador;
import com.gestionDeportiva.Jugador;
import com.gestionDeportiva.Partido;

import java.time.LocalDateTime;

public class EstadoEnJuego implements IEstadoPartido{
    @Override
    public String nombre() {
        return "En Juego.";
    }

    @Override
    public void seleccionar(Partido partido, Jugador jugador) {
        throw new IllegalStateException("No es posible seleciconar el partido, el mismo ya está en juego.");
    }

    @Override
    public void confrimar(Partido partido, Jugador jugador) {
        throw new IllegalStateException("No es posible confirmar el partido, el mismo ya está en juego.");
    }

    @Override
    public void cancelarPorAdmin(Partido partido, Administrador administrador) {
        throw new IllegalStateException("No es posible cancelar el partido, el mismo ya está en juego.");
    }

    @Override
    public void tick(Partido partido, LocalDateTime ahora) {
        if (!ahora.isBefore(partido.getFechaHoraFin())){
            partido.cambiarEstado(new EstadoFinalizado());
        }
    }
}

package com.gestionDeportiva.modulos.notificaciones.partidoEstados;

import com.gestionDeportiva.Partido;
import com.gestionDeportiva.modulos.Usuario.modelo.Administrador;
import com.gestionDeportiva.modulos.Usuario.modelo.Jugador;
import com.gestionDeportiva.modulos.notificaciones.states.IEstadoPartido;

import java.time.LocalDateTime;

public class EstadoEsperandoJugadores implements IEstadoPartido {
    @Override
    public String nombre() {
        return "Esperando jugadores.";
    }

    @Override
    public void seleccionar(Partido partido, Jugador jugador) {
        partido.agregarJugadorInterno(jugador);
    }

    @Override
    public void confirmarParticipacion(Partido partido, Jugador jugador) {
        partido.confirmarInterno(jugador);

        if (partido.todosConfirmaron()){
            partido.cambiarEstado(new EstadoConfirmado());
        }
    }

    @Override
    public void cancelarPorAdmin(Partido partido, Administrador administrador) {

    }

    @Override
    public void tick(Partido partido, LocalDateTime ahora) {
        if(!ahora.isBefore(partido.getFechaHoraInicio())){
            partido.cambiarEstado(new EstadoCancelado());
        }
    }
}

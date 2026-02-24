package com.gestionDeportiva.modulos.partidos.partidoEstados;

import java.time.LocalDateTime;

import com.gestionDeportiva.modulos.partidos.modelo.Partido;
import com.gestionDeportiva.modulos.partidos.states.IEstadoPartido;
import com.gestionDeportiva.modulos.usuarios.modelo.Administrador;
import com.gestionDeportiva.modulos.usuarios.modelo.Jugador;

public class EstadoEsperandoJugadores implements IEstadoPartido {
    @Override
    public String nombre() {
        return "Esperando jugadores.";
    }

    @Override
    public void seleccionar(Partido partido, Jugador jugador) {
        if (!partido.estaLleno()){
            partido.agregarJugadorInterno(jugador);
            System.out.println("Jugador agregado. \n");
        }
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
        partido.cambiarEstado(new EstadoCancelado());
        System.out.println(administrador.getNombre() + " ha cancelado el partido de " + partido.getDeporte().getNombre() + " programado para el día " + partido.getFechaHoraInicio());
    }

    @Override
    public void tick(Partido partido, LocalDateTime ahora) {
        if(!ahora.isBefore(partido.getFechaHoraInicio())){
            partido.cambiarEstado(new EstadoCancelado());
        }
    }
}

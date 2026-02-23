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
        if (!partido.estaLleno()){
            partido.notificar(partido.getJugadores(), "El jugador " + jugador.getNombre + " se ha añadido para jugar en un partido en el que estas anotado. \n" + partido.getInfo());
            partido.agregarJugadorInterno(jugador);
            System.out.println("Jugador agregado. \n");
        }
    }

    @Override
    public void confirmarParticipacion(Partido partido, Jugador jugador) {
        partido.confirmarInterno(jugador);

        if (partido.todosConfirmaron()){
            partido.notificar(partido.getJugadores(), "Todos los jugadores han confrimado su asistencia, el partido se jugara en la fecha " + partido.getFechaHoraInicio + " quede atento a su casilla de correo!.");
            partido.cambiarEstado(new EstadoConfirmado());
        }
    }

    public void cancelarPorAdmin(Partido partido, Administrador administrador) {
        partido.cambiarEstado(new EstadoCancelado());
        System.out.println(administrador.getNombre() + " ha cancelado el partido de " + partido.getDeporte().getNombre() + " programado para el día " + partido.getFechaHoraInicio());
    }

    public void tick(Partido partido, LocalDateTime ahora) {
        if(!ahora.isBefore(partido.getFechaHoraInicio())){
            partido.notificar(partido.getJugadores(), "El timepo limite para aceptar las confirmaciones se supero ");
            partido.cambiarEstado(new EstadoCancelado());
        }
    }
}

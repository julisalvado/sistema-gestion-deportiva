package com.gestionDeportiva.modulos.notificaciones.partidoEstados;

import com.gestionDeportiva.Partido;
import com.gestionDeportiva.modulos.Usuario.modelo.Administrador;
import com.gestionDeportiva.modulos.Usuario.modelo.Jugador;
import com.gestionDeportiva.modulos.notificaciones.states.IEstadoPartido;

import java.time.LocalDateTime;

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
            partido.notificar(partido.getJugadores(), "El partido ah finalizado! Espero lo hayas disfutado! \n Pronto podras ver los comentarios y las esadisticas dejadas por el lider y ver el partido desde tu historial!");
            partido.registrarEnHistorial(partido);
            partido.cambiarEstado(new EstadoFinalizado());
        }
    }
}

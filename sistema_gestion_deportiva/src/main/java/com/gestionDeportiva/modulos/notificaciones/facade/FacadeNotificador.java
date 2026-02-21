package com.gestionDeportiva.modulos.notificaciones.facade;

import java.util.List;

import com.gestionDeportiva.modulos.notificaciones.interfaces.IStrategyNotificador;
import com.gestionDeportiva.modulos.notificaciones.modelo.Notificacion;
import com.gestionDeportiva.modulos.notificaciones.modelo.Notificador;

public class FacadeNotificador {

    private List<IStrategyNotificador> estrategias;

    public FacadeNotificador(List<IStrategyNotificador> estrategias) {
        this.estrategias = estrategias;
    }

    public void notificarEnMedios(String mensaje) {
		Notificacion notificacion = new Notificacion(mensaje);

        for (IStrategyNotificador estrategia: estrategias) {
            Notificador notificador = new Notificador(estrategia);
            notificador.enviarNotificacion(notificacion);
        }
	}
}

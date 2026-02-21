package com.gestionDeportiva.modulos.notificaciones.facade;

public class FacadeNotificador {

    private List<IStrategyNotificador> estrategias;

    public FacadeNotificador(List<IStrategyNotificador> estrategias) {
        this.estrategias = estrategias;
    }

    public String notificarEnMedios(String mensaje) {
		Notificacion notificacion = new Notificacion(mensaje);

        for (IStrategyNotificador estrategia: estrategias) {
            Notificador notificador = new Notificador(estrategia);
            notificador.notificar(notificacion);
        }
	}
}

package com.gestionDeportiva.modulos.notificaciones.strategies;

import com.gestionDeportiva.modulos.notificaciones.interfaces.IStrategyNotificador;

public class NotificadorPush implements IStrategyNotificador {

    private IAdapterNotificadorPush adapter;

    public NotificadorPush(IAdapterNotificadorPush adapter) {
        this.adapter = adapter;
    }

    public void enviarNotificacion(Notificacion notificacion) {
        this.adapter.enviarNotificacion(notificacion);
    }
}

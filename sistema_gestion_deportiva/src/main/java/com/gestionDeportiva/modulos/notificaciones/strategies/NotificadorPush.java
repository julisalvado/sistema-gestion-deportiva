package com.gestionDeportiva.modulos.notificaciones.strategies;

import com.gestionDeportiva.modulos.notificaciones.interfaces.IAdapterNotificadorPush;
import com.gestionDeportiva.modulos.notificaciones.interfaces.IStrategyNotificador;
import com.gestionDeportiva.modulos.notificaciones.modelo.Notificacion;

public class NotificadorPush implements IStrategyNotificador {

    private IAdapterNotificadorPush adapter;

    public NotificadorPush() {
    }

    public void setAdapter(IAdapterNotificadorPush adapter) {
        this.adapter = adapter;
    }

    public void enviarNotificacion(Notificacion notificacion) {
        this.adapter.enviarNotificacionPush(notificacion.getDestino(), notificacion.getMensaje());
    }

    public String getTipo() {
        return this.adapter.getTipo();
    }
}

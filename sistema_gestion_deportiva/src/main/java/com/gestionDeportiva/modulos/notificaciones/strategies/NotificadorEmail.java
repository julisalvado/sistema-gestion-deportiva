package com.gestionDeportiva.modulos.notificaciones.strategies;

import com.gestionDeportiva.modulos.notificaciones.interfaces.IAdapterNotificadorEmail;
import com.gestionDeportiva.modulos.notificaciones.modelo.Notificacion;

public class NotificadorEmail {
    
    private IAdapterNotificadorEmail adapter;

    public NotificadorEmail(IAdapterNotificadorEmail adapter) {
        this.adapter = adapter;
    }

    public void enviarNotificacion(Notificacion notificacion) {
        this.adapter.enviarNotificacionEmail(notificacion);
    }
}

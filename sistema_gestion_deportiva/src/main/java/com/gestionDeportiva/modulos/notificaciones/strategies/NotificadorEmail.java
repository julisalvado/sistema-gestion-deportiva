package com.gestionDeportiva.modulos.notificaciones.strategies;

import com.gestionDeportiva.modulos.notificaciones.interfaces.IAdapterNotificadorEmail;
import com.gestionDeportiva.modulos.notificaciones.interfaces.IStrategyNotificador;
import com.gestionDeportiva.modulos.notificaciones.modelo.Notificacion;

public class NotificadorEmail implements IStrategyNotificador {
    
    private IAdapterNotificadorEmail adapter;

    public NotificadorEmail() {
        
    }

    public void setAdapter(IAdapterNotificadorEmail adapter) {
        this.adapter = adapter;
    }

    public void enviarNotificacion(Notificacion notificacion) {
        this.adapter.enviarNotificacionEmail(notificacion.getDestino(), notificacion.getMensaje());
    }

    public String getTipo() {
        return this.adapter.getTipo();
    }
}

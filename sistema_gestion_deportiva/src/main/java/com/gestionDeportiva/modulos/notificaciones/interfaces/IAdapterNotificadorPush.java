package com.gestionDeportiva.modulos.notificaciones.interfaces;

import com.gestionDeportiva.modulos.notificaciones.modelo.Notificacion;

public interface IAdapterNotificadorPush {
    public abstract void enviarNotificacionPush(Notificacion notificacion);
}

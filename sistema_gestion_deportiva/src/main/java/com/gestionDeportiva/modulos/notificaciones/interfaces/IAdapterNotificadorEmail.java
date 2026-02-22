package com.gestionDeportiva.modulos.notificaciones.interfaces;

import com.gestionDeportiva.modulos.notificaciones.modelo.Notificacion;

public interface IAdapterNotificadorEmail {
    public abstract void enviarNotificacionEmail(Notificacion notificacion);
}

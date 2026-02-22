package com.gestionDeportiva.modulos.notificaciones.interfaces;

import com.gestionDeportiva.modulos.notificaciones.modelo.Notificacion;

public interface IStrategyNotificador {

    public abstract void enviarNotificacion(Notificacion notificacion);

    public abstract String getTipo();
}

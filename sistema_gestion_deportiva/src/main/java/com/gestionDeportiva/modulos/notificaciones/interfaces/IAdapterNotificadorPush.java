package com.gestionDeportiva.modulos.notificaciones.interfaces;


public interface IAdapterNotificadorPush {
    public abstract void enviarNotificacionPush(String destino, String mensaje);

    public abstract String getTipo();
}

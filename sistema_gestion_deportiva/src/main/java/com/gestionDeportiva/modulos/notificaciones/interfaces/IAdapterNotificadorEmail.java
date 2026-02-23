package com.gestionDeportiva.modulos.notificaciones.interfaces;

public interface IAdapterNotificadorEmail {
    public abstract void enviarNotificacionEmail(String destino, String mensaje);

    public abstract String getTipo();
}

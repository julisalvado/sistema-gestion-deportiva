package com.gestionDeportiva.modulos.notificaciones.adapters;

import com.gestionDeportiva.modulos.notificaciones.interfaces.IAdapterNotificadorEmail;
import com.gestionDeportiva.modulos.notificaciones.modelo.Notificacion;

public class AdapterEmailJavaMail implements IAdapterNotificadorEmail{
    private JavaMail adaptada;

    public AdapterEmailJavaMail(JavaMail adaptada) {
        this.adaptada = adaptada;
    }

    public void enviarNotificacionEmail(Notificacion notificacion) {
        this.adaptada.enviarNotificacionEmail(notificacion);
    }
}

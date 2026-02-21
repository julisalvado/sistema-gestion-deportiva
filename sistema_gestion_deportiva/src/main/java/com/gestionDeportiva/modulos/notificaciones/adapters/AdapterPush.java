package com.gestionDeportiva.modulos.notificaciones.adapters;

import com.gestionDeportiva.modulos.notificaciones.interfaces.IAdapterNotificadorPush;
//importar a la clase adaptada
import com.gestionDeportiva.modulos.notificaciones.modelo.Notificacion;

public class AdapterPush implements IAdapterNotificadorPush {

    private Firebase adaptada;

    public AdapterPush(Firebase adaptada) {
        this.adaptada = adaptada;
    }

    public void enviarNotificacionPush(Notificacion notificacion) {
        this.adaptada.enviarNotificacionPush();
    }


}

package com.gestionDeportiva.modulos.notificaciones.modelo;

import com.gestionDeportiva.modulos.notificaciones.interfaces.IStrategyNotificador;

public class Notificador {
    private IStrategyNotificador estrategia;
    private Notificacion notificacion;

    public Notificador(IStrategyNotificador estrategia) {
        this.estrategia= estrategia;
    }

    public void cambiarEstrategia(IStrategyNotificador nueva) {
        this.estrategia = nueva;
    }

    public void enviarNotificacion(Notificacion notificacion) {
        if (notificacion != null && estrategia != null) {
            this.estrategia.enviarNotificacion(notificacion);
        }
    }
    
    public void setNotificacion(Notificacion notificacion) {
        this.notificacion = notificacion;
    }
}

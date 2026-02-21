package com.gestionDeportiva.modulos.notificaciones.modelo;

import com.gestionDeportiva.modulos.notificaciones.interfaces.IStrategyNotificador;

public class Notificador {
    private IStrategyNotificador estrategia;

    public Notificador(IStrategyNotificador estrategia) {
        this.estrategia= estrategia;
    }

    public void cambiarEstrategia(IStrategyNotificador nueva) {
        this.estrategia = nueva;
    }

    public void enviarNotificacion(Notificacion notificacion) {
        this.estrategia.enviarNotificacion(notificacion);
    }
    
}

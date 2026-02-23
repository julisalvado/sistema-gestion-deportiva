package com.gestionDeportiva.modulos.notificaciones.strategies;
import com.gestionDeportiva.modulos.notificaciones.interfaces.IStrategyNivelJuego;

public class Avanzado implements IStrategyNivelJuego {

    @Override
    public void definirNivelJuego() {
        System.out.println("El jugador tiene nivel avanzado.");
    }

    @Override
    public int getValorNivel() {
        return 3;
    }
    
}

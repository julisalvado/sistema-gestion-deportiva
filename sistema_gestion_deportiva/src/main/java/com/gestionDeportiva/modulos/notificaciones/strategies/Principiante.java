package com.gestionDeportiva.modulos.notificaciones.strategies;
import com.gestionDeportiva.modulos.notificaciones.interfaces.IStrategyNivelJuego;

public class Principiante implements IStrategyNivelJuego {

    @Override
    public void definirNivelJuego() {
        System.out.println("El jugador tiene nivel principiante.");
    }

    @Override
    public int getValorNivel() {
        return 1;
    }
}

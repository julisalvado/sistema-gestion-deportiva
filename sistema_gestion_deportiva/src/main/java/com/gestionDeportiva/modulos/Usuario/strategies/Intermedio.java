package com.gestionDeportiva.modulos.Usuario.strategies;
import com.gestionDeportiva.modulos.Usuario.interfaces.IStrategyNivelJuego;

public class Intermedio implements IStrategyNivelJuego {

    @Override
    public void definirNivelJuego() {
        System.out.println("El jugador tiene nivel intermedio.");
    }

    @Override
    public int getValorNivel() {
        return 2;
    }
    
}

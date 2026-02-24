package com.gestionDeportiva.modulos.usuarios.strategies;
import com.gestionDeportiva.modulos.usuarios.interfaces.IStrategyNivelJuego;

public class Intermedio implements IStrategyNivelJuego {

    @Override
    public String getName() {
        return "Intermedio";
    }

    @Override
    public void definirNivelJuego() {
        System.out.println("El jugador tiene nivel intermedio.");
    }

    @Override
    public int getValorNivel() {
        return 2;
    }
    
}

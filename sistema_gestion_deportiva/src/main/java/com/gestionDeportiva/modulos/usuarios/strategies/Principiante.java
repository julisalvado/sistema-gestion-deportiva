package com.gestionDeportiva.modulos.usuarios.strategies;
import com.gestionDeportiva.modulos.usuarios.interfaces.IStrategyNivelJuego;

public class Principiante implements IStrategyNivelJuego {

    @Override
    public String getName() {
        return "Principiante";
    }

    @Override
    public void definirNivelJuego() {
        System.out.println("El jugador tiene nivel principiante.");
    }

    @Override
    public int getValorNivel() {
        return 1;
    }
}

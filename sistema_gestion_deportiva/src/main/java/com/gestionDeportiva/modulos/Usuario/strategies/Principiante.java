package com.gestionDeportiva.modulos.Usuario.strategies;
import com.gestionDeportiva.modulos.Usuario.interfaces.IStrategyNivelJuego;

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

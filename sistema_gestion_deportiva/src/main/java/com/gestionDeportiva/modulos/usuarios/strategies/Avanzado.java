package com.gestionDeportiva.modulos.usuarios.strategies;
import com.gestionDeportiva.modulos.usuarios.interfaces.IStrategyNivelJuego;

public class Avanzado implements IStrategyNivelJuego {

    @Override
    public String getName() {
        return "Avanzado";
    }

    @Override
    public void definirNivelJuego() {
        System.out.println("El jugador tiene nivel avanzado.");
    }

    @Override
    public int getValorNivel() {
        return 3;
    }
    
}

package com.gestionDeportiva;

import java.util.List;

import com.gestionDeportiva.modulos.Usuario.interfaces.IStrategyEmparejamiento;
import com.gestionDeportiva.modulos.Usuario.modelo.Jugador;

public class BusquedaPartidos {
    private IStrategyEmparejamiento estrategiaBusqueda;

    public BusquedaPartidos(IStrategyEmparejamiento estrategiaBusqueda) {
        this.estrategiaBusqueda = estrategiaBusqueda;
    }

    public List<Partido> buscarPartido(Jugador jugador, List<Partido> partidos){
        if(partidos.isEmpty()) {
            throw new IllegalArgumentException("La lista de partidos no debe estar vacia.");
        }
        else if(jugador == null){
            throw new IllegalArgumentException("Debe haber un jugador para realizar la busqeuda");
        }
        return estrategiaBusqueda.filtrar(jugador, partidos);
    }

    public void cambiarEstrategiaBusqueda(IStrategyEmparejamiento emparejamiento){
        this.estrategiaBusqueda = emparejamiento;
    }
}
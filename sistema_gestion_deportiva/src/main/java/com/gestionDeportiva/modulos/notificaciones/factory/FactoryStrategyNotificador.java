package com.gestionDeportiva.modulos.notificaciones.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import com.gestionDeportiva.modulos.notificaciones.interfaces.IStrategyNotificador;

public class FactoryStrategyNotificador {

    public List<IStrategyNotificador> crearEstrategias() {

        List<IStrategyNotificador> listaEstrategias = new ArrayList<>();

        ServiceLoader<IStrategyNotificador> loader = ServiceLoader.load(IStrategyNotificador.class);

        for (IStrategyNotificador estrategia: loader) {
            listaEstrategias.add(estrategia);
        }

        return listaEstrategias;
    }

}

/* 
// 1. La factory busca en el archivo de texto y crea la lista
var medios = FactoryStrategyNotificador.crearMediosPorDefecto();

// 2. Se la pasamos al Facade
FacadeNotificador facade = new FacadeNotificador(medios);

// 3. Ejecutamos
facade.enviarANotificadores("¡Hola! Esto es escalable.");*/
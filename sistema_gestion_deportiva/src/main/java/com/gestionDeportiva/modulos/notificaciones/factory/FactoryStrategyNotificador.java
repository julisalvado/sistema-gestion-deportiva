package com.gestionDeportiva.modulos.notificaciones.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import com.gestionDeportiva.modulos.notificaciones.interfaces.IAdapterNotificadorPush;
import com.gestionDeportiva.modulos.notificaciones.interfaces.IStrategyNotificador;
import com.gestionDeportiva.modulos.notificaciones.strategies.NotificadorPush;

import java.util.ServiceLoader;

public class FactoryStrategyNotificador {

    public static IStrategyNotificador crearEstrategia(String tipo) {
        // ServiceLoader busca todas las implementaciones anotadas en META-INF/services
        ServiceLoader<IStrategyNotificador> loaderEstrategias = ServiceLoader.load(IStrategyNotificador.class);
        
        ServiceLoader<IAdapterNotificadorPush> loaderAdapters = ServiceLoader.load(IAdapterNotificadorPush.class);

        for (IStrategyNotificador estrategia : loaderEstrategias) {
            // Si la estrategia es de tipo Push, intentamos ponerle el adapter correcto
            if (estrategia instanceof NotificadorPush) {
                for (IAdapterNotificadorPush adapter : loaderAdapters) {
                    if (adapter.getTipo().equalsIgnoreCase(tipo)) {
                        ((NotificadorPush) estrategia).setAdapter(adapter);
                        return estrategia;
                    }
                }
            } 
            // Si es otra estrategia (como Email) que no usa adapters de Push
            else if (estrategia.getTipo().equalsIgnoreCase(tipo)) {
                return estrategia;
            }
        }
        throw new IllegalArgumentException("No hay soporte para: " + tipo);
    }
}


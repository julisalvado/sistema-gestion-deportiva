package com.gestionDeportiva.modulos.notificaciones.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import com.gestionDeportiva.modulos.notificaciones.adapters.AdapterEmailJavaMail;
import com.gestionDeportiva.modulos.notificaciones.adapters.AdapterPushFirebase;
import com.gestionDeportiva.modulos.notificaciones.interfaces.IAdapterNotificadorPush;
import com.gestionDeportiva.modulos.notificaciones.interfaces.IStrategyNotificador;
import com.gestionDeportiva.modulos.notificaciones.strategies.NotificadorEmail;
import com.gestionDeportiva.modulos.notificaciones.strategies.NotificadorPush;


public class FactoryStrategyNotificador {

    private static final Map<String, IStrategyNotificador> estrategias = new HashMap<>();

    static {
        // 1. Configuramos EMAIL
        NotificadorEmail emailStr = new NotificadorEmail();
        emailStr.setAdapter(new AdapterEmailJavaMail()); 
        estrategias.put("EMAIL", emailStr);

        // 2. Configuramos PUSH
        NotificadorPush pushStr = new NotificadorPush();
        pushStr.setAdapter(new AdapterPushFirebase());
        estrategias.put("FIREBASE", pushStr);
    }

    public static IStrategyNotificador crearEstrategia(String tipo) {
        IStrategyNotificador estrategia = estrategias.get(tipo.toUpperCase());
        if (estrategia == null) {
            throw new IllegalArgumentException("No hay soporte para el medio: " + tipo);
        }
        return estrategia;
        
    }
}


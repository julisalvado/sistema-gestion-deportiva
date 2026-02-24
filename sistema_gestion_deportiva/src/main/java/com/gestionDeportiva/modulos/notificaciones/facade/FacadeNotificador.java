package com.gestionDeportiva.modulos.notificaciones.facade;

import java.util.Collections;
import java.util.List;

import com.gestionDeportiva.modulos.notificaciones.modelo.MedioContacto;
import com.gestionDeportiva.modulos.deportes.observer.IObserver;
import com.gestionDeportiva.modulos.notificaciones.factory.FactoryStrategyNotificador;
import com.gestionDeportiva.modulos.notificaciones.interfaces.IStrategyNotificador;
import com.gestionDeportiva.modulos.notificaciones.modelo.Notificacion;
import com.gestionDeportiva.modulos.notificaciones.modelo.Notificador;


public class FacadeNotificador {

    public void notificar(IObserver usuario, String mensaje) {
        // Simplemente lo convertimos en una lista de uno y llamamos al otro método
        this.notificar(Collections.singletonList(usuario), mensaje);
    }

    public void notificar(List<? extends IObserver> usuarios, String mensaje) {
        for (IObserver u : usuarios) {

            List<MedioContacto> medios = u.getMediosContacto();
            
            if (medios == null || medios.isEmpty()) {
                System.out.println("El usuario no tiene medios de contacto configurados.");
                continue;
            }

            for (MedioContacto medio : medios) {
                try {
                    Notificacion notificacion = new Notificacion(medio.getValor(), mensaje);
                    
                    IStrategyNotificador estrategia = FactoryStrategyNotificador.crearEstrategia(medio.getTipo());

                    Notificador notificador = new Notificador(estrategia);
                    notificador.enviarNotificacion(notificacion);
                    
                } catch (Exception e) {
                    System.err.println("Error enviando por " + medio.getTipo() + ": " + e.getMessage());
                }
            }
	    }
    }
}

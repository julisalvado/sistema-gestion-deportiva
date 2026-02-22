package com.gestionDeportiva.modulos.notificaciones.facade;

import java.util.List;

import com.gestionDeportiva.Usuario;
import com.gestionDeportiva.modulos.notificaciones.MedioContacto;
import com.gestionDeportiva.modulos.notificaciones.dao.MedioContactoDAO;
import com.gestionDeportiva.modulos.notificaciones.factory.FactoryStrategyNotificador;
import com.gestionDeportiva.modulos.notificaciones.interfaces.IStrategyNotificador;
import com.gestionDeportiva.modulos.notificaciones.modelo.Notificacion;
import com.gestionDeportiva.modulos.notificaciones.modelo.Notificador;

public class FacadeNotificador {

    public void notificar(Usuario usuario, String mensaje) {
        List<MedioContacto> medios = usuario.getMediosContacto();
        
        if (medios == null || medios.isEmpty()) {
            System.out.println("El usuario no tiene medios de contacto configurados.");
            return;
        }

        for (MedioContacto medio : medios) {
            Notificacion notificacion = new Notificacion(medio.getValor(), mensaje);
            IStrategyNotificador estrategia = FactoryStrategyNotificador.crearEstrategia(medio.getTipo());

        
            Notificador notificador = new Notificador(estrategia);
            notificador.enviarNotificacion(notificacion);
        }
	}
}

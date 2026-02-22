package com.gestionDeportiva.modulos.notificaciones.interfaces;

import java.util.Map;

public interface INotificable {
    /**
     * Devuelve un mapa con los medios de contacto.
     * Ejemplo: {"email": "leo@mail.com", "tokenPush": "abcd123"}
     */
    public abstract Map<String, String> getDatosContacto();
}

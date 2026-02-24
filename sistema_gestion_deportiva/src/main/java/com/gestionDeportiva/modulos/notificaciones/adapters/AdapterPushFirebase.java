package com.gestionDeportiva.modulos.notificaciones.adapters;

import com.gestionDeportiva.modulos.notificaciones.interfaces.IAdapterNotificadorPush;

public class AdapterPushFirebase implements IAdapterNotificadorPush {

    @Override
    public void enviarNotificacionPush(String destino, String mensaje) {
        System.out.println("[SIMULACIÓN FIREBASE]");
        System.out.println("Conectando con servidor de Google...");
        System.out.println("Enviando push al token: " + destino);
        System.out.println("Mensaje: " + mensaje);
        System.out.println(">>> Estado: Enviado con éxito (Mock)");
    }

    public String getTipo() {
        return "FIREBASE";
    }

}



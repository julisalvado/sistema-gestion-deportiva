package com.gestionDeportiva.modulos.notificaciones.modelo;

public class Notificacion {
    private String destino;   // El token de Firebase o el Email que sacamos de SQLite
    private String mensaje;   // El texto que querés mandar

    public Notificacion(String destino, String mensaje) {
        this.destino = destino;
        this.mensaje = mensaje;
    }

    // Getters necesarios para que las Estrategias y Adapters lean los datos
    public String getDestino() {
        return destino;
    }

    public String getMensaje() {
        return mensaje;
    }
}

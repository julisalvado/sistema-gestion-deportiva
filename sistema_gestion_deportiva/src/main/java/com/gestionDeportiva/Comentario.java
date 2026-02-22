package com.gestionDeportiva;

import java.time.LocalDateTime;

public class Comentario {
    private Administrador administrador;
    private String contenido;
    private LocalDateTime fecha;

    public Comentario (Administrador administrador, String contenido) {
        this.administrador = administrador;
        this.contenido = contenido;
        this.fecha = LocalDateTime.now();
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public String getContenido() {
        return contenido;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
}

package com.gestionDeportiva.modulos.partidos.modelo;

import java.time.LocalDateTime;

import com.gestionDeportiva.modulos.usuarios.modelo.Administrador;

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

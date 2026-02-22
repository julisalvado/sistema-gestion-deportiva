package com.gestionDeportiva.modulos.notificaciones.modelo;

public class MedioContacto {
    private String tipo;  // Aquí vendrá "firebase", "email", etc.
    private String valor; // Aquí vendrá el token o el correo.

    public MedioContacto(String tipo, String valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getTipo() { return tipo; }
    public String getValor() { return valor; }
}

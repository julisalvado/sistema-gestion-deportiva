package com.gestionDeportiva.modulos.Usuario.modelo;

import java.util.ArrayList;
import java.util.List;

import com.gestionDeportiva.modulos.notificaciones.modelo.MedioContacto;

public abstract class Usuario {
    protected int idUsuario;
    protected String nombre;
    protected String email;
    protected String contrasenia;
    protected List<MedioContacto> mediosContacto;

    public Usuario(String nombre, String email, String contrasenia) {
        this.nombre = nombre;
        this.email = email;
        this.contrasenia = contrasenia;
        this.mediosContacto = new ArrayList<>();
    }

    // Getters y Setters
    public void setMediosContacto(List<MedioContacto> medios) {
        this.mediosContacto = medios;
    }

    public List<MedioContacto> getMediosContacto() {
        return mediosContacto;
    }

    public void agregarMedio(MedioContacto medio) {
        this.mediosContacto.add(medio);
    }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre() { 
        return nombre; 
    }
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    public String getEmail() { 
        return email; 
    }
    public void setEmail(String email) { 
        this.email = email; 
    }
    public String getContrasenia() { 
        return contrasenia; 
    }
    public void setContrasenia(String contrasenia) { 
        this.contrasenia = contrasenia; 
    }


}

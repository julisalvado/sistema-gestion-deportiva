package com.gestionDeportiva.modulos.usuarios.modelo;

import java.util.ArrayList;
import java.util.List;

import com.gestionDeportiva.modulos.deportes.observer.IObservable;
import com.gestionDeportiva.modulos.deportes.observer.IObserver;
import com.gestionDeportiva.modulos.notificaciones.facade.FacadeNotificador;
import com.gestionDeportiva.modulos.notificaciones.modelo.MedioContacto;

public abstract class Usuario implements IObserver{
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

    public void serNotificadoPor(IObservable observable) {
        if (observable != null) {
            observable.agregarObserver(this); 
        }
    }

    public void actualizar(String mensaje) {
        FacadeNotificador fachada = new FacadeNotificador();
        fachada.notificar(this,mensaje);
    }

    public void agregarMedio(MedioContacto medio) {
        this.mediosContacto.add(medio);
    }

    // Getters y Setters
    public void setMediosContacto(List<MedioContacto> medios) {
        this.mediosContacto = medios;
    }

    public List<MedioContacto> getMediosContacto() {
        return mediosContacto;
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

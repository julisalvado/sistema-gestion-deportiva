package com.gestionDeportiva.modulos.deportes.observer;

import java.util.List;

import com.gestionDeportiva.modulos.notificaciones.modelo.MedioContacto;

public interface IObserver {
    public abstract void serNotificadoPor(IObservable observable);
    public abstract List<MedioContacto> getMediosContacto();
    public abstract void actualizar(String mensaje); 
    
}

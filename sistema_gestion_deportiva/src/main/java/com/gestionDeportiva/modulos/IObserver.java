package com.gestionDeportiva.modulos;

import java.util.List;

import com.gestionDeportiva.modulos.Usuario.IObservable;
import com.gestionDeportiva.modulos.notificaciones.modelo.MedioContacto;

public interface IObserver {
    public abstract void serNotificadoPor(IObservable observable);
    public abstract List<MedioContacto> getMediosContacto();
}

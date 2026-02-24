package com.gestionDeportiva.modulos.deportes.observer;

public interface IObservable {
    public abstract void agregarObserver(IObserver observer);
    public abstract void eliminarObserver(IObserver observer);
    public abstract void notificar(String mensaje);
}

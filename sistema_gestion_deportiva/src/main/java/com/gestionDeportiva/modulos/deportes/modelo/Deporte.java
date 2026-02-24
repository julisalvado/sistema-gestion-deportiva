package com.gestionDeportiva.modulos.deportes.modelo;

import java.util.ArrayList;
import java.util.List;

import com.gestionDeportiva.modulos.deportes.observer.IObservable;
import com.gestionDeportiva.modulos.deportes.observer.IObserver;

public abstract class Deporte implements IObservable {
    protected String nombre;
    protected int jugadoresNecesarios;
    protected List<IObserver> observers = new ArrayList<>();

    public Deporte(String nombre, int jugadoresNecesarios) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del deporte no puede ser nulo o vacío.");
        }
        if (jugadoresNecesarios <= 0) {
            throw new IllegalArgumentException("La cantidad de jugadores necesarios debe ser mayor a 0.");
        }
        this.nombre = nombre;
        this.jugadoresNecesarios = jugadoresNecesarios;
    }
    
    public void agregarObserver(IObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void eliminarObserver(IObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("El observer no puede ser nulo.");
        }
        observers.remove(observer);
    }

    public void notificar(String mensaje) {
        List<IObserver> copia = new ArrayList<>(this.observers);
        for (IObserver observer : copia) {
            // El Deporte solo avisa, el Observer hace el resto
            observer.actualizar(mensaje); 
        }
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getJugadoresNecesarios() {
        return this.jugadoresNecesarios;
    }
}





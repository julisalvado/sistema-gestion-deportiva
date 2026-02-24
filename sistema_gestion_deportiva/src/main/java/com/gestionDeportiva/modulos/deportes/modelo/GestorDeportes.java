package com.gestionDeportiva.modulos.deportes.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.gestionDeportiva.modulos.deportes.factory.FactoryDeporte;

public class GestorDeportes {
    private final List<Deporte> deportes = new ArrayList<>();

    // MÉTODO MEJORADO: Ahora el gestor se encarga de pedirlo a la Factory
    public Deporte crearNuevoDeporte(String tipo) {
        // 1. Usamos la Factory internamente
        Deporte nuevoDeporte = FactoryDeporte.crearDeporte(tipo);
        
        // 2. Lo agregamos a nuestra lista (evitando duplicados)
        this.agregarDeporte(nuevoDeporte);
        
        return nuevoDeporte;
    }

    public void agregarDeporte(Deporte deporte) {
        Objects.requireNonNull(deporte, "El deporte no puede ser nulo.");
        if (!deportes.contains(deporte)) {
            deportes.add(deporte);
        }
    }

    public List<Deporte> getDeportes() {
        return Collections.unmodifiableList(deportes);
    }
}


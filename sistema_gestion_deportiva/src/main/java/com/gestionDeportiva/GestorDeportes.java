package com.gestionDeportiva;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GestorDeportes {
    private final List<Deporte> deportes = new ArrayList<>();

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

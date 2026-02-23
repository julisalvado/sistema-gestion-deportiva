package com.gestionDeportiva;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FavoritoSimple implements IFavoritoDeporte {
    private final Deporte deporte;

    public FavoritoSimple(Deporte deporte) {
        this.deporte = Objects.requireNonNull(deporte, "El deporte no puede ser nulo.");
    }

    @Override
    public List<Deporte> getDeportesFavoritos() {
        return Collections.singletonList(deporte);
    }
}

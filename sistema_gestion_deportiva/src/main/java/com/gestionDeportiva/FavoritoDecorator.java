package com.gestionDeportiva;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FavoritoDecorator implements IFavoritoDeporte {
    private final IFavoritoDeporte base;
    private final Deporte agregado;

    public FavoritoDecorator(IFavoritoDeporte base, Deporte agregado) {
        this.base = Objects.requireNonNull(base, "El favorito base no puede ser nulo.");
        this.agregado = Objects.requireNonNull(agregado, "El deporte agregado no puede ser nulo.");
    }

    @Override
    public List<Deporte> getDeportesFavoritos() {
        List<Deporte> favoritos = new ArrayList<>(base.getDeportesFavoritos());
        if (!favoritos.contains(agregado)) {
            favoritos.add(agregado);
        }
        return favoritos;
    }
}

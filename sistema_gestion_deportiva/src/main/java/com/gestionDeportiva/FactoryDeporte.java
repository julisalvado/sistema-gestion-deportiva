package com.gestionDeportiva;

public class FactoryDeporte {

    public Deporte crearDeporte(String tipo) {
        if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException("El tipo de deporte no puede ser nulo o vacío.");
        }

        switch (tipo.trim().toUpperCase()) {
            case "FUTBOL":
                return new Futbol();
            case "BASQUET":
            case "BASKET":
                return new Basquet();
            case "TENIS":
                return new Tenis();
            case "VOLEY":
            case "VOLEIBOL":
                return new Voley();
            case "HANDBALL":
                return new Handball();
            default:
                throw new IllegalArgumentException("Tipo de deporte no soportado: " + tipo);
        }
    }

    public Deporte crearDeporte(String tipo, int jugadoresNecesarios) {
        if (jugadoresNecesarios <= 0) {
            throw new IllegalArgumentException("La cantidad de jugadores necesarios debe ser mayor a 0.");
        }
        if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException("El tipo de deporte no puede ser nulo o vacío.");
        }
        return new Deporte(tipo.trim(), jugadoresNecesarios);
    }
}

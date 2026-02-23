package com.gestionDeportiva.modulos.Usuario.modelo;

import com.gestionDeportiva.Deporte;
import com.gestionDeportiva.HistorialPartidos;
import com.gestionDeportiva.Partido;
import com.gestionDeportiva.modulos.Usuario.interfaces.IStrategyNivelJuego;

public class Jugador extends Usuario {
    private String zona;
    private Deporte deporteFavorito;
    private HistorialPartidos historialPartidos;
    private IStrategyNivelJuego nivelJuego;

    public Jugador(String nombre, String email, String contrasenia) {
        super(nombre, email, contrasenia); // Llama al constructor de Usuario
    }

    public void setZona(String zona) { this.zona = zona; }
    public String getZona() { return zona; }

    public String getNombre() {
        return this.nombre;
    }

    public void cambiarNivelJuego(IStrategyNivelJuego nuevaEstrategia) {
        this.nivelJuego = nuevaEstrategia;
    }

    public IStrategyNivelJuego getNivelJuego() {
        return this.nivelJuego;
    }

    public void seleccionarPartido(Partido partido) {
    if (partido == null) throw new IllegalArgumentException("Partido no puede ser null");

    if (partido.getJugadores().contains(this)) {
        System.out.println("Ya estás inscrito en este partido.");
        return;
        }

    try {
        partido.seleccionar(this);
        System.out.println("Te has inscrito correctamente en el partido.");
    } catch (IllegalStateException e) {
        System.out.println("No se pudo seleccionar el partido: " + e.getMessage());
        }
    }

    public void confirmarAsistencia(Partido partido) {
    if (partido == null) throw new IllegalArgumentException("Partido no puede ser null");

    // Debe estar inscrito previamente
    if (!partido.getJugadores().contains(this)) {
        System.out.println("No estás inscrito en este partido.");
        return;
    }

    try {
        //revisar en Partido el metodo confirmarParticipacion
        //partido.confirmarParticipacion(this);
        System.out.println("Asistencia confirmada.");
    } catch (IllegalStateException e) {
        System.out.println("No se pudo confirmar asistencia: " + e.getMessage());
        }
    }

    public HistorialPartidos getHistorialPartidos() {
    return this.historialPartidos; 
    }

}

package com.gestionDeportiva;

import com.gestionDeportiva.modulos.Usuario.interfaces.IStrategyNivelJuego;
import com.gestionDeportiva.modulos.Usuario.modelo.Administrador;
import com.gestionDeportiva.modulos.Usuario.modelo.Jugador;
import com.gestionDeportiva.modulos.Usuario.modelo.Usuario;
import com.gestionDeportiva.modulos.notificaciones.partidoEstados.EstadoEsperandoJugadores;
import com.gestionDeportiva.modulos.notificaciones.states.*;

import java.time.LocalDateTime;
import java.util.*;

public class Partido {

    private int idPartido;
    private Usuario admin;
    private Deporte deporte;
    private LocalDateTime fechaHoraInicio;
    private int duracionMinutos;
    private String zona;
    private IStrategyNivelJuego nvlMin;
    private IStrategyNivelJuego nvlMax;
    private IEstadoPartido estado;
    private List<Jugador> jugadores;
    private Set<Jugador> confirmados;
    private List<Comentario> comentarios;
    private Estadistica estadistica;


    public Partido(Deporte deporte, int duracionMinutos, String zona, LocalDateTime fechaHoraInicio, IStrategyNivelJuego nvlMin, IStrategyNivelJuego nvlMax, Administrador admin) {

        Objects.requireNonNull(deporte);
        Objects.requireNonNull(zona);
        Objects.requireNonNull(fechaHoraInicio);
        Objects.requireNonNull(nvlMin);
        Objects.requireNonNull(nvlMax);
        Objects.requireNonNull(admin);

        if (duracionMinutos <= 0) {
            throw new IllegalArgumentException("La duración debe ser mayor a 0.");
        }

        LocalDateTime ahora = LocalDateTime.now();
        if (!fechaHoraInicio.isAfter(ahora)) {
            throw new IllegalArgumentException(
                    "La fecha y hora del partido debe ser futura."
            );
        }
        this.deporte = deporte;
        this.duracionMinutos = duracionMinutos;
        this.zona = zona;
        this.fechaHoraInicio = fechaHoraInicio;
        this.nvlMin = nvlMin;
        this.nvlMax = nvlMax;
        this.admin = admin;
        this.jugadores = new ArrayList<>();
        this.confirmados = new HashSet<>();
        this.comentarios = new ArrayList<>();
        this.estado = new EstadoEsperandoJugadores();
    }

    public void seleccionar(Jugador jugador) {
        estado.seleccionar(this, jugador);
    }

    /*public void confirmarParticipacion(Jugador jugador) {
        estado.confirmar(this, jugador);
    }*/

    public void cancelarPorAdmin(Administrador admin) {
        estado.cancelarPorAdmin(this, admin);
    }

    public void tick(LocalDateTime ahora) {
        estado.tick(this, ahora);
    }

    public void agregarJugadorInterno(Jugador jugador) {

        if (estaLleno()) {
            throw new IllegalStateException("El partido está completo.");
        }

        if (jugadores.contains(jugador)) {
            throw new IllegalStateException("El jugador ya está en el partido.");
        }
        jugadores.add(jugador);
    }

    public void confirmarInterno(Jugador jugador) {
        confirmados.add(jugador);
    }

    public boolean estaLleno() {
        return jugadores.size() >= deporte.getJugadoresNecesarios();
    }

    public boolean todosConfirmaron() {
        return estaLleno() && confirmados.size() == jugadores.size();
    }

    private boolean estaFinalizado() {
        return estado.nombre().equals("Finalizado");
    }

    public void agregarComentario(Administrador admin, String contenido) {

        if (!this.admin.equals(admin)) {
            throw new IllegalStateException(
                    "Solo el administrador creador puede comentar."
            );
        }

        if (!estaFinalizado()) {
            throw new IllegalStateException(
                    "Solo se pueden agregar comentarios a partidos finalizados."
            );
        }

        Comentario comentario = new Comentario(admin, contenido);
        comentarios.add(comentario);
    }

    public List<Comentario> getComentarios() {
        return Collections.unmodifiableList(comentarios);
    }

    public void registrarEstadistica(Administrador admin, Estadistica estadistica) {

        if (!this.admin.equals(admin)) {
            throw new IllegalStateException(
                    "Solo el administrador creador puede registrar estadísticas."
            );
        }

        if (!estaFinalizado()) {
            throw new IllegalStateException(
                    "Solo se pueden registrar estadísticas en partidos finalizados."
            );
        }

        if (this.estadistica != null) {
            throw new IllegalStateException(
                    "El partido ya tiene estadísticas registradas."
            );
        }

        this.estadistica = Objects.requireNonNull(estadistica);
    }

    public Optional<Estadistica> getEstadistica() {
        return Optional.ofNullable(estadistica);
    }


    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraInicio.plusMinutes(duracionMinutos);
    }

    public void cambiarEstado(IEstadoPartido nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public IEstadoPartido getEstado() {
        return estado;
    }


    public List<Jugador> getJugadores() {
        return Collections.unmodifiableList(jugadores);
    }

    public Deporte getDeporte() {
        return deporte;
    }

    public Usuario getAdmin() {
        return admin;
    }

    //agregado para el uso de emparejamiento por nivel
    public IStrategyNivelJuego getNvlMin() {
        return nvlMin;
    }

    public IStrategyNivelJuego getNvlMax() {
        return nvlMax;
    }

    //agregado para el uso de emparejamiento por cercania
    public String getZona() {
        return zona;
    }

}
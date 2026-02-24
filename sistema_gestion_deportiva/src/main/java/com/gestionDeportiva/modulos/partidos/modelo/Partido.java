package com.gestionDeportiva.modulos.partidos.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import java.time.format.DateTimeFormatter;

import com.gestionDeportiva.modulos.deportes.modelo.Deporte;
import com.gestionDeportiva.modulos.deportes.observer.IObserver;
import com.gestionDeportiva.modulos.notificaciones.facade.FacadeNotificador;
import com.gestionDeportiva.modulos.partidos.partidoEstados.EstadoEsperandoJugadores;
import com.gestionDeportiva.modulos.partidos.states.IEstadoPartido;
import com.gestionDeportiva.modulos.usuarios.interfaces.IStrategyNivelJuego;
import com.gestionDeportiva.modulos.usuarios.modelo.Administrador;
import com.gestionDeportiva.modulos.usuarios.modelo.Jugador;
import com.gestionDeportiva.modulos.usuarios.modelo.Usuario;

public class Partido {

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

    //ESTE FUNCIONARÁ PARA LAS PRUEBAS
    public Partido(
            Deporte deporte,
            LocalDateTime fechaHoraInicio,
            int duracionMinutos,
            String zona,
            IStrategyNivelJuego nvlMin,
            IStrategyNivelJuego nvlMax,
            Administrador admin,
            List<Jugador> jugadoresIniciales,
            Set<Jugador> confirmadosIniciales,
            IEstadoPartido estadoInicial
    ) {

        Objects.requireNonNull(deporte);
        Objects.requireNonNull(zona);
        Objects.requireNonNull(fechaHoraInicio);
        Objects.requireNonNull(nvlMin);
        Objects.requireNonNull(nvlMax);
        Objects.requireNonNull(admin);
        Objects.requireNonNull(jugadoresIniciales);
        Objects.requireNonNull(confirmadosIniciales);
        Objects.requireNonNull(estadoInicial);

        if (duracionMinutos <= 0) {
            throw new IllegalArgumentException("La duración debe ser mayor a 0.");
        }

        this.deporte = deporte;
        this.fechaHoraInicio = fechaHoraInicio;
        this.duracionMinutos = duracionMinutos;
        this.zona = zona;
        this.nvlMin = nvlMin;
        this.nvlMax = nvlMax;
        this.admin = admin;
        this.jugadores = new ArrayList<>();
        this.confirmados = new HashSet<>();
        this.comentarios = new ArrayList<>();
        this.estado = estadoInicial;
        for (Jugador jugador : jugadoresIniciales) {
            agregarJugadorInterno(jugador);
        }
        for (Jugador jugador : confirmadosIniciales) {
            if (!jugadores.contains(jugador)) {
                throw new IllegalArgumentException(
                        "El jugador " + jugador.getNombre() +
                                " no está en la lista de jugadores anotados."
                );
            }
            confirmarInterno(jugador);
        }
    }


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

        System.out.println("\n[SISTEMA] Nuevo partido creado con éxito:");
        System.out.println(this.getInfo());
    }

    public void seleccionar(Jugador jugador) {
        estado.seleccionar(this, jugador);
    }

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

        if (!nivelPermitido(jugador)) {
            throw new IllegalStateException(
                    "El nivel del jugador no está dentro del rango permitido."
            );
        }
        jugadores.add(jugador);
    }

    public void confirmarInterno(Jugador jugador) {
        confirmados.add(jugador);
        System.out.println("El jugador " + jugador.getNombre() + " ha confirmado su participación en el partido de " + deporte.getNombre() + ".\n");
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

        String mensaje ="AVISO DE CAMBIO DE ESTADO\n" +
                     "El partido de " + this.deporte.getNombre() + " ahora está: " + nuevoEstado.nombre() + "\n" +
                     this.getInfo();
        
        if (this.jugadores != null && !this.jugadores.isEmpty()) {
            System.out.println("[NOTIFICACION] Enviando actualización de estado a los jugadores...");
            this.notificar(mensaje);
        }
    }

    public void confirmarParticipacion(Jugador jugador) {
        estado.confirmarParticipacion(this, jugador);
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

    public IStrategyNivelJuego getNvlMin() {
        return nvlMin;
    }

    public IStrategyNivelJuego getNvlMax() {
        return nvlMax;
    }

    public String getZona() {
        return zona;
    }

    private boolean nivelPermitido(Jugador jugador) {
        int nivelJugador = jugador.getNivelJuego().getValorNivel();
        return nivelJugador >= nvlMin.getValorNivel()
                && nivelJugador <= nvlMax.getValorNivel();
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public String getInfo() {
    // Definimos un formato legible: Día/Mes/Año Hora:Minutos
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        return "\n===== INFORMACIÓN DEL PARTIDO =====\n" +
            "  Deporte:      " + deporte.getNombre() + "\n" +
            "  Organizador:  " + admin.getNombre() + "\n" +
            "  Fecha y Hora: " + fechaHoraInicio.format(formatoFecha) + " hs\n" +
            "  Zona:         " + zona + "\n" +
            "  Duración:     " + duracionMinutos + " minutos\n" +
            "  Niveles:      " + nvlMin.getName() + " hasta " + nvlMax.getName() + "\n" +
            "  Inscritos:    " + jugadores.size() + " (" + confirmados.size() + " confirmados)\n" +
            "  Estado:       " + estado.nombre() + "\n" +
            "====================================";
    }

    public void registrarEnHistorial() {
        for (Jugador jugador : jugadores) {
            jugador.agregarAlHistorial(this);
        }
    }

    public void notificar(String mensaje) {
        FacadeNotificador fachada = new FacadeNotificador();
        fachada.notificar(this.jugadores, mensaje);
    }
}
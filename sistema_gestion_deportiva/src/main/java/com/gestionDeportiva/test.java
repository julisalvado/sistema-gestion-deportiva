package com.gestionDeportiva;


import com.gestionDeportiva.modulos.Usuario.interfaces.IStrategyNivelJuego;
import com.gestionDeportiva.modulos.Usuario.modelo.Administrador;
import com.gestionDeportiva.modulos.Usuario.modelo.Autenticador;
import com.gestionDeportiva.modulos.Usuario.modelo.Jugador;
import com.gestionDeportiva.modulos.Usuario.strategies.Avanzado;
import com.gestionDeportiva.modulos.Usuario.strategies.Intermedio;
import com.gestionDeportiva.modulos.Usuario.strategies.Principiante;
import com.gestionDeportiva.modulos.notificaciones.facade.FacadeNotificador;
import com.gestionDeportiva.modulos.notificaciones.modelo.MedioContacto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class test {
    public static void main(String[] args) {
        //1. CREACIÓN DE USUARIO ADMINISTRADOR
        Administrador admin = new Administrador("admin", "admin@mail.com", "admin123");

        //2. CREACIÓN DE DEPORTE
        Deporte tenis = new Deporte("Tenis", 2);

        //3. CREACIÓN DE PARTIDO
        IStrategyNivelJuego nivelMin = new Principiante();
        IStrategyNivelJuego nivelMax = new Intermedio();

        LocalDateTime fechaPartido1 = LocalDateTime.of(LocalDate.now(), LocalTime.of(18, 0));

        Partido partidoTenis = new Partido(tenis, 10, "San Isidro", fechaPartido1, nivelMin, nivelMax, admin);

        System.out.println(partidoTenis.getInfo());


        //4. CREACIÓN DE USUARIOS JUGADORES
        Jugador jugador1 = new Jugador("juan", "laralarghi@gmail.com", "1234", "San Isidro");
        Jugador jugador2 = new Jugador("ana", "ana@mail.com", "1234", "San Isidro");
        Jugador jugador3 = new Jugador("pedro", "pedro@gmail.com", "1234", "San Isidro");
            //4.1 DE DEPORTE FAVORITO A LOS JUGADORES
        jugador1.setDeporteFavorito(tenis);
        jugador2.setDeporteFavorito(tenis);
        jugador3.setDeporteFavorito(tenis);
            //4.2 DE NIVEL DE JUEGO A LOS JUGADORES
        jugador1.cambiarNivelJuego(new Principiante());
        jugador2.cambiarNivelJuego(new Avanzado());
        jugador3.cambiarNivelJuego(new Intermedio());
            //4.3 DE REGISTRO DE LOS JUGADORES EN EL SISTEMA
        Autenticador autenticador = new Autenticador();
        autenticador.registrarUsuario(jugador1);
        autenticador.registrarUsuario(jugador2);
        autenticador.registrarUsuario(jugador3);
            //4.4 OBTENER INFORMACION DE LOS JUGADORES
        System.out.println(jugador1.getInformacion());
        System.out.println(jugador2.getInformacion());
        System.out.println(jugador3.getInformacion());

        //5. JUGADOR SELECCIONA PARA PARTICIPAR EN PARTIDO DE TENIS
        partidoTenis.seleccionar(jugador1);
        /*partidoTenis.seleccionar(jugador2); ejemplo de error*/
        partidoTenis.seleccionar(jugador3);

        System.out.println(partidoTenis.getInfo());

        //6. JUGADOR CONFIRMA ASISTENCIA AL PARTIDO DE TENIS
        partidoTenis.confirmarParticipacion(jugador1);
        partidoTenis.confirmarParticipacion(jugador3);

        System.out.println(partidoTenis.getInfo());



        //2. CREACIÓN DE USUARIO ADMINISTRADOR

        //3. CRACIÓN DE DEPORTE --> cantidad de jugadores necesarios para formar un equipo: 2


       /* //4. AUTENTICACION DE USUARIOS
        Autenticador autenticador = new Autenticador();
        autenticador.registrarUsuario(admin);
            //4.1 AUTENTICACION DE JUGADOR FALLIDA
        try {
            autenticador.autenticarUsuario("juan", "WRONG_PASS");
            System.out.println("ERROR: Debería haber fallado");
        } catch (Exception e) {
            System.out.println("✅ Login rechazado correctamente: " + e.getMessage());
        }
            //4.2 AUTENTICACION DE JUGADOR EXITOSA
        */




    }
}

    


package com.gestionDeportiva;


import com.gestionDeportiva.modulos.Usuario.modelo.Administrador;
import com.gestionDeportiva.modulos.Usuario.modelo.Autenticador;
import com.gestionDeportiva.modulos.Usuario.modelo.Jugador;
import com.gestionDeportiva.modulos.notificaciones.facade.FacadeNotificador;
import com.gestionDeportiva.modulos.notificaciones.modelo.MedioContacto;


public class test {
    public static void main(String[] args) {
        //1. CREACIÓN DE USUARIO JUGADORES
        Jugador jugador1 = new Jugador("juan", "juan@mail.com", "1234");
        Jugador jugador2 = new Jugador("ana", "ana@mail.com", "1234");

        //2. CREACIÓN DE USUARIO ADMINISTRADOR
        Administrador admin = new Administrador("admin", "admin@mail.com", "admin123");

        //3. CRACIÓN DE DEPORTE --> cantidad de jugadores necesarios para formar un equipo: 2
        Deporte futbol = new Deporte("Futbol", 2);

        //4. AUTENTICACION DE USUARIOS
        Autenticador autenticador = new Autenticador();
        autenticador.registrarUsuario(jugador1);
        autenticador.registrarUsuario(jugador2);
        autenticador.registrarUsuario(admin);
            //4.1 AUTENTICACION DE JUGADOR FALLIDA
        try {
            autenticador.autenticarUsuario("juan", "WRONG_PASS");
            System.out.println("ERROR: Debería haber fallado");
        } catch (Exception e) {
            System.out.println("✅ Login rechazado correctamente: " + e.getMessage());
        }
            //4.2 AUTENTICACION DE JUGADOR EXITOSA





    }
}

    


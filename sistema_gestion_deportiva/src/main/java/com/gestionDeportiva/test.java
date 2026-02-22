package com.gestionDeportiva;

import com.gestionDeportiva.modulos.notificaciones.MedioContacto;
import com.gestionDeportiva.modulos.notificaciones.facade.FacadeNotificador;


public class test {
    public static void main(String[] args) {
        // 1. Creamos el objeto directamente en memoria
        Usuario miUsuario = new Jugador("TuNombre", "tu@email.com", "pass123");
        
        // 2. Le asignamos un "Token" de prueba manualmente
        // Como ya no hay DAO, simulamos que el usuario tiene un medio de contacto
        MedioContacto medioFCM = new MedioContacto("FIREBASE", "token_de_prueba_123");
        miUsuario.agregarMedio(medioFCM);

        // 3. El Facade ahora recibe el objeto y lo procesa
        FacadeNotificador facade = new FacadeNotificador();
        facade.notificar(miUsuario, "Recibiste una notificacion");
    }
}
    


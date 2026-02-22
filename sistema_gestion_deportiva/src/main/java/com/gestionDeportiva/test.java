package com.gestionDeportiva;


import com.gestionDeportiva.modulos.notificaciones.facade.FacadeNotificador;
import com.gestionDeportiva.modulos.notificaciones.modelo.MedioContacto;


public class test {
    public static void main(String[] args) {
            // 1. Creamos al usuario (Jugador) y le seteamos los datos
        Usuario usuario = new Usuario("Leo Messi","correo@gamil.com","deportes");
        // Importante: El Facade buscará el destino aquí. 
        // Asegúrate de que el tipo coincida con el que pusiste en el MAPA de la Factory ("EMAIL")
        MedioContacto medio = new MedioContacto("EMAIL", "julietasalvad@gmail.com");
        usuario.agregarMedio(medio);

        // 2. Instanciamos el Facade
        FacadeNotificador facade = new FacadeNotificador();

        // 3. Llamamos al método con los dos parámetros que te pide
        System.out.println("Enviando notificación vía Facade...");
        facade.notificar(usuario, "¡Prueba exitosa! El sistema de notificaciones funciona.");
        
        System.out.println("Revisa tu consola y tu bandeja de entrada.");
    }
}

    


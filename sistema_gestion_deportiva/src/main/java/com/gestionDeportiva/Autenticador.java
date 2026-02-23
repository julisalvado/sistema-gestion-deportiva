package com.gestionDeportiva;

import com.gestionDeportiva.modulos.notificaciones.interfaces.IAuthenticator;

public class Autenticador implements IAuthenticator{

    @Override
    public void autenticarUsuario(String nombreUsuario, String contrasenia) {
        // Aquí iría la lógica real de autenticación
        System.out.println("Autenticando usuario: " + nombreUsuario);
        System.out.println("Usuario " + nombreUsuario + " autenticado exitosamente.");
    }
    
}

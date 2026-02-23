package com.gestionDeportiva.modulos.Usuario.modelo;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.gestionDeportiva.modulos.Usuario.interfaces.IAuthenticator;

public class Autenticador implements IAuthenticator {
    private final Map<String, Usuario> usuariosRegistrados = new HashMap<>();

    private Usuario usuarioAutenticado;

    public void registrarUsuario(Usuario usuario) {
        Objects.requireNonNull(usuario);
        usuariosRegistrados.put(usuario.getNombre(), usuario);
        System.out.println("Usuario registrado: " + usuario.getNombre());
    }

    @Override
    public void autenticarUsuario(String nombreUsuario, String contrasenia) {

        Usuario usuario = usuariosRegistrados.get(nombreUsuario);

        if (usuario == null) {
            throw new IllegalStateException(
                    "Usuario no registrado: " + nombreUsuario
            );
        }

        if (!usuario.getContrasenia().equals(contrasenia)) {
            throw new IllegalStateException(
                    "Contraseña incorrecta para: " + nombreUsuario
            );
        }

        usuarioAutenticado = usuario;

        System.out.println("✅ Usuario autenticado: " + nombreUsuario);
    }

    public boolean hayUsuarioAutenticado() {
        return usuarioAutenticado != null;
    }

    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public void cerrarSesion() {
        usuarioAutenticado = null;
    }

    public boolean esAdministradorAutenticado() {
        return usuarioAutenticado instanceof Administrador;
    }

    public boolean esJugadorAutenticado() {
        return usuarioAutenticado instanceof Jugador;
    }
}
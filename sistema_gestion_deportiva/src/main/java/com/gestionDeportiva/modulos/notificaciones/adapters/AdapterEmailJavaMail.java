package com.gestionDeportiva.modulos.notificaciones.adapters;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import com.gestionDeportiva.modulos.notificaciones.interfaces.IAdapterNotificadorEmail;

public class AdapterEmailJavaMail implements IAdapterNotificadorEmail{
    private String remitente;
    private String password;

    public AdapterEmailJavaMail() {
        cargarConfiguracion();
    }

    private void cargarConfiguracion() {
        Properties config = new Properties();
        try (FileInputStream fis = new FileInputStream("sistema_gestion_deportiva/config.properties")) {
            config.load(fis);
            this.remitente = config.getProperty("email.usuario");
            this.password = config.getProperty("email.password");
            
            if (this.remitente == null || this.password == null) {
                throw new RuntimeException("Las llaves en config.properties no coinciden.");
            }
        } catch (IOException e) {
            String rutaActual = new java.io.File(".").getAbsolutePath();
            System.err.println("CRÍTICO: No se encontró config.properties en: " + rutaActual);
        }
    }

    public String getTipo() {
        return "EMAIL";
    }

    public void enviarNotificacionEmail(String destino, String mensaje) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destino));
            message.setSubject("Notificación de Gestión Deportiva");
            message.setText(mensaje);

            System.out.println("Enviando correo a " + destino + "...");
            Transport.send(message);
            System.out.println("¡Correo enviado con éxito!");

        } catch (MessagingException e) {
            System.err.println("Error al enviar email: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}

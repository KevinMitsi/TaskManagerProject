package com.example.taskmanager.threads;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailThread extends Thread {
    private final String correoEmisor;
    private final String contrasena;
    private final String correoReceptor;
    private final String subject;
    private final String text;
    private volatile boolean isRunning = true; // Flag de control


    public EmailThread(String subject, String text,String correoReceptor) {
        this.correoEmisor = "kegarrapala.2003@gmail.com";
        this.contrasena = "vjlxcltitkrpobbh";
        this.correoReceptor = correoReceptor;
        this.subject = subject;
        this.text = text;
    }

    @Override
    public void run() {
        try {
            enviarMensajeCorreo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void enviarMensajeCorreo() throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(correoEmisor, contrasena);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correoEmisor));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoReceptor));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
            isRunning=false;
        } catch (MessagingException e) {
            throw new Exception("Error al enviar el correo: " + e.getMessage());
        }
    }
    public boolean isRunning(){
        return isRunning;
    }

}
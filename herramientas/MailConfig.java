package es.daw.proyectoDAW.herramientas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // Configuración del servidor SMTP de Mailtrap
        mailSender.setHost("sandbox.smtp.mailtrap.io");
        mailSender.setPort(2525);
        mailSender.setUsername("b4b0fe3c79b9b0");  // Tu usuario de Mailtrap
        mailSender.setPassword("cfbf8bfff385e6");  // Tu contraseña de Mailtrap

        // Configuraciones adicionales
        mailSender.getJavaMailProperties().put("mail.transport.protocol", "smtp");
        mailSender.getJavaMailProperties().put("mail.smtp.auth", "true");
        mailSender.getJavaMailProperties().put("mail.smtp.starttls.enable", "true");
        mailSender.getJavaMailProperties().put("mail.debug", "true");  // Puedes quitarlo en producción

        return mailSender;
    }
}

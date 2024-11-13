package es.daw.proyectoDAW.herramientas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        
        // Configuración del servidor SMTP (usando Gmail como ejemplo)
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("profesorprimero42@gmail.com");  
        mailSender.setPassword("minimates123");       
        
        // Configuraciones adicionales
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");  // Habilitar TLS
        props.put("mail.debug", "true");                 // Para depuración (puedes quitarlo en producción)

        return mailSender;
    }
}

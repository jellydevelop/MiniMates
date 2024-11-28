package es.daw.proyectoDAW.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.daw.proyectoDAW.modelo.EmailRequest;
import es.daw.proyectoDAW.modelo.Usuario;
import es.daw.proyectoDAW.repositorio.RepositorioUsuario;
import es.daw.proyectoDAW.servicio.IFServicioEmail;

@RestController
public class ControladorEmail {

    @Autowired
    private IFServicioEmail servicioEmail;

    @Autowired
    private RepositorioUsuario repoUsuario;

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/enviarEmail")
    public ResponseEntity<String> enviarEmail(@RequestBody EmailRequest emailRequest) {
        // Validar datos de entrada
        if (emailRequest.getEmailAlumno() == null || emailRequest.getEmailAlumno().isEmpty() ||
            emailRequest.getNombreTutor() == null || emailRequest.getNombreTutor().isEmpty() ||
            emailRequest.getCuerpoMensaje() == null || emailRequest.getCuerpoMensaje().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Todos los campos son obligatorios.");
        }

        // Buscar el profesor correspondiente a la letra de clase
        Usuario profesor = repoUsuario.findByRolUsuarioAndLetraClase("profesor", emailRequest.getLetraClase());
        if (profesor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profesor no encontrado para la letra de clase.");
        }

        // Crear el correo
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(emailRequest.getEmailAlumno()); // Remitente: email del alumno
        mail.setTo(profesor.getMailUsuario()); // Destinatario: email del profesor
        mail.setSubject("Consulta de " + emailRequest.getNombreTutor()); // Asunto del correo
        mail.setText(emailRequest.getCuerpoMensaje()); // Cuerpo del mensaje

        try {
            // Enviar el correo con Mailtrap
            mailSender.send(mail);
            return ResponseEntity.ok("Correo enviado correctamente.");
        } catch (MailException e) {
            // Capturar posibles errores durante el envío
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al enviar el correo: " + e.getMessage());
        }
    }
}



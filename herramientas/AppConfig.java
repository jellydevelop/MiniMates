package es.daw.proyectoDAW.herramientas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import es.daw.proyectoDAW.servicio.IFServicioEmail;
import es.daw.proyectoDAW.servicio.ServicioEmail;

public class AppConfig {
	@Bean
	public IFServicioEmail servicioEmail() {
		return new ServicioEmail(); // Retorna la implementación
	}

}

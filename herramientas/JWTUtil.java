package es.daw.proyectoDAW.herramientas;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;




@Component
public class JWTUtil {
	
	/// mi clave segura pra firmar con autenticidad
		@Value("${jwt.secret}")
			private String secretKey;
		

    
    // Convertimos la clave secreta a un objeto Key
    private Key obtenerClaveSecreta() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
    
    // Método para obtener el email (o subject) desde el token
    public String obtenerEmailDesdeToken(String token) {
    	
        Claims claims = Jwts.parserBuilder()  // Utiliza parserBuilder()
                .setSigningKey(obtenerClaveSecreta())  // Clave de firma
                .build()  // Construimos el parser
                .parseClaimsJws(token)  // Parseamos el token
                .getBody();  // Obtenemos el cuerpo del token, donde están los claims

        return claims.getSubject();  // Suponiendo que el email está en el "subject"
    }
}

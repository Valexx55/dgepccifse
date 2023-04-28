package edu.dgepc.alumnos.security;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenUtil {
	
	//SECRETO PARA FIRMA - clave privada -256 BITS 32 bytes
	
	//DURACIÓN SEGUNDOS LONG
	private final static Long ACCES_TOKEN_TIME_SECONDS_VAL = 60L;
	//private final static Long ACCES_TOKEN_TIME_SECONDS_VAL=2_592_000L; //30 días

	private final static String ACCES_TOKEN_SECRET = "0b385dd964fa4eef81af491ab8f0cb2d";
	
	//crear TOKEN
	public static String createToken (String nombre, String email)
	{
		String token_nuevo = null;
		
			long expiration_time = ACCES_TOKEN_TIME_SECONDS_VAL * 1000;
			Date expiration_date = new Date(System.currentTimeMillis() + expiration_time);
			
			Map<String, Object> extra = new HashMap<>();
			extra.put("nombre", nombre);
			
			token_nuevo =  Jwts.builder()
				.setSubject(email)
				.setExpiration(expiration_date)
				.setHeaderParam("typ", "JWT")
				.addClaims(extra)
				.signWith(Keys.hmacShaKeyFor(ACCES_TOKEN_SECRET.getBytes()))
				.compact();
			
			System.out.println("TOKEN NUEVO generado = " + token_nuevo);
		
		return token_nuevo;
		
	}
	
	//validar TOKEN
	public static UsernamePasswordAuthenticationToken getAuthentication (String token)
	{
		UsernamePasswordAuthenticationToken usuarioConToken = null;
		
		//intento extrar del token el body (payload)
		
		try {
			Claims claims = Jwts
							.parserBuilder()
							.setSigningKey(ACCES_TOKEN_SECRET.getBytes())
							.build()
							.parseClaimsJws(token)
							.getBody();
			
			String email = claims.getSubject();
			
			usuarioConToken = new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
					
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return usuarioConToken;
		
	}

}

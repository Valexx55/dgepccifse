package edu.dgepc.alumnos.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FiltroJWTAutenticacion extends UsernamePasswordAuthenticationFilter {
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		Authentication authentication = null;
		
		try
		{
			CredencialesAutenticacion credenciales = new ObjectMapper().readValue(request.getReader(), CredencialesAutenticacion.class);
			
			UsernamePasswordAuthenticationToken unpat = new UsernamePasswordAuthenticationToken
					(credenciales.getEmail(), credenciales.getPassword(), Collections.emptyList());
			
			authentication = getAuthenticationManager().authenticate(unpat);
		
		} catch (Exception e) {
			AuthenticationException e1 = new AuthenticationCredentialsNotFoundException("fallo godo, usuario no autenticado");
			throw e1;
		}
		return authentication;
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("AUTENTICACIÓN EXITOSA");
		DetalleUsuario detalleUsuario = (DetalleUsuario) authResult.getPrincipal();
		//creo el token con el nombre y el email
		String tokennuevo = TokenUtil.createToken(detalleUsuario.getNombre(), detalleUsuario.getUsername());
		
		response.addHeader("Authorization", "Bearer "+ tokennuevo);
		response.getWriter().flush();
		
		super.successfulAuthentication(request, response, chain, authResult);
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("AUTENTICACIÓN FALLIDA");
		super.unsuccessfulAuthentication(request, response, failed);
	}

}

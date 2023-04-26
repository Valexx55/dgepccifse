package edu.dgepc.alumnos.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//ControllerAdvice "SUMIDERO de EXCEPCIONES- Gestión Centralizada" POA
@RestControllerAdvice(basePackages = {"edu.dgepc.alumnos"})//si ocurre una expcecion en este paquete(sub) me la mandas aquí
public class GestionExcepciones {

	//org.springframework.dao.EmptyResultDataAccessException:
	//con esta anotación, le indico a Spring que este metodo, es el que gestiona este tipo de fallo
	@ExceptionHandler(org.springframework.dao.EmptyResultDataAccessException.class)
	public ResponseEntity<?> idAlumnoBorradoNoExiste (EmptyResultDataAccessException excepcion)
	{
		ResponseEntity<?> responseEntity = null;
		String mensaje_error = null;
			
			mensaje_error = "Error borrando un alumno por id " +excepcion.getMessage();
			responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje_error);
		
		return responseEntity;
	}
	
	
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<?> tratamientoExcepcionGenerica (Throwable excepcion)
	{
		ResponseEntity<?> responseEntity = null;
		String mensaje_error = null;
			
			mensaje_error = "Ha ocurrido un fallo " +excepcion.getMessage();
			responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje_error);
		
		return responseEntity;
	}
	
	
}

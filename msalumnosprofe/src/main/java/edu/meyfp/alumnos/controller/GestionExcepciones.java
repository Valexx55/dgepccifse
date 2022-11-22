package edu.meyfp.alumnos.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 
 * Esta clase actua como sumidero de errores
 * 
 * La gestión de la excepciones de nuestra app se centralice aquí
 * 
 * CUALQUIER error/expceción que ocurra en nuestro MS, derivará aquí
 *
 */

//oye Spring, todas las excepciones de este paquete, me las mandas aquí
@RestControllerAdvice (basePackages = {"edu.meyfp.alumnos"})
public class GestionExcepciones {
	
	//debo definir tantos métodos como tipos de fallo/excepción quiera gestionar
	

	@ExceptionHandler(org.springframework.dao.EmptyResultDataAccessException.class)
	public ResponseEntity<?> errorAlBorrarIdQueNoExiste (EmptyResultDataAccessException e)
	{
		ResponseEntity<?> responseEntity = null;
		String str_exception = null;
		
			str_exception = "ERROR EN LA PETICIÓN DELETE "+ e.getMessage();
			responseEntity = ResponseEntity.internalServerError().body(str_exception);
	
		return responseEntity;
	}
	
	
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<?> erroGenerico (Throwable e)
	{
		ResponseEntity<?> responseEntity = null;
		String str_exception = null;
		
			str_exception = "ERROR GENÉRICO "+ e.getMessage();
			responseEntity = ResponseEntity.internalServerError().body(str_exception);
	
		return responseEntity;
	}
	
}

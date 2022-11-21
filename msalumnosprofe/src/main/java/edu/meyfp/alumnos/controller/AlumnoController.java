package edu.meyfp.alumnos.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.meyfp.alumnos.repository.entity.Alumno;

//LISTAR TODOS LOS ALUMNOS - GET
//LISTAR UN ALUMNO POR SU ID - GET 
//BORRAR UN ALUMNO - DELETE 
//CREAR UN ALUMMNO - POST
//ACTUALIZARLO - PUT


@RestController
@RequestMapping("/alumno")
public class AlumnoController {
	
	
	@GetMapping("/obtener-alumno-test") //GET http://localhost:8081/alumno/obtener-alumno-test
	public Alumno obtenerAlumnoTest()
	{
		Alumno alumno = null;
		
			//ESTADO TRANSIENT - objeto sin relación con el Contexto de Persisntecia
			alumno = new Alumno(3l, "OSCAR", "PORTELA", "oscar@correo.es", 18, new Date());
		
		
		return alumno;
		
	}
	

}

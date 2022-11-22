package edu.meyfp.alumnos.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.meyfp.alumnos.repository.entity.Alumno;
import edu.meyfp.alumnos.service.AlumnoService;

//LISTAR TODOS LOS ALUMNOS - GET x
//LISTAR UN ALUMNO POR SU ID - GET x
//BORRAR UN ALUMNO - DELETE x
//CREAR UN ALUMMNO - POST x
//ACTUALIZARLO - PUT


@RestController
@RequestMapping("/alumno")
public class AlumnoController {
	
	@Autowired
	AlumnoService alumnoService;
	
	@GetMapping("/obtener-alumno-test") //GET http://localhost:8081/alumno/obtener-alumno-test
	public Alumno obtenerAlumnoTest()
	{
		Alumno alumno = null;
		
			//ESTADO TRANSIENT - objeto sin relación con el Contexto de Persisntecia
			alumno = new Alumno(3l, "OSCAR", "PORTELA", "oscar@correo.es", 18, new Date());
		
		
		return alumno;
		
	}
	
	@GetMapping //GET http://localhost:8081/alumno/
	public ResponseEntity<?> listarAlumnos()
	{
		ResponseEntity<?> responseEntity = null;
		Iterable<Alumno> lista_alumnos = null;
		
			lista_alumnos = this.alumnoService.findAll();
			responseEntity = ResponseEntity.ok(lista_alumnos);
		
		return responseEntity;//representa el HTTP
	}

	
	
	@GetMapping("/{id}") //GET http://localhost:8081/alumno/3
	public ResponseEntity<?> listarAlumnoPorId(@PathVariable Long id)
	{
		ResponseEntity<?> responseEntity = null;
		Optional<Alumno> optional_alumno = null;
		
			optional_alumno = this.alumnoService.findById(id);
			if (optional_alumno.isPresent())
			{
				//exsitías un alumno con ese id
				Alumno alumno_leido = optional_alumno.get();//200
				responseEntity = ResponseEntity.ok(alumno_leido);
			} else {
				responseEntity = ResponseEntity.noContent().build();//204
			}
					
		
		return responseEntity;//representa el HTTP
	}
	
	
	@DeleteMapping("/{id}") //GET http://localhost:8081/alumno/3
	public ResponseEntity<?> borrarAlumnoPorId(@PathVariable Long id)
	{
		ResponseEntity<?> responseEntity = null;
		
			this.alumnoService.deleteById(id);
			responseEntity = ResponseEntity.ok().build();
			
		
		return responseEntity;//representa el HTTP
	}
	
	
	@PostMapping //POST http://localhost:8081/alumno/
	public ResponseEntity<?> insertarAlumno(@RequestBody Alumno alumno)
	{
		ResponseEntity<?> responseEntity = null;
		Alumno alumno_insertado = null;
		
			alumno_insertado = this.alumnoService.save(alumno);
			responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(alumno_insertado);//201
		
		return responseEntity;//representa el HTTP
	}
	
	
	@PutMapping("/{id}") //PUT http://localhost:8081/alumno/3
	public ResponseEntity<?> actualizarAlumno(@RequestBody Alumno alumno, @PathVariable Long id)
	{
		ResponseEntity<?> responseEntity = null;
		Optional<Alumno> optional_alumno = null;
		
			optional_alumno = this.alumnoService.update(alumno, id);
			if (optional_alumno.isPresent())
			{
				//exsitías un alumno con ese id
				Alumno alumno_modificado = optional_alumno.get();//200
				responseEntity = ResponseEntity.ok(alumno_modificado);
			} else {
				responseEntity = ResponseEntity.notFound().build();//404
			}
					
		
		return responseEntity;//representa el HTTP
	}
}

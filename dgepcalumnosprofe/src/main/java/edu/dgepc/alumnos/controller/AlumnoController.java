package edu.dgepc.alumnos.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.dgepc.alumnos.model.Alumno;
import edu.dgepc.alumnos.service.AlumnoService;

@RestController // automaticamente "recibimos" y devolvemos JSON
@RequestMapping("/alumno")
public class AlumnoController {

	@Autowired
	AlumnoService alumnoService;

	// LEER TODOS LOS ALUMNOS - get
	// GET http://localhost:8081/alummo
	@GetMapping
	public ResponseEntity<?> listarAlumnos() // ResponseEntinty el paquete HTTP de vuelta
	{
		ResponseEntity<?> responseEntity = null;
		Iterable<Alumno> listaAlumnos = null;

			listaAlumnos = this.alumnoService.findAll();
			responseEntity = ResponseEntity.ok(listaAlumnos);

		return responseEntity;

	}

	// LEER UN ALUMNO -get
	/// GET http://localhost:8081/alummo/id ///// por ej: GET
	// http://localhost:8081/alummo/8
	@GetMapping("/{id}")
	public ResponseEntity<?> listarAlumnoPorID(@PathVariable Long id) // ResponseEntinty el paquete HTTP de vuelta
	{
		ResponseEntity<?> responseEntity = null;
		Optional<Alumno> optionalAlumno = null;

			optionalAlumno = this.alumnoService.findById(id);
			// si está el id por el que pregunta, le doy un 200 y el almuno
			if (optionalAlumno.isPresent()) {
				Alumno alumno_leido = optionalAlumno.get();
				responseEntity = ResponseEntity.ok(alumno_leido);
			} else {
				responseEntity = ResponseEntity.noContent().build();// .header("saludo", "HOLA").build();
			}

		return responseEntity;

	}

	// BORRAR UN ALUMNO - delete
	///// DELETE http://localhost:8081/alummo/id ///// por ej: DELETE
	// http://localhost:8081/alummo/8
	@DeleteMapping("/{id}")
	public ResponseEntity<?> borrarAlumnoPorID(@PathVariable Long id) // ResponseEntinty el paquete HTTP de vuelta
	{
		ResponseEntity<?> responseEntity = null;

			this.alumnoService.deleteById(id);
			responseEntity = ResponseEntity.ok().build();

		return responseEntity;

	}

	// MODIFICAR UN ALUMNO - put
	/// PUT http://localhost:8081/alummo/id ///// por ej: DELETE
	// http://localhost:8081/alummo/8
	@PutMapping("/{id}")
	public ResponseEntity<?> modificarAlumnoPorID(@RequestBody Alumno alumno, @PathVariable Long id) // ResponseEntinty
																										// vuelta
	{
		ResponseEntity<?> responseEntity = null;
		Optional<Alumno> optionalAlumno = null;

			optionalAlumno = this.alumnoService.update(alumno, id);
	
			if (optionalAlumno.isPresent()) {
				Alumno alumno_leido = optionalAlumno.get();
				responseEntity = ResponseEntity.ok(alumno_leido);
			} else {
				responseEntity = ResponseEntity.notFound().build();// .header("saludo", "HOLA").build();
			}

		return responseEntity;

	}
	
	private ResponseEntity<?> obtenerErrores(BindingResult bindingResult)
	{
		ResponseEntity<?> responseEntity = null;
		List<ObjectError> listaErrores = null;
		
			listaErrores = bindingResult.getAllErrors();
			//TODO: log de los fallos
			responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listaErrores);
		
		
		return responseEntity;
	}
	// CREAR UN ALUMNO - post

	/// POST http://localhost:8081/alummo ///// por ej: POST
	/// http://localhost:8081/alummo
	@PostMapping
	public ResponseEntity<?> crearAlumno(@Valid @RequestBody Alumno alumno, BindingResult bindingResult ) // ResponseEntinty el paquete HTTP de vuelta
	{
		ResponseEntity<?> responseEntity = null;
		Alumno alumno_nuevo = null;
		
			//si el alumno es válido
				//INSERTAR
			//si no, mensaje de ERROR 400
		
			if (bindingResult.hasErrors())
			{
				//crearUnMensajeDeErrorComoRespuesta
				responseEntity = obtenerErrores(bindingResult);
			} else {
				//sin errores --> insertar
				alumno_nuevo = this.alumnoService.save(alumno);
				responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(alumno_nuevo);
				
			}
			

		return responseEntity;

	}

	// GET http://localhost:8081/alummo/obtener-alumno-test
	@GetMapping("/obtener-alumno-test")
	public Alumno obtenerAlumnoTest() {
		Alumno alumno = null; // transiente

			alumno = new Alumno(5l, "Jesus", "Gutierrez", "jguti@correo.es", new Date(), 3);

		return alumno;
	}

}

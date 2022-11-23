package edu.meyfp.alumnos.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	Logger logger = LoggerFactory.getLogger(AlumnoController.class);
	
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
		
			logger.debug("Entrando en listarAlumnos()" );
			lista_alumnos = this.alumnoService.findAll();
			responseEntity = ResponseEntity.ok(lista_alumnos);
			logger.debug("Saliendo en listarAlumnos() "  + lista_alumnos);
		
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
	
	
	private ResponseEntity<?> obtenerErrores (BindingResult bindingResult)
	{
		ResponseEntity<?> responseEntity = null;
		List<ObjectError> listaErrores = null;
		
		
				listaErrores = bindingResult.getAllErrors();
				responseEntity = ResponseEntity.badRequest().body(listaErrores);
				listaErrores.forEach(objeto_error -> {
					logger.error(objeto_error.toString());
				});
		
		return responseEntity;
	}
	
	@PostMapping //POST http://localhost:8081/alumno/
	public ResponseEntity<?> insertarAlumno(@Valid @RequestBody Alumno alumno, BindingResult bindingResult)
	{
		ResponseEntity<?> responseEntity = null;
		Alumno alumno_insertado = null;
		
			if (bindingResult.hasErrors())
			{
				logger.debug("el alumno trae errores -POST" );
				responseEntity = obtenerErrores(bindingResult);
			} else {
				logger.debug("el alumno ha superado la validación POST" );
				alumno_insertado = this.alumnoService.save(alumno);
				responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(alumno_insertado);//201
			}
			
		
		return responseEntity;//representa el HTTP
	}
	
	
	@PutMapping("/{id}") //PUT http://localhost:8081/alumno/3
	public ResponseEntity<?> actualizarAlumno(@Valid @RequestBody Alumno alumno, BindingResult bindingResult, @PathVariable Long id)
	{
		ResponseEntity<?> responseEntity = null;
		Optional<Alumno> optional_alumno = null;
		
			if (bindingResult.hasErrors())
			{
				logger.debug("el alumno trae errores - PUT" );
				responseEntity = obtenerErrores(bindingResult);
			} else {
				logger.debug("el alumno ha superado la validación - PUT" );
				optional_alumno = this.alumnoService.update(alumno, id);
				if (optional_alumno.isPresent())
				{
					//exsitías un alumno con ese id
					Alumno alumno_modificado = optional_alumno.get();//200
					responseEntity = ResponseEntity.ok(alumno_modificado);
				} else {
					responseEntity = ResponseEntity.notFound().build();//404
				}
			}
			
					
		
		return responseEntity;//representa el HTTP
	}
	
	
	@GetMapping("/listar-alumnos-rango-edad") //GET http://localhost:8081/alumno/listar-alumnos-rango-edad?edad1=17&edad2=38
	public ResponseEntity<?> listarAlumnosRangoEdad(@RequestParam(name = "edad1") int edad1, @RequestParam(name = "edad2") int edad2)
	{
		ResponseEntity<?> responseEntity = null;
		Iterable<Alumno> lista_alumnos = null;
		
			lista_alumnos = this.alumnoService.findByEdadBetween(edad1, edad2);
			responseEntity = ResponseEntity.ok(lista_alumnos);
		
		
		return responseEntity;
		
	}
	
	@GetMapping("/patron/{nombre}") //GET http://localhost:8081/alumno/patron/
	public ResponseEntity<?> listarAlumnosPatron(@PathVariable String nombre)
	{
		ResponseEntity<?> responseEntity = null;
		Iterable<Alumno> lista_alumnos = null;
		
			logger.debug("Entrando en listarAlumnosPatron()");
			lista_alumnos = this.alumnoService.findByNombreLike(nombre);
			responseEntity = ResponseEntity.ok(lista_alumnos);
			logger.debug("Saliendo de listarAlumnosPatron()");
			
		return responseEntity;//representa el HTTP
	}
	
	
	@GetMapping("/busquedaPorNombreOApellidoJQLP/{patron}") //GET http://localhost:8081/alumno/busquedaPorNombreOApellidoJQLP/pepe
	public ResponseEntity<?> busquedaPorNombreOApellidoJQLP(@PathVariable String patron)
	{
		ResponseEntity<?> responseEntity = null;
		Iterable<Alumno> lista_alumnos = null;
		
			logger.debug("Entrando en busquedaPorNombreOApellidoJQLP()");
			lista_alumnos = this.alumnoService.buscarPorNombreOApellidoJQPL(patron);
			responseEntity = ResponseEntity.ok(lista_alumnos);
			logger.debug("Saliendo de busquedaPorNombreOApellidoJQLP()");
			
		return responseEntity;//representa el HTTP
	}
	
	@GetMapping("/busquedaPorNombreOApellidoNativa/{patron}") //GET http://localhost:8081/alumno/busquedaPorNombreOApellidoNativa/pepe
	public ResponseEntity<?> busquedaPorNombreOApellidoNativa(@PathVariable String patron)
	{
		ResponseEntity<?> responseEntity = null;
		Iterable<Alumno> lista_alumnos = null;
		
			logger.debug("Entrando en busquedaPorNombreOApellidoNativa()");
			lista_alumnos = this.alumnoService.buscarPorNombreOApellidoNativa(patron);
			responseEntity = ResponseEntity.ok(lista_alumnos);
			logger.debug("Saliendo de busquedaPorNombreOApellidoNativa()");
			
		return responseEntity;//representa el HTTP
	}
	
	@GetMapping("/procedimientoAlumnosAltaHoy") //GET http://localhost:8081/alumno/procedimientoAlumnosAltaHoy
	public ResponseEntity<?> procedimientoAlumnosAltaHoy()
	{
		ResponseEntity<?> responseEntity = null;
		Iterable<Alumno> lista_alumnos = null;
		
			logger.debug("Entrando en procedimientoAlumnosAltaHoy()");
			lista_alumnos = this.alumnoService.procedimientoAlumnosAltaHoy();
			responseEntity = ResponseEntity.ok(lista_alumnos);
			logger.debug("Saliendo de procedimientoAlumnosAltaHoy()");
			
		return responseEntity;//representa el HTTP
	}
	
	@GetMapping("/procedimientoEstadisticosEdad") //GET http://localhost:8081/alumno/procedimientoEstadisticosEdad
	public ResponseEntity<?> procedimientoEstadisticosEdad()
	{
		ResponseEntity<?> responseEntity = null;
		Map<String, Number> valores_edad = null;
		
			logger.debug("Entrando en procedimientoEstadisticosEdad()");
			valores_edad = this.alumnoService.procedimientosEstadisticosEdad();
			responseEntity = ResponseEntity.ok(valores_edad);
			logger.debug("Saliendo de procedimientoEstadisticosEdad()");
			
		return responseEntity;//representa el HTTP
	}
	
	@GetMapping("/procedmientoBuscarNombreComo/{patron}") //GET http://localhost:8081/alumno/procedmientoBuscarNombreComo/pepe
	public ResponseEntity<?> procedmientoBuscarNombreComo(@PathVariable String patron)
	{
		ResponseEntity<?> responseEntity = null;
		Iterable<Alumno> lista_alumnos = null;
		
			logger.debug("Entrando en procedmientoBuscarNombreComo()");
			lista_alumnos = this.alumnoService.procedimientoAlumnosNombreComo(patron);
			responseEntity = ResponseEntity.ok(lista_alumnos);
			logger.debug("Saliendo de procedmientoBuscarNombreComo()");
			
		return responseEntity;//representa el HTTP
	}
}

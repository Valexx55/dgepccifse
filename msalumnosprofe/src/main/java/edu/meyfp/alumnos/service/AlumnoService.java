package edu.meyfp.alumnos.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import edu.meyfp.alumnos.repository.entity.Alumno;

public interface AlumnoService {
	
	public Iterable<Alumno> findAll();
	
	public Optional<Alumno> findById(Long id);
	
	public Alumno save (Alumno alumno);
	
	public Alumno save2 (Alumno alumno); //alternativa Óscar Portela
	
	public void deleteById(Long id);
	
	public Optional<Alumno> update (Alumno alumno, Long id);
	
	public Iterable<Alumno> findByEdadBetween (int edad_min, int edad_max);
	
	public Iterable<Alumno> findByNombreLike (String nombre);
	
	public Iterable<Alumno> buscarPorNombreOApellidoJQPL (String patron);
	
	public Iterable<Alumno> buscarPorNombreOApellidoNativa (String nombre);
	
	
	public Iterable<Alumno> procedimientoAlumnosAltaHoy ();
	
	public Map<String, Number> procedimientosEstadisticosEdad ();

	public Iterable<Alumno> procedimientoAlumnosNombreComo (String patron);

}

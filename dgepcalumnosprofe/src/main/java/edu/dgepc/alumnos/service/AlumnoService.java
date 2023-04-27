package edu.dgepc.alumnos.service;

import java.util.Optional;

import edu.dgepc.alumnos.model.Alumno;
import edu.dgepc.alumnos.model.FraseChiquito;

public interface AlumnoService {
	
	//consultar todos
	public Iterable<Alumno> findAll();
	//consultar por ID
	public Optional<Alumno> findById (Long id);
	//guardar Alumno
	public Alumno save (Alumno alumno);
	//borrar alumno
	public void deleteById (Long id);
	//modificar
	public Optional<Alumno> update (Alumno alumno, Long id);
	//consultar alumnos en un rango de edad
	public Iterable<Alumno> findByEdadBetween(int edad_min, int edad_max);
	//consultar por nombre o apellido 
	public Iterable<Alumno> busquedaPorNombreOApellidoNativa (String patron);
	
	//
	public Optional<FraseChiquito> obtenerFraseAleatoriaChiquito ();

}

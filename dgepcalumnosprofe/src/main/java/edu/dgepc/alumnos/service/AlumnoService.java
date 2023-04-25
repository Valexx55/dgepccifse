package edu.dgepc.alumnos.service;

import java.util.Optional;

import edu.dgepc.alumnos.model.Alumno;

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

}

package edu.meyfp.alumnos.service;

import java.util.Optional;

import edu.meyfp.alumnos.repository.entity.Alumno;

public interface AlumnoService {
	
	public Iterable<Alumno> findAll();
	
	public Optional<Alumno> findById(Long id);
	
	public Alumno save (Alumno alumno);
	
	public Alumno save2 (Alumno alumno); //alternativa Óscar Portela
	
	public void deleteById(Long id);
	
	public Optional<Alumno> update (Alumno alumno, Long id);

}

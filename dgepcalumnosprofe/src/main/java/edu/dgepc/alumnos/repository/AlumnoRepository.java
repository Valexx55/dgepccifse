package edu.dgepc.alumnos.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.dgepc.alumnos.model.Alumno;

//@Repository
public interface AlumnoRepository extends CrudRepository<Alumno, Long>{
	
	//KEYWORD Queries - Consultas por palabras Clave
	
		//obtener un listado con los alumos comprendidos en un rango de edad
		public Iterable<Alumno> findByEdadBetween (int edad_min, int edad_max);
		
	//CONSULTA NATIVA

		@Query(value = "SELECT * FROM alumnos a WHERE a.nombre LIKE %?1% OR a.apellido LIKE %?1%", nativeQuery = true)
		public Iterable<Alumno> busquedaPorNombreOApellidoNativa (String patron);
		
	//JPQL @query
	//Procediminetos 
	//CRITERIA API	
}

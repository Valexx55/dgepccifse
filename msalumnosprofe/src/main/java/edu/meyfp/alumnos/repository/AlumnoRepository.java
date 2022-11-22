package edu.meyfp.alumnos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.meyfp.alumnos.repository.entity.Alumno;

/**
 * en esta clase se encapaul TODA LA interacción con la base de datos
 * @author usuario
 *
 */
@Repository
public interface AlumnoRepository extends CrudRepository<Alumno, Long> {
	
	//ABMC - CRUD
	
	//KEYWORD Query - Consultas por palabra
	
		//1 OBTENER EL LISTADO DE ALUMNOS EN UN RANGO DE EDAD
		public Iterable<Alumno> findByEdadBetween (int edad_min, int edad_max);

}

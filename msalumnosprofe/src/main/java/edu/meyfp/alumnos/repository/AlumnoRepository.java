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

}

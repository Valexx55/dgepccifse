package edu.dgepc.alumnos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.dgepc.alumnos.model.Alumno;

//@Repository
public interface AlumnoRepository extends CrudRepository<Alumno, Long>{

}

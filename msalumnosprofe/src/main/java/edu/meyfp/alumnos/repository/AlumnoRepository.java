package edu.meyfp.alumnos.repository;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.meyfp.alumnos.repository.entity.Alumno;

/**
 * en esta clase se encapaul TODA LA interacción con la base de datos
 * @author usuario
 *
 */
@Repository
public interface AlumnoRepository extends PagingAndSortingRepository<Alumno, Long> {
//public interface AlumnoRepository extends CrudRepository<Alumno, Long> {
	
	//ABMC - CRUD
	
	//KEYWORD Query - Consultas por palabra
	
		//1 OBTENER EL LISTADO DE ALUMNOS EN UN RANGO DE EDAD
		public Iterable<Alumno> findByEdadBetween (int edad_min, int edad_max);
		
		//TODO 2 OBTENER EL LISTADO DE ALUMNOS CUYO NOMBRE CUMPLA UN PATRÓN
		public Iterable<Alumno> findByNombreLike (String nombre);
		
		//ejemplo por completar //TODO la capa de servicio y el controlador
		public Page<Alumno> findByEdadBetween (int edad_min, int edad_max, Pageable p);
		
		
		
	//JPQL "Agnóstico" - JPA
		
		//3 BÚSQUEDAD DE ALUMNOS POR NOMBRE O APELLIDO
		@Query("SELECT a FROM Alumno a WHERE a.nombre LIKE %?1% or a.apellido LIKE %?1%")
		public Iterable<Alumno> buscarPorNombreOApellidoJQPL (String nombre);
		
		//3.1 BÚSQUEDAD DE ALUMNOS POR NOMBRE O APELLIDO PAGINADO
		@Query("SELECT a FROM Alumno a WHERE a.nombre LIKE %?1% or a.apellido LIKE %?1%")
		public Page<Alumno> buscarPorNombreOApellidoJQPLPaginado (String nombre, Pageable pageable);
		
	//NATIVAS - SQL Adaptado a la base de datos subyacente
		@Query(value="SELECT * FROM alumnos a WHERE a.nombre LIKE %?1% or a.apellido LIKE %?1%", nativeQuery = true)
		public Iterable<Alumno> buscarPorNombreOApellidoNativa (String nombre);
		
		/**
		 * PARA OBTENER DATOS PARCIALES (NO EL ALUMNO ENTERO)
		 * 
		 * interface NombreYApellidosAlumno 
	{
		String getNombre();
		String getApellidos();
	}

	@Transactional(readOnly=true)
	Optional<NombreYApellidosAlumno> findFirstNombreYApellidosAlumno(Long idAlumno);
		 */
		
	//PROCEDIMIENTOS ALMACENADOS
		
		//1 CREO EL PROCEDIMIENTO EN BASE DE DATOS x 
		//2 LO REFERENCIA DESDE LA ENTIDAD ALUMNO @NamedStoredProcedureQuery 
		//3 DEFINIR EN EL REPOSITORIO, LOS MÉTODOS (haciendo refenrencia al punto 2)
		
		//selección de alumnos registros hoy (SIN PARAMS)
		@Procedure(name = "Alumno.alumnosRegistradosHoy")
		public Iterable<Alumno> procedimientoAlumnosAltaHoy ();
		
		//obtener los estadísitcos de la edad : Máximo, Min, la media (OUT)
		@Procedure(name = "Alumno.alumnosEdadMediaMinMax")
		public Map<String, Number> procedimientosEstadisticosEdad (int edadmax, int edadmin, float edadmedia);
		
		//obtener los alumnos cuyo nombre cumpla un patrón (IN)
		@Procedure(name = "Alumno.alumnosNombreComo")
		public Iterable<Alumno> procedimientoAlumnosNombreComo (@Param("patron") String patron);
		
		
	//CRITERIA API x
		

}

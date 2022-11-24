package edu.meyfp.alumnos.service;

import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import edu.meyfp.alumnos.controller.AlumnoController;
import edu.meyfp.alumnos.dto.FraseChuckNorris;
import edu.meyfp.alumnos.repository.AlumnoRepository;
import edu.meyfp.alumnos.repository.entity.Alumno;

@Service
public class AlumnoServiceImp implements AlumnoService{
	
	@Autowired
	AlumnoRepository alumnoRepository;
	
	@Autowired
	EntityManager entityManager;
	

	//**
	/*@Transactional es la anotación "estrella" de la POA programación orientada a Aspectos
	 * hace que se inicie la transaccion antes de la ejecución del método así anotado
	 * si no hay ningún fallo, al final del método, se ejcuta un commit
	 * si lo hay, se ejecuta un rollback automáticamente*/
	/***/
	
	
	@Override
	@Transactional (readOnly = true)
	public Iterable<Alumno> findAll() {
		
		return this.alumnoRepository.findAll();
	}

	@Override
	@Transactional (readOnly = true)
	public Optional<Alumno> findById(Long id) {
		
		return this.alumnoRepository.findById(id);
	}

	@Override
	@Transactional//readonly false
	public Alumno save(Alumno alumno) {
		// TODO Auto-generated method stub
		Alumno alumno_nuevo = null;
		
			alumno_nuevo = alumnoRepository.save(alumno);
			//var nombre= "Vale";
			//nombre.charAt(4);
		
		return alumno_nuevo;
	}
	
	@Override
	@Transactional//readonly false
	public Alumno save2(Alumno alumno) {
		// TODO Auto-generated method stub
		
		//this.entityManager.getTransaction().begin();
		
		//long id_insertado = this.entityManager.
		
		return alumnoRepository.save(alumno);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		//this.alumnoRepository.existsById(id); //una opción para asegurarme de que existe un registro con ese id, pero aumenta la complejida
		
		this.alumnoRepository.deleteById(id);
		
	}

	@Override
	@Transactional
	public Optional<Alumno> update(Alumno alumno, Long id) {
		Optional<Alumno> oalumno = Optional.empty();
		
			//1 leer al alumno
			oalumno = this.alumnoRepository.findById(id);
			//si existe
			if (oalumno.isPresent())
			{
				Alumno alumno_leido = oalumno.get();//PERSISTENCE -DENTRO DE UNA TX
				//alumno_leido.setNombre(alumno.getNombre());
				BeanUtils.copyProperties(alumno, alumno_leido, "id", "creadoEn");//estos atributos quedan exlcuidos de la copia
				oalumno = Optional.of(alumno_leido);//"relleno el optional"
				//this.alumnoRepository.save(alumno_leido);
			}
				//modifico
		
		return oalumno;
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> findByEdadBetween(int edad_min, int edad_max) {
		
		return this.alumnoRepository.findByEdadBetween(edad_min, edad_max);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> findByNombreLike(String nombre) {
		return this.alumnoRepository.findByNombreLike("%"+nombre+"%");
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> buscarPorNombreOApellidoJQPL(String patron) {
		
		return this.alumnoRepository.buscarPorNombreOApellidoJQPL(patron);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> buscarPorNombreOApellidoNativa (String nombre){
		return this.alumnoRepository.buscarPorNombreOApellidoNativa(nombre);
	}

	@Override
	@Transactional //AUNQUE EL PROCEDIMIENTO NO MODIFIQUE LA BASE DE DATOS, HAY QUE DEJAR READ_ONLY A FALSE
	public Iterable<Alumno> procedimientoAlumnosAltaHoy() {
		
		return this.alumnoRepository.procedimientoAlumnosAltaHoy();
	}

	@Override
	@Transactional
	public Map<String, Number> procedimientosEstadisticosEdad() {

		return this.alumnoRepository.procedimientosEstadisticosEdad(0, 0, 0);//indico parametros ficción para que concuerde con la cabcera del procedimiento
	}

	@Override
	@Transactional(readOnly = false)
	public Iterable<Alumno> procedimientoAlumnosNombreComo(String patron) {
		
		return this.alumnoRepository.procedimientoAlumnosNombreComo("%"+patron+"%");
	}

	@Override
	@Transactional (readOnly = true)
	public Page<Alumno> findAll(Pageable pageable) {
		
		return this.alumnoRepository.findAll(pageable);
	}

	@Override
	@Transactional (readOnly = true)
	public Page<Alumno> buscarPorNombreOApellidoJQPLPaginado(String nombre, Pageable pageable) {
		
		return this.alumnoRepository.buscarPorNombreOApellidoJQPLPaginado(nombre, pageable);
	}

	@Override
	public Optional<FraseChuckNorris> obtenerFraseChuckAleatoria() {
		Optional<FraseChuckNorris> optional = Optional.empty();
		FraseChuckNorris fraseChuckNorris = null;
		RestTemplate restTemplate = null;
		
			restTemplate = new RestTemplate ();
			fraseChuckNorris = restTemplate.getForObject("https://api.chucknorris.io/jokes/random", FraseChuckNorris.class);
			optional = Optional.of(fraseChuckNorris);	
			
			
		return optional;
	}

}

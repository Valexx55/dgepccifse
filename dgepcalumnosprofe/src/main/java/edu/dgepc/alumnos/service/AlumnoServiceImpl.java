package edu.dgepc.alumnos.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.dgepc.alumnos.model.Alumno;
import edu.dgepc.alumnos.repository.AlumnoRepository;

@Service
public class AlumnoServiceImpl implements AlumnoService{
	
	@Autowired
	AlumnoRepository alumnoRepository;//acceso a datos

	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> findAll() {
		
		return this.alumnoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Alumno> findById(Long id) {
		// TODO Auto-generated method stub
		return this.alumnoRepository.findById(id);
	}

	@Override
	@Transactional//readonly a false
	public Alumno save(Alumno alumno) {
		// TODO Auto-generated method stub
		return this.alumnoRepository.save(alumno);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		this.alumnoRepository.deleteById(id);
		
	}

	@Override
	@Transactional
	public Optional<Alumno> update(Alumno alumno, Long id) {
		Optional<Alumno> optional = Optional.empty();//creo el optional, vacío
		// TODO Auto-generated method stub
		//1 leer el registro por el id
		
			Optional<Alumno> aoleido = this.alumnoRepository.findById(id);
			if (aoleido.isPresent()) //si está presente
			{//2 modificar los atributos (todos menos el id)
				//obtener del optional el registro recuperado
				 Alumno alumno_leido = aoleido.get();//PERSISTENCE
				 //alumno_leido.setNombre(alumno.getNombre());
				 //actualizo el registro con todo lo nuevo menos el id y el creado en
				 BeanUtils.copyProperties(alumno, alumno_leido, "id", "creadoEn");
				 //this.alumnoRepository.save(alumno_leido);
				 optional = Optional.of(alumno_leido);//"rellenamos el optional"
			}
		//si no,no hago nada
		return optional;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> findByEdadBetween(int edad_min, int edad_max) {

		return this.alumnoRepository.findByEdadBetween(edad_min, edad_max);
	}

	@Override
	public Iterable<Alumno> busquedaPorNombreOApellidoNativa(String patron) {
		
		return this.alumnoRepository.busquedaPorNombreOApellidoNativa(patron);
	}
	
	//hacerMatricula
	//try{
	
		//inscribir al alumno
		//hacer pago
		//actualizar el curso
	
	//SI SALE BIEN, HAGO COMMIT
     //} catch 
	//{
		//SI ALGO HA IDO MAL ROLLBACK
	//}
}

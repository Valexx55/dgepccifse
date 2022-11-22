package edu.meyfp.alumnos.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.meyfp.alumnos.controller.AlumnoController;
import edu.meyfp.alumnos.repository.AlumnoRepository;
import edu.meyfp.alumnos.repository.entity.Alumno;

@Service
public class AlumnoServiceImp implements AlumnoService{
	
	@Autowired
	AlumnoRepository alumnoRepository;
	

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
		return alumnoRepository.save(alumno);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
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

}

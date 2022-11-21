package edu.meyfp.alumnos.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.meyfp.alumnos.controller.AlumnoController;
import edu.meyfp.alumnos.repository.AlumnoRepository;
import edu.meyfp.alumnos.repository.entity.Alumno;

@Service
public class AlumnoServiceImp implements AlumnoService{
	
	@Autowired
	AlumnoRepository alumnoRepository;
	

	@Override
	public Iterable<Alumno> findAll() {
		
		return this.alumnoRepository.findAll();
	}

	@Override
	public Optional<Alumno> findById(Long id) {
		
		return this.alumnoRepository.findById(id);
	}

	@Override
	public Alumno save(Alumno alumno) {
		// TODO Auto-generated method stub
		return alumnoRepository.save(alumno);
	}

	@Override
	public void deleteById(Long id) {
		this.alumnoRepository.deleteById(id);
		
	}

	@Override
	public Optional<Alumno> update(Alumno alumno, Long id) {
		Optional<Alumno> oalumno = Optional.empty();
		
			//1 leer al alumno
			oalumno = this.alumnoRepository.findById(id);
			//si existe
			if (oalumno.isPresent())
			{
				Alumno alumno_leido = oalumno.get();
				alumno_leido.setNombre(alumno.getNombre());
				BeanUtils.copyProperties(alumno, alumno_leido, "id", "creadoEn");
				oalumno = Optional.of(alumno_leido);//"relleno el optional"
			}
				//modifico
		
		return oalumno;
	}

}

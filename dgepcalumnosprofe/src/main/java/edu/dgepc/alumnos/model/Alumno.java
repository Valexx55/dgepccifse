package edu.dgepc.alumnos.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name = "alumnos")
public class Alumno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//autoinc en mysql
	private Long id; //clave primaria
	
	@Size(min=3, max=5)
	private String nombre;
	
	@NotEmpty //que no sea null y que tenga longitud > 1
	private String apellido;
	
	@Email
	private String email;
	
	@Column(name = "creado_en")
	private Date creadoEn;
	
	@Min(0)
	@Max(130)
	private int edad;
	
	
	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@PrePersist//antes de insertarlo en bd, se ejecuta este método
	private void generarFechaCreacion ()
	{
		this.creadoEn = new Date();//obtener la fecha actual
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreadoEn() {
		return creadoEn;
	}

	public void setCreadoEn(Date creadoEn) {
		this.creadoEn = creadoEn;
	}

	public Alumno(Long id, String nombre, String apellido, String email, Date creadoEn, int edad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.creadoEn = creadoEn;
		this.edad = edad;
	}
	
	

	public Alumno() {
		super();
	}

	
	
	
	
}

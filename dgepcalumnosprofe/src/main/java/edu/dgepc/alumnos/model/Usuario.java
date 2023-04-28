package edu.dgepc.alumnos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//autoinc en mysql
	private Long id; //clave primaria
	
	private String nombre;
	
	@Column(unique = true)
	private String email;
	
	private String empresa;
	
	private String password;
	
	@Column(columnDefinition = "boolean default true")
	private Boolean activada;
	
	@Column(columnDefinition = "boolean default false")
	private Boolean bloqueada;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getActivada() {
		return activada;
	}

	public void setActivada(Boolean activada) {
		this.activada = activada;
	}

	public Boolean getBloqueada() {
		return bloqueada;
	}

	public void setBloqueada(Boolean bloqueada) {
		this.bloqueada = bloqueada;
	}

	public Usuario(Long id, String nombre, String email, String empresa, String password, Boolean activada,
			Boolean bloqueada) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.empresa = empresa;
		this.password = password;
		this.activada = activada;
		this.bloqueada = bloqueada;
	}

	public Usuario() {
		super();
	}
	
	
	

}

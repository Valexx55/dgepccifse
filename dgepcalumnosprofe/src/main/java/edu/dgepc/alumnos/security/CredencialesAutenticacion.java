package edu.dgepc.alumnos.security;


//clase que representa los datos recibidos por el controller
public class CredencialesAutenticacion {
	
	private String email;
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public CredencialesAutenticacion(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public CredencialesAutenticacion() {
		// TODO Auto-generated constructor stub
	}
	

}

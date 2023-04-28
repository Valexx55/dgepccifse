package edu.dgepc.alumnos.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import edu.dgepc.alumnos.model.Usuario;

public class DetalleUsuario implements UserDetails {
	
	private final Usuario usuario;
	
	public DetalleUsuario(Usuario usuario) {
		super();
		this.usuario = usuario;
	}

	
	//los permisos que goza este usuario / roles (delegamos en BD)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.emptyList();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return usuario.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return usuario.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return (!this.usuario.getBloqueada());
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.usuario.getActivada();
	}

	public String getNombre ()
	{
		return this.usuario.getNombre();
	}
	
	
}

package edu.dgepc.alumnos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.dgepc.alumnos.model.Usuario;
import edu.dgepc.alumnos.repository.UsuarioRepository;

@Service
public class DetalleUsuarioService implements UserDetailsService {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserDetails userDetails = null;
		
			Usuario usuario = usuarioRepository
				.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("El usuario no existe"));
			
			userDetails = new DetalleUsuario(usuario);
		
		return userDetails;
	}

}

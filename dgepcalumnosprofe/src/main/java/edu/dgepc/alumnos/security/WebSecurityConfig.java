package edu.dgepc.alumnos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity(debug = true)
@Configuration
public class WebSecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private FiltroJWTAutorizacion filtroJWTAutorizacion; // valido token

	private FiltroJWTAutenticacion filtroJWTAutenticacion;// valido usuario en bd

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder,
			UserDetailsService userDetailsService) throws Exception {
		return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder).and().parentAuthenticationManager(null)// no use la cadena de
																							// Autenticadores
				.build();
	}

	@Bean
	UsernamePasswordAuthenticationFilter crearFiltroAutenticacion(AuthenticationManager am) {
		FiltroJWTAutenticacion jwtAutenticacion = null;

			jwtAutenticacion = new FiltroJWTAutenticacion();
			jwtAutenticacion.setAuthenticationManager(am);
			jwtAutenticacion.setFilterProcessesUrl("/login");
			this.filtroJWTAutenticacion = jwtAutenticacion;

		return jwtAutenticacion;

	}
	
	/*public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("secreto"));
	}*/
	
	@Bean
	SecurityFilterChain filterChain (HttpSecurity httpSecurity, AuthenticationManager am, UsernamePasswordAuthenticationFilter filtroAutenticacion) throws Exception
	{
		/*UsernamePasswordAuthenticationFilter jwtAuthenticationFilter = new FiltroJWTAutenticacion();
		jwtAuthenticationFilter.setAuthenticationManager(am);
		jwtAuthenticationFilter.setFilterProcessesUrl(NOMBRE_SERVICIO_AUTH);*/
		//this.filtroJWTAutenticacion.setAuthenticationManager(am);
		//this.filtroJWTAutenticacion.setFilterProcessesUrl("/login");
		
		//System.out.println(httpSecurity); //CADA OBJETO SECURITY ES DISTINTO
		
		return httpSecurity
				.csrf().disable()//SIN ESTADO, SIN COOKIES, NO LA NECESITAMOS. EXIGIMOS TOKEN
				//.cors().disable()
				.authorizeHttpRequests()//exigimos autorización
				//.antMatchers(HttpMethod.POST)//sólo pedimos login pa get
				.anyRequest()//para todos las peticiones
				.authenticated()//y autenticación
				.and()
				/*.<httpBasic()
				.and()*/
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilter(filtroAutenticacion)
				.addFilterBefore (filtroJWTAutorizacion, UsernamePasswordAuthenticationFilter.class)
				.build();
		
	}

}

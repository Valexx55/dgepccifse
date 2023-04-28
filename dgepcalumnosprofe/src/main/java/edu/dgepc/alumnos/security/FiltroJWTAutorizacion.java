package edu.dgepc.alumnos.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

//ESTA CLASE, VALIDA EL TOKEN

@Component
public class FiltroJWTAutorizacion extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String bearerToken = request.getHeader("Authorization");
		if (bearerToken!=null)
		{
			if (bearerToken.startsWith("Bearer"))
			{
				String token = bearerToken.replace("Bearer ", "");
				UsernamePasswordAuthenticationToken unPAT = TokenUtil.getAuthentication(token);
				SecurityContextHolder.getContext().setAuthentication(unPAT);
				
			}
		}
		
		/*UsernamePasswordAuthenticationToken unPAT = TokenUtil.getAuthentication("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2YWxlcmlhbm9tb3Jlbm9AZ21haWwuY29tIiwiZXhwIjoxNjgyMTg4NjY0LCJub21icmUiOiJ2YWxlcmlhbm8ifQ.rtqdToegabn0HZDXbyOjkoEE43-dtnvZxoPXNFFuor8");
		SecurityContextHolder.getContext().setAuthentication(unPAT);*/
		
		filterChain.doFilter(request, response);
	}

}

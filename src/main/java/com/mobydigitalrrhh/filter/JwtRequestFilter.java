package com.mobydigitalrrhh.filter;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.mobydigitalrrhh.configuration.OAuthProperties;
import com.mobydigitalrrhh.google.GoogleChecker;
import com.mobydigitalrrhh.models.services.TokenService;
import com.mobydigitalrrhh.utils.JwtUtil;



import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	/*
	 * JwtRequestFilter es el PRIMER FILTRO que recibe el "authorization code".
	 * - Funciona solamente para la request "/oauth/app_token".
	 */
	@Autowired
	private TokenService tokenService;
  
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
    	/*
    	 * Obtengo el token.
    	 */
    	final String token = tokenService.getTokenFromRequest(request);
    	/*
    	 * Pregunto si la request viene de "/oauth/app_token" y NO es null, hago lo que sigue.
    	 */
    	if( this.isPostMethod(request) &&  token != null ) {    		
    		try {
    			/*
    			 * Acá verificamos el token, validamos que corresponda con un usuario de Google y 
    			 * generamos el token de nuestra aplicación.
    			 */
    			Authentication user = this.loadAuthenticationContext(token, request);            	
    			/*
    			 * Después de pasar por el método loadAuthenticationContext(), creamos el token para responder en el método que sigue.
    			 */
            	response.addHeader(HttpHeaders.AUTHORIZATION, tokenService.generateAppToken(user.getPrincipal().toString()));    			
    		}catch (Exception e) {
    			response.sendError(HttpStatus.SC_FORBIDDEN);
			}
    			
    	} else {
    		response.sendError(HttpStatus.SC_BAD_REQUEST);  
    	}
    }  	
    
    private boolean isPostMethod(HttpServletRequest request) {
    	return request.getMethod().equals(HttpMethod.POST.name());
    }
    
    private Authentication loadAuthenticationContext(String token, HttpServletRequest request) throws Exception{
    	/*
    	 * Por medio de tokenService.verifyTokenFromGoogle(token);, verificamos que realmente el token
    	 * viene de una cuenta logueada por Google.
    	 */
    	UsernamePasswordAuthenticationToken user = tokenService.verifyTokenFromGoogle(token);
    	/*
    	 * Una vez validado el token, hacemos otra validación para ver si el token 
    	 * (definido en la variable "user") NO es null.
    	 */
		if( user != null) {
			/*
			 * Si el user NO es null, entonces significa que está en nuestro sistema.
			 */
    		user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); //Optional
    		/*
    		 * Creamos el contexto para que todo el sistema pueda estar seguro de que el user está logueado. 
    		 * 
    		 * [ANOTACIÓN] El user debería extender de la clase "Authentication" para enviarlo directamente.
    		 * (VER ---> ".setAuthentication(user)" para tomar de referencia los parámetros.)
    		 */
        	SecurityContextHolder.getContext().setAuthentication(user);
        	return user;
		} else {
			throw new Exception();
		}
    }
		

}

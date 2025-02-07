package com.bugrates.online_marketplace_app.config;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bugrates.online_marketplace_app.service.JWTService;
import com.bugrates.online_marketplace_app.service.MyUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{

	private JWTService jwtService;
	private ApplicationContext applicationContext;
	
	
	public JwtFilter(JWTService jwtService, ApplicationContext applicationContext) {
		this.jwtService = jwtService;
		this.applicationContext = applicationContext;
	}
	
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String eMailAddress = null;
		
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			eMailAddress = jwtService.extractEMailAddress(token);
		}
		
		
		if(eMailAddress != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userDetails = applicationContext.getBean(MyUserDetailsService.class).loadUserByUsername(eMailAddress);
			
			if(jwtService.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
			
		}
		
		filterChain.doFilter(request, response);
		
		
	}

}

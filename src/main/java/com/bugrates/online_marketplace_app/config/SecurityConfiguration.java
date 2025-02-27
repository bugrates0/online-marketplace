package com.bugrates.online_marketplace_app.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.bugrates.online_marketplace_app.model.enums.Role;
import com.bugrates.online_marketplace_app.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	private MyUserDetailsService myUserDetailsService;
	
	private JwtFilter jwtFilter;

	public SecurityConfiguration(MyUserDetailsService myUserDetailsService, JwtFilter jwtFilter) {
		this.myUserDetailsService = myUserDetailsService;
		this.jwtFilter = jwtFilter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		return httpSecurity
				.cors(cors -> cors.configurationSource(corsConfigurationSource())
						)
				.csrf(customizer -> customizer.disable()
				)
				.authorizeHttpRequests(request ->
					request .requestMatchers("/api/v1/authentication/login", "/api/v1/authentication/register/customer", "/api/v1/authentication/register/seller", "/redis/*").permitAll()
						.requestMatchers("/api/v1/authentication/register/admin").hasRole(Role.ADMIN.toString())
						.requestMatchers("/api/v1/product-categories/{categoryId}/products").hasAnyRole(Role.SELLER.toString(), Role.ADMIN.toString())
						.requestMatchers("/api/v1/product-categories/**").hasRole(Role.ADMIN.toString())
						.requestMatchers("/api/v1/products/listed-products").hasRole(Role.CUSTOMER.toString())
						.requestMatchers("/api/v1/products/{productId}/listed-products").hasRole(Role.SELLER.toString())
						.requestMatchers("/api/v1/products/{productId}").hasRole(Role.ADMIN.toString())
						.requestMatchers("/api/v1/cart/**").hasRole(Role.CUSTOMER.toString())
						.requestMatchers("/api/v1/orders").hasRole(Role.CUSTOMER.toString())
						.requestMatchers("/api/v1/incoming-orders").hasRole(Role.SELLER.toString())
						
				)
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
		return daoAuthenticationProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8081"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
}

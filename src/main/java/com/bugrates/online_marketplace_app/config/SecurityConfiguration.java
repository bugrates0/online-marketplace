package com.bugrates.online_marketplace_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.bugrates.online_marketplace_app.MyUserDetailsService;
import com.bugrates.online_marketplace_app.model.Role;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	private MyUserDetailsService myUserDetailsService;

	public SecurityConfiguration(MyUserDetailsService myUserDetailsService) {
		this.myUserDetailsService = myUserDetailsService;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		return httpSecurity
				.csrf(customizer -> customizer.disable()
				)
				.authorizeHttpRequests(request ->
				request .requestMatchers("/registerAsCustomer").permitAll()
						.requestMatchers("/registerAsSeller").permitAll()
						.requestMatchers("/helloCustomer").hasRole(Role.CUSTOMER.toString())
						.requestMatchers("/helloSeller").hasRole(Role.SELLER.toString())
		)
				.httpBasic(Customizer.withDefaults())

				.build();
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

}

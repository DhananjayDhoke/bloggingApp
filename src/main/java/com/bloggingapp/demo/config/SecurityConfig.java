package com.bloggingapp.demo.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bloggingapp.demo.security.CustomUserDetailsService;
import com.bloggingapp.demo.security.JwtAuthenticationEntryPoint;
import com.bloggingapp.demo.security.JwtAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig{
    
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	    http.csrf()
//	      .disable()
//	      .authorizeHttpRequests()
//	      .anyRequest()
//	      .authenticated()
//	      .and()
//	      .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
//	      .and()
//	      .sessionManagement()
//	      .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//	    
//	    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//	    return http.build();
		
		http
		.cors()
		.and()
		.csrf()
		.disable()
		.authorizeHttpRequests()
		.requestMatchers("/api/v1/auth/**")
		.permitAll()
		.requestMatchers(HttpMethod.GET).permitAll()
//		.permitAll()
//		.requestMatchers(HttpMethod.DELETE, "api/users/{userID}")
//		.hasAuthority("ADMIN")
		.anyRequest()
//		.permitAll()
		.authenticated()
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
		.and().addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		http.authenticationProvider(authenticationProvider());
		
		return http.build();
	}
	
	  @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
	        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
	        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	        return authenticationManagerBuilder.build();
	    }
	    
	    @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	    	provider.setUserDetailsService(customUserDetailsService);
	    	provider.setPasswordEncoder(passwordEncoder());
	    	return provider;
	    }
	    

	
}

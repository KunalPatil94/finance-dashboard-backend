package com.finance.finance_dashboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.finance.finance_dashboard.security.JwtFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
	private final JwtFilter jwtFilter;
	
	public SecurityConfig(JwtFilter jwtFilter) {
		this.jwtFilter = jwtFilter;
	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf(csrf -> {
			try {
				csrf.disable().authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**","/swagger-ui/**",
                        "/v3/api-docs/**").permitAll().anyRequest()
	                    .authenticated()).addFilterBefore(jwtFilter,
	                            UsernamePasswordAuthenticationFilter.class);;
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		});
		return http.build();
	}

}

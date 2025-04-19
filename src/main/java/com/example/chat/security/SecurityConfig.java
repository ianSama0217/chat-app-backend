package com.example.chat.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		return http
				.sessionManagement(session -> session
						.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
				
				.csrf(csrf -> csrf.disable())
				.httpBasic(Customizer.withDefaults())
				// 關閉預設登入表單
				.formLogin(form -> form.disable())
				
				.authorizeHttpRequests(request -> request
						.requestMatchers("/register", "/test").permitAll()
						.requestMatchers("/login", "/hello").authenticated()
						.anyRequest().denyAll()
				)
				
				.build();
	}
}

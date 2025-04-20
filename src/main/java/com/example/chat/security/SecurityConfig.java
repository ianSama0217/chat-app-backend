package com.example.chat.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.chat.jwt.JwtAuthFilter;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {
	
	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
	    return http
	            .csrf(csrf -> csrf.disable())
	            .httpBasic(httpBasic -> httpBasic.disable()) // 可選，JWT 不需要 HTTP Basic
	            .formLogin(form -> form.disable()) // 關閉表單登入
	            .authorizeHttpRequests(request -> request
	                .requestMatchers("/register", "/login", "/test").permitAll()
	                .requestMatchers("/hello", "user/**").authenticated()
	                .anyRequest().denyAll()
	            )
	            .cors(cors -> cors.configurationSource(createCorsConfig()))
	            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
	            .build();
	}
	
	private CorsConfigurationSource createCorsConfig() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(List.of("*")); // 允許那些來源請求
		config.setAllowedHeaders(List.of("*"));
		config.setAllowedMethods(List.of("*"));
		config.setMaxAge(3600L); // 設定preflight請求結果可以被瀏覽器cache幾秒
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		
		return source;
	}
}

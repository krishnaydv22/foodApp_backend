package com.main.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class AppConfig {
	

	
//	@Bean
//	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//		
//		http.sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//		 .authorizeHttpRequests( auth -> auth 
//				 .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//				 .requestMatchers("/auth/signin", "/auth/signup").permitAll()
//				 .requestMatchers("/api/admin/**").hasAnyRole("RESTAURANT_OWNER", "ADMIN")
//				 .requestMatchers("/api/**").authenticated()
//				 .anyRequest().permitAll())
//		         .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
//		          .csrf(csrf -> csrf.disable() )
//		          .cors( cors -> cors.configurationSource(configurationSource()));
//		 
//		return http.build();
//		
//	}
//	
//	
//	
//	@Bean
//	PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	private CorsConfigurationSource configurationSource() {
//		
//		return new CorsConfigurationSource() {
//			
//			@Override
//			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//				CorsConfiguration cfg = new CorsConfiguration();
//				
//				cfg.setAllowedOrigins(Arrays.asList(
//						"http://localhost:3000",
//						"https://food-land-git-main-krushna-yadavs-projects.vercel.app"));
//				
//				cfg.setAllowedMethods(Collections.singletonList("*"));
//				cfg.setAllowCredentials(true);
//				cfg.setAllowedHeaders(Collections.singletonList("*"));
//				cfg.setExposedHeaders(Arrays.asList("Authorization"));
//				
//				cfg.setMaxAge(3600L);
//				
//				UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//			    source.registerCorsConfiguration("/**", cfg);
//				
//				return cfg;
//			}
//		};
//		
//		
//		
//		
//	}
	
	 @Bean
	    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
	                .requestMatchers("/auth/signin", "/auth/signup").permitAll()
	                .requestMatchers("/api/admin/**").hasAnyRole("RESTAURANT_OWNER", "ADMIN")
	                .requestMatchers("/api/**").authenticated()
	                .anyRequest().permitAll()
	            )
	            .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
	            .csrf(csrf -> csrf.disable())
	            .cors(cors -> cors.configurationSource(configurationSource()))
	            .headers(headers -> headers
	                .frameOptions(frameOptions -> frameOptions.sameOrigin())  // ✅ Allow iframe embedding from same origin
	                .contentTypeOptions(contentTypeOptions -> contentTypeOptions.disable())  // ✅ Disable "nosniff"
	                  // ✅ XSS protection enabled
	            );

	        return http.build();
	    }

	    @Bean
	    PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    CorsConfigurationSource configurationSource() {
	    	 CorsConfiguration cfg = new CorsConfiguration();
	    	    cfg.setAllowedOrigins(Arrays.asList(
	    	        "http://localhost:3000",
	    	        "https://food-land-git-main-krushna-yadavs-projects.vercel.app"
	    	    ));
	    	    cfg.setAllowedMethods(Collections.singletonList("*"));
	    	    cfg.setAllowCredentials(true);
	    	    cfg.setAllowedHeaders(Collections.singletonList("*"));
	    	    cfg.setExposedHeaders(Arrays.asList("Authorization"));
	    	    cfg.setMaxAge(3600L);

	    	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    	    source.registerCorsConfiguration("/**", cfg); // ✅ Correctly registering configuration

	    	    return (CorsConfigurationSource) source; // ✅ Correctly returning the source
	    }

}

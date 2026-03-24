package com.example.token.jwt;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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

@Configuration
@EnableWebSecurity
public class SecurityConfigJWT {


	
	  private JwtFilter jwtFilter;
	    
			// Constructor for injection (Spring automatically injects SecurityConfigJWT here)
			public SecurityConfigJWT(JwtFilter jwtFilter) {
				this.jwtFilter = jwtFilter;
			}
	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http)  {
	        http
	            .csrf(csrf -> csrf.disable()) // Disable CSRF for stateless APIs
	            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Use stateless sessions
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/api/student/login/**","/api/student/register/**").
	                permitAll() // Allow public access to authentication endpoints
	                .anyRequest().authenticated() // Secure all other endpoints
	            );

	        // Add the custom JWT filter before the standard UsernamePasswordAuthenticationFilter
	        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	        return http.build();
	    }
	    
	    
	    
	
	   
	   
//    @Bean
//    public SecurityFilterChain  securityFilterChain(HttpSecurity http) throws Exception {
//    	http.csrf(csrf -> csrf.disable())
//    	.authorizeHttpRequests(auth -> auth
//                .requestMatchers("/public/**").permitAll() // Use requestMatchers
//                .anyRequest().authenticated()
//        .requestMatchers("/api/student/login/*")
//       //antMatchers("/api/student/authenticate")//for angular
//        .permitAll()
//        .anyRequest()
//        .authenticated()
//        //.and()
//        //.sessionManagement()
//        //.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        );
//http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//return http.build();
//
//    }

    // You can also define other beans, like a PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // To expose the AuthenticationManager as a bean, you can create a bean of type AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    //To allow cross origin request access 
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
      CorsConfiguration configuration = new CorsConfiguration();
      configuration.setAllowedOrigins(Arrays.asList("*"));
      configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","OPTIONS","PATCH", "DELETE"));
      configuration.setAllowCredentials(true);
      configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
      configuration.setMaxAge((long) 10);
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", configuration);
      return source;
    }
}
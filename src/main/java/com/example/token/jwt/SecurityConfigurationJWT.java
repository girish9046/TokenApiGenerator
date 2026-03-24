//package com.example.token.jwt;
//import java.util.Arrays;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
// 
//@Configuration
//@EnableWebSecurity
//public class SecurityConfigurationJWT extends WebSecurityConfigurerAdapter{
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private JwtFilter jwtFilter;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.userDetailsService(userService);
//    } 
//    
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
//
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//    	 System.err.println(".......JWTT........SecurityConfigurationJWTSecurityConfigurationJWTSecurityConfigurationJWT : ");
//        http.csrf()
//                .disable()
//                .authorizeRequests()
//                .antMatchers("/api/student/authenticate/*")
//               //antMatchers("/api/student/authenticate")//for angular
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//
//    }
//		
//  
//  //To allow cross origin request access 
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//      CorsConfiguration configuration = new CorsConfiguration();
//      configuration.setAllowedOrigins(Arrays.asList("*"));
//      configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","OPTIONS","PATCH", "DELETE"));
//      configuration.setAllowCredentials(true);
//      configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
//      configuration.setMaxAge((long) 10);
//      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//      source.registerCorsConfiguration("/**", configuration);
//      return source;
//    }
//   
//}
package com.examplerm.rmdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig {
        private final UserDetailsService userDetailsService;
        private final JWTAuthorizationFilter jwtAuthorizationFilter;



        @Bean
        SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception{
            JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
            jwtAuthenticationFilter.setAuthenticationManager(authManager);
            jwtAuthenticationFilter.setFilterProcessesUrl("/login");

            return http
                .cors().and()
                .csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
        }

        
        @Bean
        AuthenticationManager authManager(HttpSecurity http) throws Exception{
            return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
        }

        @Bean
        PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }

         @Bean
        public WebMvcConfigurer corsConfigurer(){
            return new WebMvcConfigurer() {
                    @Override
                    public void addCorsMappings(CorsRegistry registry) {
                        registry.addMapping("/**")
                            .allowedOrigins("http://127.0.0.1:5173")
                            .allowedMethods("GET","POST","PUT","DELETE","OPTIONS","HEAD")
                            .allowedHeaders("*");
                    }	
            };
		
	}	
}



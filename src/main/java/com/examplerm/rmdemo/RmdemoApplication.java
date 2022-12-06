package com.examplerm.rmdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RmdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RmdemoApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer() {
				@Override
				public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("/**")
						.allowedOrigins("http://127.0.0.1:5173/","http://localhost:5173")
						.allowedMethods("GET","POST","PUT","DELETE","OPTIONS","HEAD")
						.allowedHeaders("*");
				}	
		};
		
	}

}

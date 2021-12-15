package com.folcode.techoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*lo saco?*/
/*@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})*/
@SpringBootApplication
/*@SpringBootApplication(scanBasePackages = {"boot.registration"} , exclude = JpaRepositoriesAutoConfiguration.class)*/
public class TechoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechoApiApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer	corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("https://techo-app.herokuapp.com","http://localhost:4000");
			}
		};
	}
}



package edu.dgepc.alumnos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DgepcalumnosprofeApplication {

	/**
	 * 
	 * Para configurar Eureka cliente:
	 * 
	 * 1) ADD dependencia 
	 * 2) Incluyo la anotacion @EnableEurekaClient
	 * 3) Configurar propiedades (dir servidor eureka)
	 */
	
	public static void main(String[] args) {
		SpringApplication.run(DgepcalumnosprofeApplication.class, args);
	}

}

package edu.meyfp.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer//activo las librerías de Eureka
public class MseurekaprofeApplication {
	
	/**
	 * PASOS PARA CREAR EUREKA
	 * 
	 * 1) SPRING starter-project, add depdendecia de EurekaServer
	 * 2) Manualmente, añado la dependencia de Glssfish
	 * 3) Anotamos el Main @EnableEurekaServer
	 * 4) Configurar las properties
	 * @param args
	 */

	public static void main(String[] args) {
		SpringApplication.run(MseurekaprofeApplication.class, args);
	}

}

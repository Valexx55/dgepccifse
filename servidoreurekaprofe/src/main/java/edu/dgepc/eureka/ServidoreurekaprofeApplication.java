package edu.dgepc.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer //"Activar las librerias de Eureka-server- Servidor"
public class ServidoreurekaprofeApplication {
	
	/*
	 * PASOS PARA CREAR EUREKA SERVER
	 * 
	 * 1) Defino el proyecto con Spring Starter DEPENDECIA Eureka SERVER
	 * 2) Add depednecia JAXB
	 * 3) Configuro properties
	 * 4) Anotamos el main con @EnableEurekaServer
	 * 
	 */

	public static void main(String[] args) {
		SpringApplication.run(ServidoreurekaprofeApplication.class, args);
	}

}

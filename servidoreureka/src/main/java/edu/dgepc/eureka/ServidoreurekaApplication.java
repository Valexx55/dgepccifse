package edu.dgepc.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //"Activar las librerias de Eureka-server- Servidor"
public class ServidoreurekaApplication {

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
		SpringApplication.run(ServidoreurekaApplication.class, args);
		//TODO: ASOCIAR EL SERVICIO DE ALUMNOS COMO CLIENTE DE EUREKA, para que se haga el AUTOREGISTRO
		
		//1 COMPLETAR EUREKA
		//2 AUTENTICATION JWT
		//3 subida FICHEROS
		//4 TEST
		//5 TRANSACIONALIDAD
		//6 GATEWAY?
	}

}

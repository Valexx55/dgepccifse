package edu.dgepc.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServidorgatewayprofeApplication {

	//http://localhost:8081/alumno/obtenerFraseChiquito
	//http://localhost:8090/api/alumnos/obtenerFraseChiquito
	public static void main(String[] args) {
		SpringApplication.run(ServidorgatewayprofeApplication.class, args);
	}

}

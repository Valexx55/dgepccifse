package edu.meyfp.alumnos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsalumnosprofeApplication {
	
	/**
	 * 
	 * para configuar eureka cliente:
	 * 
	 * 1) add starter eureka cliente
	 * 2) anotación en e amin de @EnableEurekaClient
	 * 3) Config propiertes
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		SpringApplication.run(MsalumnosprofeApplication.class, args);
	}

}

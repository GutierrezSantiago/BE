package ar.edu.utn.bda.c3k4.holamundospringinitializr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*
* Esta anotación dice que todas las clases que salgan en este paquete
* o de paquetes que esten en esta clase, cuando la app arranque
* spring lea las clases que estan dentro y las empiece a trabajar de forma
* automatica (lea y carga en memoria y los trabaja segun como y cuando
* aparezcan)
* */
public class HolamundoSpringinitializrApplication {

	public static void main(String[] args) {
		SpringApplication.run(HolamundoSpringinitializrApplication.class, args);
	}

}

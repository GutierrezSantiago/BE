package ar.edu.utn.frc.bso.demoSpringBoot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoSpringBootApplication implements CommandLineRunner {

	private Menu menu;

	public DemoSpringBootApplication(Menu menu) {
		this.menu = menu;
	}

	@Override
	public void run(String... args) throws Exception {
		menu.menu();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringBootApplication.class, args);


	}


}

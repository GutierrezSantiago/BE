package utn.frc.backend.tutor.sac;

import utn.frc.backend.tutor.sac.dal.*;
import utn.frc.backend.tutor.sac.domain.Persona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import utn.frc.backend.tutor.sac.lib.util.Util;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Sac04SpringDataJpaApplication implements CommandLineRunner {

    PersonaRepository rep;

    @Autowired
    public Sac04SpringDataJpaApplication(PersonaRepository rep) {
        this.rep = rep;
    }

    public static void main(String[] args) {
         SpringApplication.run(Sac04SpringDataJpaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        //Util.fill('=', 80);
        //runBatch();
    }

    public String pData(Persona p) {
        return p.toString() + (p.getPid());
    }

    public void runBatch() {
        List<Persona> personas = rep.findAll();
        System.out.println(Util.fill('-', 80));
        System.out.println("Personas:");
        System.out.println(Util.fill('-', 80));
        personas.forEach(persona -> System.out.println(Util.rPad(persona, 80, 8)));

        System.out.println("Creando nueva persona...");
        Persona persona = new Persona();
        persona.setDni("dni999");
        persona.setApellido("Lojuno");
        persona.setNombre("Johny");
        System.out.println("Nueva Persona creadada: " + persona);
        rep.save(persona);
        System.out.println("Nueva Persona guardada: " + persona);
        Integer pid = persona.getPid();

        persona = rep.findById(pid).get();
        System.out.println("Nueva persona cargada: " + persona);

        persona.setApellido("Quito");
        persona.setNombre("Armando Esteban");
        System.out.println("Nueva persona modificada: " + persona);

        rep.save(persona);
        System.out.println("Nueva persona guardada: " + persona);

        persona = rep.findById(pid).get();
        System.out.println("Nueva persona cargada: " + persona);

        rep.delete(persona);
        System.out.println("Nueva persona eliminada: " + persona);

        System.out.println("Se intenta cargar nuevamente: ");
        Optional<Persona> p = rep.findById(pid);
        System.out.println(p.isPresent() ? p.get() : "No se encontró");
    }

}

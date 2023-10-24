package utn.frc.backend.tutor.sac;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utn.frc.backend.tutor.sac.domain.*;
import utn.frc.backend.tutor.sac.lib.store.exceptions.StoreableValidationException;

import java.util.Date;
import java.util.Set;

public class DomainTests {

    SACStore store = new SACStore();

    @BeforeEach
    void setup() throws Exception {
        store.generarDatos();
    }

    @Test
    void initialData() {
        Set<Persona> personas = store.findPersonas();
        personas.forEach(System.out::println);
        Assertions.assertFalse(personas.isEmpty());
    }

    @Test
    void validation1() throws StoreableValidationException {
        Persona persona = new Persona("dni", (String) null, "nombre");

        StoreableValidationException thrown =  Assertions.assertThrows(
                StoreableValidationException.class,
                () -> store.savePersona(persona),
                "Se espera StoreableValidationException");

        Assertions.assertEquals(thrown.getMessage(), "apellido es obligatorio");

        persona.setApellido("apellido");
        store.savePersona(persona);

    }

    @Test
    void isolation1() throws Exception {
        Persona persona = new Persona("dni", "apellido", "nombre");
        Set<Persona> personas = store.findPersonas();
        personas.add(persona);
        Assertions.assertTrue(personas.contains(persona));
        Assertions.assertFalse(store.findPersonas().contains(persona));

        store.findPersonas().add(persona);
        Assertions.assertFalse(store.findPersonas().contains(persona));

        store.savePersona(persona);
        Assertions.assertFalse(personas.contains(persona));
        Assertions.assertTrue(store.findPersonas().contains(persona));
    }

    @Test
    void isolation2() throws Exception {
        Persona persona = new Persona("dni", "apellido", "nombre");

        Persona saved = store.savePersona(persona);
        Assertions.assertEquals(persona, saved);
        Assertions.assertTrue(persona == saved);

        Persona loaded = store.findPersona(persona.getPid());
        Assertions.assertEquals(persona, loaded);
        Assertions.assertFalse(persona == loaded);
    }

}

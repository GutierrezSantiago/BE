package utn.frc.backend.tutor.sac;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import utn.frc.backend.tutor.sac.dal.PersonaRepository;
import utn.frc.backend.tutor.sac.domain.Persona;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
class Sac04SpringDataJpaApplicationTests {

    private PersonaRepository rep;
    private Integer pid = 0;
    private Map<Integer, Persona> data = new HashMap<>();

    @BeforeEach
    public void setup() {
        rep = Mockito.mock(PersonaRepository.class);

        data.clear();
        Persona p = new Persona();
        p.setPid(1); p.setDni("111"); p.setApellido("Lojuno"); p.setNombre("Johny");
        data.put(p.getPid(), p);
        p = new Persona();
        p.setPid(2); p.setDni("222"); p.setApellido("Quito"); p.setNombre("Armando Estéban");
        data.put(p.getPid(), p);
        p = new Persona();
        p.setPid(3); p.setDni("333"); p.setApellido("Chuca"); p.setNombre("Kevin");
        data.put(p.getPid(), p);
    }

    //@Test
    //void contextLoads() {
    //}

    @Test
    void testDataCount() {
        Mockito.when(rep.findAll()).thenReturn(data.values().stream().toList());

        List<Persona> personas = rep.findAll();
        personas.forEach(persona -> System.out.println(persona));
        Assertions.assertTrue(personas.size() == 3);
    }

    @Test
    void testDataSingle() {
        Mockito.when(rep.findById(anyInt())).thenReturn(Optional.of(data.get(1)));

        Persona persona = rep.findById(20).get();
        System.out.println(persona);
        Assertions.assertEquals(persona.getPid(), 1);
    }


}

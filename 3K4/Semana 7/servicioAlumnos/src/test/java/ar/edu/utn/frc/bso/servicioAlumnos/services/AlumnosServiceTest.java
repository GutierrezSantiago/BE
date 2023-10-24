package ar.edu.utn.frc.bso.servicioAlumnos.services;

import ar.edu.utn.frc.bso.servicioAlumnos.entities.Alumno;
import ar.edu.utn.frc.bso.servicioAlumnos.repositories.AlumnoRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class AlumnosServiceTest {

    private AlumnosService service;
    private AlumnoRepository repo;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Before All");
    }

    @BeforeEach
    public void setup() {
        System.out.println("Before Each");
        repo = Mockito.mock(AlumnoRepository.class);
        Alumno trinidad = new Alumno(234, "Trinidad", 6, 7, 8);
        Alumno pepe = new Alumno(123, "Pepe", 2, 3, 4);
        List<Alumno> alumnos = List.of(
                pepe,
                trinidad
        );
        Mockito.when(repo.findAll()).thenReturn(alumnos);
        service = new AlumnosService(repo);


//        Mockito.when(repo.getAlumnoByNombre("Trinidad")).thenReturn(Optional.of(trinidad));
//        Mockito.when(repo.getAlumnoByNombre("Pepe")).thenReturn(Optional.of(pepe));
//        Mockito.when(repo.getAlumnoByNombre("Juan")).thenReturn(Optional.empty());
//        System.out.println(repo.getAlumnoByNombre("Pepe"));
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("After All");
    }

    @AfterEach
    public void cleanup() {
        System.out.println("After Each");
        service = new AlumnosService(null);
    }


    @Test
    public void testPromedio() {
        System.out.println("Test Promedio");
        Alumno a = new Alumno(123, "pepe", 4, 5, 6);
        double promedio = service.promedio(a);
        assertEquals(5.0, promedio, 0.001);
    }

    @Test
    public void testPromedioCeros() {
        System.out.println("Test promedio ceros");
        Alumno a = new Alumno(123, "pepe", 0, 0, 0);
        double promedio = service.promedio(a);
        assertEquals(0, promedio, 0.001);
    }

    @Test
    public void testPromedioNulo() {
        assertThrows(IllegalArgumentException.class, () -> service.promedio(null), "Debería lanzar error.");
    }

    @Test
    public void testAlumnosRegulares() {
        List<Alumno> alumnosRegulares = service.getAlumnosRegulares();
        assertNotNull(alumnosRegulares);
        assertEquals(1, alumnosRegulares.size());
        assertEquals("Trinidad", alumnosRegulares.get(0).getNombre());
    }

}
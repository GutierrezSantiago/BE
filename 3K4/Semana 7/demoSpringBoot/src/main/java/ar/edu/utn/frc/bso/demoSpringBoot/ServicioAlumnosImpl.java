package ar.edu.utn.frc.bso.demoSpringBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioAlumnosImpl implements ServicioAlumnos {

    private RepositorioAlumnos repositorio;
    private int notaMinima;

    public ServicioAlumnosImpl(RepositorioAlumnos repositorio, @Value("${alumnos.notaMinima}") int notaMinima) {
        this.repositorio = repositorio;
        this.notaMinima = notaMinima;
    }

    @Override
    public void agregarAlumno(Alumno x) {
        repositorio.agregar(x);
    }

    @Override
    public List<Alumno> alumnosAprobados() {
        return repositorio.listar().stream()
                .filter(x -> x.getNota() > notaMinima)
                .toList();
    }
}

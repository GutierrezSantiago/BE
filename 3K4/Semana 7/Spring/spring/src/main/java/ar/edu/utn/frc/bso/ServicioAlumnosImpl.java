package ar.edu.utn.frc.bso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioAlumnosImpl implements ServicioAlumnos {

    private RepositorioAlumnos repositorio;
    private int notaMinima;

    @Autowired
    public ServicioAlumnosImpl(RepositorioAlumnos repositorio) {
        this(repositorio, 4);
    }

    public ServicioAlumnosImpl(RepositorioAlumnos repositorio, int notaMinima) {
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

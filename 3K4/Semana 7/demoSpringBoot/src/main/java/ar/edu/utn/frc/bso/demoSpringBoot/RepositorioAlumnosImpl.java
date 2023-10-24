package ar.edu.utn.frc.bso.demoSpringBoot;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepositorioAlumnosImpl implements RepositorioAlumnos {

    private Alumno[] v = new Alumno[100];
    private int size;


    @Override
    public List<Alumno> listar() {
        List<Alumno> listado = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            listado.add(v[i]);
        }
        return listado;
    }

    @Override
    public void agregar(Alumno a) {
        v[size] = a;
        size++;
    }
}

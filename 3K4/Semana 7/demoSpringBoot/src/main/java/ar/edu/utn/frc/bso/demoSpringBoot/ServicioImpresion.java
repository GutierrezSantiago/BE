package ar.edu.utn.frc.bso.demoSpringBoot;

import org.springframework.stereotype.Service;

@Service
public class ServicioImpresion {

    public void imprimirAlumno(Alumno x) {
        System.out.println(x);
    }
}

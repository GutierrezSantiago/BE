package ar.edu.utn.frc.bso.demoSpringBoot;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class Menu {

    ServicioAlumnos alumnos;
    ServicioImpresion impresion;

    public Menu(ServicioAlumnos alumnos, ServicioImpresion impresion) {
        this.alumnos = alumnos;
        this.impresion = impresion;
    }


    public void menu() {
        Scanner sc = new Scanner(System.in);

        int op = -1;
        while (op != 3) {

            System.out.println("1 _ Agregar");
            System.out.println("2 _ Listar");
            System.out.println("Ingrese opción");
            op = sc.nextInt();

            if (op == 1) {
                System.out.println("Ingrese legajo: ");
                int legajo = sc.nextInt();
                sc.nextLine();
                System.out.println("Ingrese nombre: ");
                String nombre = sc.nextLine();
                System.out.println("Ingrese nota: ");
                int nota = sc.nextInt();
                sc.nextLine();

                Alumno a = Alumno.builder()
                        .nota(nota)
                        .nombre(nombre)
                        .legajo(legajo)
                        .build();

                alumnos.agregarAlumno(a);

            } else if (op == 2) {
                List<Alumno> lista = alumnos.alumnosAprobados();
                for (Alumno a : lista) {
                    impresion.imprimirAlumno(a);
                }
            }
        }
    }
}

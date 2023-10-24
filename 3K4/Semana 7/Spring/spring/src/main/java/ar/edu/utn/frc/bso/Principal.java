package ar.edu.utn.frc.bso;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

//        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext("ar.edu.utn.frc.bso");

        ServicioAlumnos alumnos = context.getBean(ServicioAlumnos.class);

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

                Alumno a = new Alumno(legajo, nombre, nota);
                alumnos.agregarAlumno(a);

            } else if (op == 2) {
                List<Alumno> lista = alumnos.alumnosAprobados();
                for (Alumno a : lista) {
                    System.out.println(a);
                }

            }



        }



    }
}

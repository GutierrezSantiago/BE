package utn.frc.bda.k4;

import utn.frc.bda.k4.sem3.poo.NroRacional;
import utn.frc.bda.k4.sem3.poo.PersonaSem3;
import utn.frc.bda.k4.sem3.poo.Soldado;
import utn.frc.bda.k4.sem2.java.Perro;
import utn.frc.bda.k4.sem5.jdbc.JDBC;
import utn.frc.bda.k4.sem6.jpa.JPA;

import java.util.Scanner;


public class Main {

    public static void ejemploPerro() {
        System.out.println("Bienvenido al mundo canino");
        System.out.println("Invocando definicion de perro:");
        Perro.definir();

        System.out.println("Bienvenido al mundo canino");
        System.out.println("Les presentamos a Bobby:");
        System.out.println("Bobby, ladre por favor");

        Perro bobby = new Perro();
        bobby.ladrar();
    }

    public static void ejemploFor1(int x, int y) {
        for (int i = 0; i < y; i++) {

            for (int j = 0; j < x; j++) {
                System.out.print(j == 0 ? "*" : " *");
                //if (j == 0) {
                //    System.out.print("*");
                //} else {
                //    System.out.print(" *");
                //}
            }

            System.out.println();
        }

    }

    public static void clase3ej1() {
        PersonaSem3 personaSem3 = new PersonaSem3();
        personaSem3.saludar();

        Soldado soldado = new Soldado();
        soldado.saludar();

        ////persona = soldado;
        ////persona.saludar();
        //
        //Persona ppp = new Soldado();
        //ppp.saludar();
        //
        ////Soldado sss = (Soldado) persona;
        ////sss.saludar();
        //
        //((Persona) soldado).saludar();


    }

    public static void racionales() {
        NroRacional r1 = new NroRacional(3, 4);
        NroRacional r2 = new NroRacional(8);
        NroRacional r3 = new NroRacional();
        r3.setNum(7);
        r3.setDen(0); // termina valiendo 1

        System.out.println("R1: " + r1);
        System.out.println("R2: " + r2);
        System.out.println("R3: " + r3);
    }

    public static void ejemploScanner() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese 1er entero: ");

        int a = scanner.nextInt();

        System.out.println("Ingrese 2do entero: ");

        String b = scanner.next();

        System.out.println("A + B = " + (a + Integer.parseInt(b)));
    }

    public static void main(String[] args) {

        //System.out.println("Hello world!");

        //ejemploPerro();

        //ejemploFor1(6, 4);

        //clase3ej1();

        //racionales();

        //ejemploScanner();

        //Tamagotchi.run();

        Scanner scanner = new Scanner(System.in);

        //JDBC.run(scanner);
        JPA.run(scanner);

        scanner.close();

    }
}
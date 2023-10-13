package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class LeyendoDatos {
    public static void main(String[] args){
        Scanner lector;
        //lectura desde archivo de texto
        File archivo = new File(".\\src\\main\\resources\\numeros.txt");
        try {
            lector = new Scanner(archivo);
            int acu=0;
            while (lector.hasNext()){
                acu += lector.nextInt();
            }
            System.out.println(acu);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        // lectura por consola
        lector = new Scanner(System.in); //recibe de param de donde saca la información
        //system in es la entrada por teclado, system out sale de la clase, son estaticos los atributos in y out
        System.out.print("Cual es tu nombre?: ");
        var nombre = lector.next();

        System.out.print("Cual es tu edad?: ");
        var edad = lector.nextInt();

        System.out.println("Hola soy " + nombre + " y tengo " + edad + " años.");
    }

}

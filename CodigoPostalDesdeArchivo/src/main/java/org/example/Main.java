package org.example;
import java.awt.*;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;
import java.io.File;

public class Main {
    private static CodigoPostal[] codigos;

    public static void main(String[] args) {
        final URL fileLocation = Main.class.getClassLoader().getResource("cp.csv");
        File cps = null;
        try {
            if (fileLocation != null){
            cps = new File(fileLocation.toURI());
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        if (cps != null) {
            try {
                Main.cargarCodigosPostales(cps);
                for (int i = 0; i<Main.codigos.length; i++){
                    System.out.println(Main.codigos[i]);
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }



    }

    private static void cargarCodigosPostales(File cps) throws FileNotFoundException {
        int cantidad = getSizeCodigosPostales(cps);
        codigos = new CodigoPostal[cantidad];
        Scanner lectorCodigos = new Scanner(cps);
        int posicion = 0;
        while (lectorCodigos.hasNext()){
            if (posicion>=Main.codigos.length) break;
            var linea = lectorCodigos.nextLine();
            var tokens = linea.split(";");
            CodigoPostal codigo = new CodigoPostal(tokens[0], tokens[1], tokens[2]);
            codigos[posicion] = codigo;
            posicion++;
        }
    }
    private static int getSizeCodigosPostales(File cps){
        Scanner lectorCodigos = new Scanner(cps);
        int contador = 0;
        while(lectorCodigos.hasNext()){
            lectorCodigos.nextLine();
            contador++;
        }
        return contador;
    }
}
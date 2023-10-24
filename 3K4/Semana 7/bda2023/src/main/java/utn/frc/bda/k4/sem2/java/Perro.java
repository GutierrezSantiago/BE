package utn.frc.bda.k4.sem2.java;

public class Perro {
    private static int nroPatas = 4;
    private static int nroDientes = 32;

    private static String raza = "canina";

    public static void definir() {
        System.out.println("Un perro es un animal mamifero de " + nroPatas + " patas... sabe ladrar");
        System.out.println(
                String.format(
                        "Un perro es un animal mamifero de raza %s de %d patas y %d dientes... sabe ladrar",
                        raza,
                        nroPatas,
                        nroDientes
                )
        );
    }

    public static int getNroPatas() {
        return nroPatas;
    }

    public void ladrar() {
        System.out.println("Guau");
    }



}

package org.example;

public class Main {
    public static void main(String[] args) {

        // númericos naturales
        short numerito;
        int numero;
        long numerote;
        byte intentoDeNumerito;

        numero = 15; // contenido en 8 bytes
        numerito = 5; // contenido en 4 bytes

        numero = numerito; // funciona (4 bytes entran en 8)
        //numerito = numero; // tengo problemas cuando hay tipos de datos de mayor tamaño y se los quiere guardar en un tipo de dato de menor tamaño
        // Si quiero hacer esa conversion debo convertir el entero en short y asumir la perdida de precisión
        numerito = (short) numero;

        // númerico reales
        float decimal; // precision de decimales
        double decimalote; // precision de doble de decimales

        //asume los decimales literales como double, entonces hay que castear si queremos float
        decimal = 19.45f;
        decimal = (float) 19.45; //es lo mismo
        decimalote = 20.43;

        boolean verdaderoFalso;

        //Cadena de caracteres
        char unCaracter = 'f'; // Si se suman dos char no es un ASCII, se muestra la suma de la codificacion ASCII de esos dos caracteres.
        char otroCaracter = 'g';
        String nombre = "Santiago"; //internamente es una lista de chars
        String valor; //valor numerico que quiero pasar a string

        valor = Float.toString(decimal); // Uso el metodo estatico de la clase wrapper del metodo que quiero pasar as string
        char res = (char) (unCaracter + otroCaracter);

        //variables especiales - contadores y acumuladores
        // c=0; c+=1 - c++ (contador (+1)); c+=valor (acumulador)

        // acu = acu + numero // + - / * %
        // Cuando divido y son dos enteros es division entera, si hay float o double el resultado es ese mismo tipo

        int conteo = 0;
        conteo = conteo++; // primero asigno la variable y despues le sumo 1
        conteo = ++conteo; // primero le sumo 1 y despues le asigno el valor a la variable

        // var -> no se el valor que es, pero tengo que asignarlo en el momento que guardo memoria porque ahi infiere el tipo de dato y no se va a poder cambiar
        var tipoDeDato = 65;

        System.out.println((int)unCaracter); //caracter ascii
        System.out.println((int)otroCaracter);
        System.out.println(unCaracter+otroCaracter); //si concateno dos caracteres me da un int del codigo ascii, no el caracter (Debo castear a char si quiero el caracter)
        System.out.println(res);
        System.out.println("Hola " + nombre );

        // Estructura de control
        //condicionales
        // and: &&, or: ||, not: !
        if (numero>=0 && numero<=200) { // Se encuentra en el rango [0...200]
            int var1 = 0;
        }
        // == != > >= < <=
        var opcion = 5;
        switch (opcion) {
            case 1:
                break;
            case 2:
                break;
            default:
        }

        // iteradores o ciclos
        // 0 - n while
        while (opcion < 100) {
            opcion++;
        }
        // 1 - n do while
        do {
            opcion++;
        } while (opcion < 100);
        // n for
        for (var i = 0; i<200; i++){
            System.out.println(i);
        }
    }
}
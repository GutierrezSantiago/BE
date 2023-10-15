package org.example;

import java.util.HashMap;
import java.util.Scanner;
import java.util.function.BinaryOperator;


public class App 
{
    public static void main( String[] args )
    {
        CalculadoraFuncionalMapaBinaryOperator();
    }
    public static void CalculadoraClases()
    {
        Scanner sc = new Scanner(System.in);

        Operacion c1 = new Suma();
        Operacion c2 = new Resta();
        Operacion c3 = new Producto();
        Operacion c4 = new Division();

        System.out.println( "Calculadora" );

        System.out.println( "1 - Sumar" );
        System.out.println( "2 - Restar" );
        System.out.println( "3 - Multiplicar" );
        System.out.println( "4 - Dividir" );
        System.out.println( "0 - Salir" );

        int opcion = sc.nextInt();
        while (opcion!=0){
            System.out.print("Ingrese los dos números: ");
            float a = sc.nextFloat();
            float b = sc.nextFloat();

            switch (opcion) {
                case 1:
                    System.out.println(c1.calcular(a, b));
                    break;
                case 2:
                    System.out.println(c2.calcular(a, b));
                    break;
                case 3:
                    System.out.println(c3.calcular(a, b));
                    break;
                case 4:
                    System.out.println(c4.calcular(a, b));
                    break;
                default:
            }
            opcion = sc.nextInt();
        }
    }


    public static void CalculadoraFuncional()
    {
        Scanner sc = new Scanner(System.in);
        /* Version mas explayada
        Operacion c1 = new Operacion(){ // Java permite instanciar Interfaces con un solo método haciendo un override al instanciar, o mediante expresiones lambda
            @Override
            public float calcular(float a, float b) {
                return a+b;
            }
        };
        Operacion c2 = (float a, float b)->{return a - b;}; // Expresiones lambda, debo poner ; luego de return y luego de llaves
        Operacion c3 = (float a, float b)->{return a * b;};
        Operacion c4 = (float a, float b)->{return a / b;};

        Operacion[] opciones = new Operacion[4];
        opciones[0] = c1;
        opciones[1] = c2;
        opciones[2] = c3;
        opciones[3] = c4;
        */
        //Version mas corta
        Operacion[] opciones = {(float a, float b)->{return a + b;},
                (float a, float b)->{return a - b;},
                (float a, float b)->{return a * b;},
                (float a, float b)->{return a / b;}};

        System.out.println( "Calculadora" );

        System.out.println( "1 - Sumar" );
        System.out.println( "2 - Restar" );
        System.out.println( "3 - Multiplicar" );
        System.out.println( "4 - Dividir" );
        System.out.println( "0 - Salir" );

        int opcion = sc.nextInt();
        while (opcion!=0){
            System.out.println("Ingrese los dos números: ");
            float a = sc.nextFloat();
            float b = sc.nextFloat();

            System.out.println(opciones[opcion-1].calcular(a, b));
            opcion = sc.nextInt();
        }
    }

    public static void CalculadoraFuncionalConMapa()
    {
        Scanner sc = new Scanner(System.in);

        HashMap<String, Operacion> calculos = new HashMap<>();
        calculos.put("+", (float a, float b)->{return a + b;});
        calculos.put("-", (float a, float b)->{return a - b;});
        calculos.put("*", (float a, float b)->{return a * b;});
        calculos.put("/", (float a, float b)->{return a / b;});

        System.out.println( "Calculadora" );
        System.out.println( "Ingrese la operación (numero1 simbolo numero2)" );
        System.out.println( "Para salir, S como simbolo" );

        float a = sc.nextFloat();
        String opcion = sc.next();
        float b = sc.nextFloat();
        while (!opcion.equals("S")){

            float resultado = calculos.get(opcion).calcular(a, b);
            System.out.println(resultado);
            a = sc.nextFloat();
            opcion = sc.next();
            b = sc.nextFloat();
        }
    }

    public static void CalculadoraFuncionalMapaBinaryOperator()
    {
        Scanner sc = new Scanner(System.in);

        HashMap<String, BinaryOperator<Float>> calculos = new HashMap<>();
        calculos.put("+", (Float a, Float b)->{return a + b;});
        calculos.put("-", (Float a, Float b)->{return a - b;});
        calculos.put("*", (Float a, Float b)->{return a * b;});
        calculos.put("/", (Float a, Float b)->{return a / b;});

        System.out.println( "Calculadora" );
        System.out.println( "Ingrese la operación (numero1 simbolo numero2)" );
        System.out.println( "Para salir, S como simbolo" );

        float a = sc.nextFloat();
        String opcion = sc.next();
        float b = sc.nextFloat();
        while (!opcion.equals("S")){

            float resultado = calculos.get(opcion).apply(a, b);
            System.out.println(resultado);
            a = sc.nextFloat();
            opcion = sc.next();
            b = sc.nextFloat();
        }
    }
}


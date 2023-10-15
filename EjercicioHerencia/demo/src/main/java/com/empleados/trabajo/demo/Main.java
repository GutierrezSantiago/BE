package com.empleados.trabajo.demo;public class Main
{
    public static void main(String[] args)
    {
        Obrero obrero = new Obrero(20000, 15);
        Administrativo admin1 = new Administrativo(10000, true);
        Administrativo admin2 = new Administrativo(10000, false);
        Vendedor vendedor = new Vendedor(10000, 100000);

        //System.out.println(obrero.CalcularSueldo());
        //System.out.println(admin1.CalcularSueldo());
        //System.out.println(admin2.CalcularSueldo());
        //System.out.println(vendedor.CalcularSueldo());

        Empleado[] plantel = new Empleado[4];
        plantel[0] = obrero;
        plantel[1] = admin1;
        plantel[2] = admin2;
        plantel[3] = vendedor;

        for (Empleado e: plantel){
            var clase = e.getClass().getSimpleName();
            System.out.println("Tipo de Empleado: " + clase + " - Sueldo base: " + e.sueldo + " - Sueldo final: " + e.CalcularSueldo());

        }

    }
}

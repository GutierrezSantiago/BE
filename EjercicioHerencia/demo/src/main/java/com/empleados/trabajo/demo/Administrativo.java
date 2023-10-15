package com.empleados.trabajo.demo;

public class Administrativo extends Empleado{
    private boolean presentismo;
    public Administrativo(double sueldoParam, boolean presentismoParam)
    {
        sueldo = sueldoParam;
        presentismo = presentismoParam;
    }
    @Override
    public double CalcularSueldo()
    {
        if (presentismo)
        {
            return sueldo * 1.15;
        }
        return sueldo;
    }
}

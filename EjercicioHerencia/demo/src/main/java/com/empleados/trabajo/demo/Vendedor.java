package com.empleados.trabajo.demo;

public class Vendedor extends Empleado
{
    private double ventasTotales;
    public Vendedor(double sueldoParam, double ventasParam)
    {
        sueldo = sueldoParam;
        ventasTotales = ventasParam;
    }
    @Override
    public double CalcularSueldo()
    {
        return sueldo + ventasTotales*0.1;
    }
}

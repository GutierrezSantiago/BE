package com.empleados.trabajo.demo;

public class Obrero extends Empleado
{
    private int diasTrabajados;
    public Obrero(double sueldoParam, int diasTrabajadosParam)
    {
        sueldo = sueldoParam;
        diasTrabajados = diasTrabajadosParam;
    }

    @Override
    public double CalcularSueldo()
    {
        return (sueldo/20)*diasTrabajados;
    }
}

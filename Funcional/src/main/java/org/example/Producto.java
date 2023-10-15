package org.example;

public class Producto implements Operacion{
    @Override
    public float calcular(float a, float b) {
        return a*b;
    }
}

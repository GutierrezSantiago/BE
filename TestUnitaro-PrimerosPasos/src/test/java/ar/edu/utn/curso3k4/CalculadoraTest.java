package ar.edu.utn.curso3k4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {
    private Calculadora c;
    @BeforeEach
    public void crearCalculadora(){
        c = new Calculadora();
    }
    @Test
    void pruebaSumar1() {
        assertEquals(9.4f, c.sumar(1.7f, 7.7f));
    }
    @Test
    void pruebaSumar2() {
        assertEquals(0f, c.sumar(0, 0));
    }
    @Test
    void pruebaSumar3() {
        assertEquals(3.5f, c.sumar(5, -1.5f));
    }
    @Test
    void pruebaSumar4() {
        assertEquals(0f, c.sumar(10, -10));
    }
    @Test
    void pruebaSumar5() {
        assertEquals(4f, c.sumar(2, 2));
    }
}
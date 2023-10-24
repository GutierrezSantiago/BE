package ar.edu.utn.frc.bso;

public class Alumno {

    private int legajo;
    private String nombre;

    private int nota;

    public Alumno(int legajo, String nombre, int nota) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.nota = nota;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "legajo=" + legajo +
                ", nombre='" + nombre + '\'' +
                ", nota=" + nota +
                '}';
    }
}

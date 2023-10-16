package entidades;

import java.util.ArrayList;
import java.util.List;

public class Aeropuerto {
    private int codigo;//key

    private String codigoInternacional; // COR EZE

    private String nombre;
    private ArrayList<Terminal> terminales;

    private DepositoEquipaje depositoTransito;

    public Aeropuerto(int codigo, String codigoInternacional, String nombre) {
        this.codigo = codigo;
        this.codigoInternacional = codigoInternacional;
        this.nombre = nombre;
        this.terminales = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoInternacional() {
        return codigoInternacional;
    }

    public void setCodigoInternacional(String codigoInternacional) {
        this.codigoInternacional = codigoInternacional;
    }

    @Override
    public String toString() {
        return "Aeropuerto{" +
                "codigo=" + codigo +
                ", codigoInternacional='" + codigoInternacional + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    public void setTerminales(ArrayList<Terminal> terminalesByAeropuerto) {
        terminales = terminalesByAeropuerto;
    }
}

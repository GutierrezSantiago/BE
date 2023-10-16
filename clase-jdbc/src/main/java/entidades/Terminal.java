package entidades;

import java.util.ArrayList;

public class Terminal {

    private int codigo;
    private Aeropuerto aeropuerto;
    private ArrayList<Equipaje> cintaTransportadora;

    public Terminal(int codigo, Aeropuerto aeropuerto) {
        this.codigo = codigo;
        this.aeropuerto = aeropuerto;
        this.cintaTransportadora = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Aeropuerto getAeropuerto() {
        return aeropuerto;
    }

    public void setAeropuerto(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }

    @Override
    public String toString() {
        return "Terminal{" +
                "codigo=" + codigo +
                ", aeropuerto=" + aeropuerto.toString() +
                '}';
    }
}

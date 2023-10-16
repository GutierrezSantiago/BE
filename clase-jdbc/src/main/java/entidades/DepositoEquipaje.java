package entidades;

import java.util.ArrayList;

public class DepositoEquipaje {

    private int codigo;
    private String descripcion;
    private Aeropuerto aeropuerto;
    private ArrayList<Equipaje> equipajes;

    public DepositoEquipaje(int codigo, String descripcion, Aeropuerto aeropuerto) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.aeropuerto = aeropuerto;
        this.equipajes = new ArrayList<>();
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

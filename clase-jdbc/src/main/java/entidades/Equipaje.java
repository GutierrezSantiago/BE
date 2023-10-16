package entidades;

public abstract class Equipaje {
    private int codigo;
    private Aeropuerto destino;
    private double costo;
    private String numeroVuelo;
    private boolean analizado;

    public Equipaje() {

        this(null, 0.0, null);
    }

    public Equipaje(Aeropuerto destino, double costo, String numeroVuelo) {
        this.destino = destino;
        this.costo = costo;
        this.numeroVuelo = numeroVuelo;
        this.analizado = false;
    }

    public int getCodigo() {
        return codigo;
    }

    public Aeropuerto getDestino() {
        return destino;
    }

    public void setDestino(Aeropuerto destino) {
        this.destino = destino;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getNumeroVuelo() {
        return numeroVuelo;
    }

    public void setNumeroVuelo(String numeroVuelo) {
        this.numeroVuelo = numeroVuelo;
    }

    public boolean isAnalizado() {
        return analizado;
    }

    public void setAnalizado(boolean analizado) {
        this.analizado = analizado;
    }


    public abstract double costoAnalisis();
}

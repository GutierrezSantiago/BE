package entidades;

public class Valija extends Equipaje {

    private double peso;

    public Valija() {
        this(null, 0.0, null, 0.0);
    }

    public Valija(Aeropuerto destino, double costo, String numeroVuelo, double peso) {
        super(destino, costo, numeroVuelo);
        this.peso = peso;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public double costoAnalisis() {
        double total = 0;

        if (this.peso < 50) {
            total = super.getCosto();
        } else {
            var dif = (this.peso - 50) * 12 / 100d;
            total = super.getCosto() + (super.getCosto() * dif);
        }

        return total;
    }
}

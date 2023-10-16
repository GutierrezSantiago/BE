package entidades;

public class BolsaCorreo extends Equipaje {

    private int cantidad_bultos;
    private TipoCorreo tipoCorreo;

    public enum TipoCorreo {
        NONE,
        SOBRES,
        ENCOMIENDAS
    }

    public BolsaCorreo() {
        this(null, 0.0, null, 0, TipoCorreo.NONE);
    }

    public BolsaCorreo(Aeropuerto destino, double costo, String numeroVuelo, int cantidadBultos, TipoCorreo tipoCorreo) {
        super(destino, costo, numeroVuelo);
        cantidad_bultos = cantidadBultos;
        this.tipoCorreo = tipoCorreo;
    }

    public int getCantidadBultos() {
        return cantidad_bultos;
    }

    public void setCantidadBultos(int cantidad) {
        this.cantidad_bultos = cantidad;
    }

    public TipoCorreo getTipoCorreo() {
        return tipoCorreo;
    }

    public void setTipoCorreo(TipoCorreo tipoCorreo) {
        this.tipoCorreo = tipoCorreo;
    }

    @Override
    public double costoAnalisis() {
        if (this.tipoCorreo == TipoCorreo.ENCOMIENDAS)
            return super.getCosto() * 1.15;
        return super.getCosto();
    }
}

package org.example;

public class CodigoPostal {
    private String numero;
    private String provincia;
    private String localidad;

    public CodigoPostal(String numero, String nombreCiudad, String localidad) {
        this.numero = numero;
        this.provincia = nombreCiudad;
        this.localidad = localidad;
    }
    public CodigoPostal(String linea){
        var tokens = linea.split(";");
        this.numero = tokens[0];
        this.provincia = tokens[1];
        this.localidad = tokens[2];
    }

    @Override
    public String toString() {
        return "CodigoPostal{" +
                "numero='" + numero + '\'' +
                ", nombreCiudad='" + provincia + '\'' +
                ", localidad='" + localidad + '\'' +
                '}';
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
}

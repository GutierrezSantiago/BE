package utn.frc.bda.k4.lib.domain;

import java.io.Serializable;

public class Alumno extends Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    private String legajo;

    public Alumno() {
        super();
    }

    public Alumno(Integer id, String dni, String apellido, String nombre, String legajo) {
        super(id, dni, apellido, nombre);
        this.legajo = legajo;
    }

    public Alumno(String dni, String apellido, String nombre, String legajo) {
        this(null, dni, apellido, nombre, legajo);
    }

    public Alumno(Persona persona, String legajo) {
        this(persona.id, persona.dni, persona.apellido, persona.nombre, legajo);
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alumno)) {
            return false;
        }
        Alumno other = (Alumno) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return super.toString() + String.format(" [%s]", legajo);
        return String.format("(%3d) %s, %s [%s]", id, apellido, nombre, legajo);
    }

}

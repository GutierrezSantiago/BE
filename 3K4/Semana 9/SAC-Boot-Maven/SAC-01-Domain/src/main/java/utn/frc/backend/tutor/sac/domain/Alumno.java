package utn.frc.backend.tutor.sac.domain;

import utn.frc.backend.tutor.sac.lib.store.Updateable;
import utn.frc.backend.tutor.sac.lib.store.annotations.StoreNotBlank;

import java.io.Serializable;

/**
 *
 * @author scarafia
 */
public class Alumno extends Persona implements Serializable, Updateable<Persona> {

  private static final long serialVersionUID = 1L;

  @StoreNotBlank(msg = "legajo es obligatorio")
  private String legajo;

  public Alumno() {
    super();
  }

  public Alumno(Persona persona, String legajo) {
    super(persona);
    this.legajo = legajo;
  }

  public Alumno(Alumno alumno) {
    this();
    update(alumno);
  }

  public Alumno(Integer pid, String dni, String apellido, String nombre, String legajo) {
    super(pid, dni, apellido, nombre);
    this.legajo = legajo;
  }

  public Alumno(String dni, String apellido, String nombre, String legajo) {
    this(null, dni, apellido, nombre, legajo);
  }

  public String getLegajo() {
    return legajo;
  }

  public void setLegajo(String legajo) {
    this.legajo = legajo;
  }

  @Override
  public Alumno clone() {
    return new Alumno(this);
  }

  public Alumno update(Alumno alumno) {
    if (alumno == null) {
      return null;
    }

    this.legajo = ((Alumno) alumno).legajo;
    return (Alumno) super.update(alumno);
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (pid != null ? pid.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Alumno)) {
      return false;
    }
    Alumno other = (Alumno) object;
    if ((this.pid == null && other.pid != null) || (this.pid != null && !this.pid.equals(other.pid))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    //return super.toString() + String.format(" [%s]", legajo);
    return String.format("(%3d) %s, %s [%s]", pid, apellido, nombre, legajo);
  }
  
}

package utn.frc.backend.tutor.sac.domain;

import utn.frc.backend.tutor.sac.lib.store.Updateable;
import utn.frc.backend.tutor.sac.lib.store.annotations.StoreNotBlank;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author scarafia
 */
public class Docente extends Persona implements Serializable, Updateable<Persona> {

  private static final long serialVersionUID = 1L;

  @StoreNotBlank(msg = "legajo es obligatorio")
  private String legajo;
  private Set<Materia> materias = new HashSet<>(); // null safety
  
  public Docente() {
    super();
  }

  public Docente(Persona persona, String legajo) {
    super(persona);
    this.legajo = legajo;
  }

  public Docente(Docente docente) {
    this();
    update(docente);
  }

  public Docente(Integer pid, String dni, String apellido, String nombre, String legajo) {
    super(pid, dni, apellido, nombre);
    this.legajo = legajo;
  }

  public Docente(String dni, String apellido, String nombre, String legajo) {
    this(null, dni, apellido, nombre, legajo);
  }
  
  public String getLegajo() {
    return legajo;
  }

  public void setLegajo(String legajo) {
    this.legajo = legajo;
  }

  public Set<Materia> getMaterias() {
    return materias;
  }
  
  public void setMaterias(Set<Materia> materias) {
    if (materias == null) {
      materias = new HashSet<>(); // null safety
    }
    this.materias = materias;
  }

  @Override
  public Docente clone() {
    return new Docente(this);
  }

  public Docente update(Docente docente) {
    if (docente == null) {
      return null;
    }

    this.legajo = ((Docente)docente).legajo;
    return (Docente) super.update(docente);
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
    if (!(object instanceof Docente)) {
      return false;
    }
    Docente other = (Docente) object;
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

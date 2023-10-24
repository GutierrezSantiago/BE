package utn.frc.backend.tutor.sac.domain;

import utn.frc.backend.tutor.sac.lib.store.Updateable;
import utn.frc.backend.tutor.sac.lib.store.annotations.StoreKey;
import utn.frc.backend.tutor.sac.lib.store.annotations.StoreNotBlank;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author scarafia
 */
public class Materia implements Serializable, Updateable<Materia> {

  private static final long serialVersionUID = 1L;

  @StoreKey
  private Integer mid;
  @StoreNotBlank(msg = "nombre es obligatorio")
  private String nombre;
  private String descripcion;
  
  private Set<Docente> docentes = new HashSet<>(); // null safety

  public Materia() {
    super();
  }

  public Materia(Materia materia) {
    this();
    update(materia);
  }

  public Materia(Integer mid, String nombre, String descripcion) {
    this();
    this.mid = mid;
    this.nombre = nombre;
    this.descripcion = descripcion;
  }
  
  public Materia(String nombre, String descripcion) {
    this(null, nombre, descripcion);
  }

  public Integer getMid() {
    return mid;
  }

  public void setMid(Integer mid) {
    this.mid = mid;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Set<Docente> getDocentes() {
    return docentes;
  }

  public void setDocentes(Set<Docente> docentes) {
    if (docentes == null) {
      docentes = new HashSet<>(); // null safety
    }
    this.docentes = docentes;
  }

  @Override
  public Materia clone() {
    return new Materia(this);
  }

  @Override
  public Materia update(Materia materia) {
    if (materia == null) {
      return null;
    }

    mid = materia.mid;
    nombre = materia.nombre;
    descripcion = materia.descripcion;
    return this;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (mid != null ? mid.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Materia)) {
      return false;
    }
    Materia other = (Materia) object;
    if ((this.mid == null && other.mid != null) || (this.mid != null && !this.mid.equals(other.mid))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return String.format("(%2d) %s", mid, nombre);
  }
  
}

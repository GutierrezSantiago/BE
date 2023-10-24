package utn.frc.backend.tutor.sac.domain;

import utn.frc.backend.tutor.sac.lib.store.Updateable;
import utn.frc.backend.tutor.sac.lib.store.annotations.StoreKey;
import utn.frc.backend.tutor.sac.lib.store.annotations.StoreNotBlank;
import utn.frc.backend.tutor.sac.lib.store.annotations.StoreNotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author scarafia
 */
public class Curso implements Serializable, Updateable<Curso> {

  private static final long serialVersionUID = 1L;

  @StoreKey
  private Integer cid;
  @StoreNotBlank(msg = "nombre es obligatorio")
  private String nombre;
  private String descripcion;
  @StoreNotNull(msg = "cupo es obligatorio")
  private Short cupo;
  @StoreNotNull(msg = "fecha de inicio es obligatoria")
  private Date fInicio;
  private Date fFin;

  @StoreNotNull(msg = "materia es obligatoria")
  private Materia materia;

  @StoreNotNull(msg = "docente es obligatorio")
  private Docente docente;
  
  private Set<Inscripcion> inscripciones = new HashSet<>(); // null safety
  
  public Curso() {
    super();
  }

  public Curso(Curso curso) {
    this();
    update(curso);
  }

  public Curso(Integer cid, String nombre, String descripcion, short cupo, Date fInicio, Date fFin, Materia materia, Docente docente) {
    this();
    this.cid = cid;
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.cupo = cupo;
    this.fInicio = fInicio;
    this.fFin = fFin;
    this.materia = materia;
    this.docente = docente;
  }
  
  public Curso(String nombre, String descripcion, short cupo, Date fInicio, Date fFin, Materia materia, Docente docente) {
    this(null, nombre, descripcion, cupo, fInicio, fFin, materia, docente);
  }

  public Integer getCid() {
    return cid;
  }

  public void setCid(Integer cid) {
    this.cid = cid;
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

  public short getCupo() {
    return cupo;
  }

  public void setCupo(short cupo) {
    this.cupo = cupo;
  }

  public Date getFInicio() {
    return fInicio;
  }

  public void setFInicio(Date fInicio) {
    this.fInicio = fInicio;
  }

  public Date getFFin() {
    return fFin;
  }

  public void setFFin(Date fFin) {
    this.fFin = fFin;
  }
  
  public Materia getMateria() {
    return materia;
  }
  
  public void setMateria(Materia materia) {
    this.materia = materia;
  }
  
  public Docente getDocente() {
    return docente;
  }
  
  public void setDocente(Docente docente) {
    this.docente = docente;
  }
  
  public Set<Inscripcion> getInscripciones() {
    return inscripciones;
  }
  
  public void setInscripciones(Set<Inscripcion> inscripciones) {
    if (inscripciones == null) {
      inscripciones = new HashSet<>(); // null safety
    }
    this.inscripciones = inscripciones;
  }

  // Como Set
  public Set<Alumno> getAlumnos() {
    return inscripciones.stream().map(i -> i.getAlumno()).collect(Collectors.toSet());
  }
  // O también como List
  public List<Alumno> getAlumnosList() {
    return inscripciones.stream().map(i -> i.getAlumno()).toList();
  }

  @Override
  public Curso clone() {
    return new Curso(this);
  }

  @Override
  public Curso update(Curso curso) {
    if (curso == null) {
      return null;
    }

    this.cid = curso.cid;
    this.nombre = curso.nombre;
    this.descripcion = curso.descripcion;
    this.cupo = curso.cupo;
    this.fInicio = curso.fInicio;
    this.fFin = curso.fFin;
    this.materia = curso.materia;
    this.docente = curso.docente;
    return this;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (cid != null ? cid.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Curso)) {
      return false;
    }
    Curso other = (Curso) object;
    if ((this.cid == null && other.cid != null) || (this.cid != null && !this.cid.equals(other.cid))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return String.format("(%2d) %s", cid, nombre);
  }
  
}

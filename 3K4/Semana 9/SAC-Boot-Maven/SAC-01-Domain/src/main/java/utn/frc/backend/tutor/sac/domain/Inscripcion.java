package utn.frc.backend.tutor.sac.domain;

import utn.frc.backend.tutor.sac.lib.store.Updateable;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author scarafia
 */

public class Inscripcion implements Serializable, Updateable<Inscripcion> {

  private static final long serialVersionUID = 1L;

  private Curso curso;
  private Alumno alumno;
  private Date fecha;
  private Integer calificacionFinal;

  public Inscripcion() {
    super();
  }

  public Inscripcion(Inscripcion inscripcion) {
    this();
    update(inscripcion);
  }

  public Inscripcion(Curso curso, Alumno alumno, Date fecha, Integer calificacionFinal) {
    this();
    this.curso = curso;
    this.alumno = alumno;
    this.fecha = fecha;
    this.calificacionFinal = calificacionFinal;
  }

  public Curso getCurso() {
    return curso;
  }

  public void setCurso(Curso curso) {
    this.curso = curso;
  }

  public Alumno getAlumno() {
    return alumno;
  }

  public void setAlumno(Alumno alumno) {
    this.alumno = alumno;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public Integer getCalificacionFinal() {
    return calificacionFinal;
  }

  public void setCalificacionFinal(Integer calificacionFinal) {
    this.calificacionFinal = calificacionFinal;
  }

  @Override
  public Inscripcion clone() {
    return new Inscripcion(this);
  }

  @Override
  public Inscripcion update(Inscripcion inscripcion) {
    this.curso = inscripcion.curso;
    this.alumno = inscripcion.alumno;
    this.fecha = fecha;
    this.calificacionFinal = inscripcion.calificacionFinal;
    return this;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCurso(), getAlumno());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Inscripcion that = (Inscripcion) o;
    return getCurso().equals(that.getCurso()) && getAlumno().equals(that.getAlumno());
  }

}

package utn.frc.bda.k4.lib.domain.jpa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
//import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author scarafia
 */
@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "pid")
@Table(name = "docente")
//@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "JPADocente.findAll", query = "SELECT d FROM JPADocente d"),
  @NamedQuery(name = "JPADocente.findAllById", query = "SELECT d FROM JPADocente d ORDER BY d.id"),
  @NamedQuery(name = "JPADocente.findAllByApeNom", query = "SELECT d FROM JPADocente d ORDER BY d.apellido, d.nombre"),
  @NamedQuery(name = "JPADocente.findById", query = "SELECT d FROM JPADocente d WHERE d.id = :id"),
  @NamedQuery(name = "JPADocente.findByLegajo", query = "SELECT d FROM JPADocente d WHERE d.legajo = :legajo")})
public class JPADocente extends JPAPersona implements Serializable {

  private static final long serialVersionUID = 1L;

  @Basic(optional = false)
  @Column(name = "legajo", length = 16)
  private String legajo;
  
  // No hace falta del lado del docente => se cambia por relacion many to many mapped.
  // Sí hará falta del lado del curso, pues para poder asignar un docente, éste
  // tiene que estar relacionado con la materia a dicho curso.
  //@OneToMany(cascade = CascadeType.ALL, mappedBy = "docente")
  //private Collection<MateriaDocentes> materiaDocentesCollection;
  @ManyToMany(mappedBy = "docentes")
  private Collection<JPAMateria> materias;
  
  public JPADocente() {
    super();
  }

  public JPADocente(Integer id, String dni, String apellido, String nombre, String legajo) {
    super(id, dni, apellido, nombre);
    this.legajo = legajo;
  }

  public JPADocente(String dni, String apellido, String nombre, String legajo) {
    this(null, dni, apellido, nombre, legajo);
  }
  
  public JPADocente(JPAPersona persona, String legajo) {
    this(persona.id, persona.dni, persona.apellido, persona.nombre, legajo);
  }

  public String getLegajo() {
    return legajo;
  }

  public void setLegajo(String legajo) {
    this.legajo = legajo;
  }

  public Collection<JPAMateria> getMaterias() {
    return materias;
  }
  
  public void setMaterias(Collection<JPAMateria> materias) {
    this.materias = materias;
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
    if (!(object instanceof JPADocente)) {
      return false;
    }
    JPADocente other = (JPADocente) object;
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

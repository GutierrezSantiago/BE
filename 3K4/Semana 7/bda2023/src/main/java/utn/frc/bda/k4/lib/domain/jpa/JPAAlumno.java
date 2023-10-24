package utn.frc.bda.k4.lib.domain.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
//--
//import org.apache.openjpa.persistence.jdbc.FetchMode;
//import org.apache.openjpa.persistence.jdbc.SubclassFetchMode;
//--
//import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author scarafia
 */
@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
//@SubclassFetchMode(FetchMode.NONE)
@PrimaryKeyJoinColumn(name = "pid")
@Table(name = "alumno")
//@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "JPAAlumno.findAll", query = "SELECT a FROM JPAAlumno a"),
  @NamedQuery(name = "JPAAlumno.findAllById", query = "SELECT a FROM JPAAlumno a ORDER BY a.id"),
  @NamedQuery(name = "JPAAlumno.findAllByApeNom", query = "SELECT a FROM JPAAlumno a ORDER BY a.apellido, a.nombre"),
  @NamedQuery(name = "JPAAlumno.findById", query = "SELECT a FROM JPAAlumno a WHERE a.id = :id"),
  @NamedQuery(name = "JPAAlumno.findByLegajo", query = "SELECT a FROM JPAAlumno a WHERE a.legajo = :legajo"),
  @NamedQuery(name = "JPAAlumno.findByLegajo", query = "SELECT a FROM JPAAlumno a WHERE a.legajo = :legajo")
})
public class JPAAlumno extends JPAPersona implements Serializable {
  
  public static void insert(EntityManager em, Integer id, String legajo) {
    String query = "insert into alumno (pid, legajo) values (?, ?)";
    em.createNativeQuery(query).setParameter(1, id).setParameter(2, legajo).executeUpdate();
  }
  public static void delete(EntityManager em, Integer id) {
    String query = "delete from alumno where pid = ?";
    em.createNativeQuery(query).setParameter(1, id).executeUpdate();
  }

  private static final long serialVersionUID = 1L;
  
  @Basic(optional = false)
  @Column(name = "legajo", length = 16)
  private String legajo;

  public JPAAlumno() {
    super();
  }

  public JPAAlumno(Integer id, String dni, String apellido, String nombre, String legajo) {
    super(id, dni, apellido, nombre);
    this.legajo = legajo;
  }

  public JPAAlumno(String dni, String apellido, String nombre, String legajo) {
    this(null, dni, apellido, nombre, legajo);
  }
  
  public JPAAlumno(JPAPersona persona, String legajo) {
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
    if (!(object instanceof JPAAlumno)) {
      return false;
    }
    JPAAlumno other = (JPAAlumno) object;
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

package utn.frc.backend.tutor.sac.domain.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
//import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author scarafia
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//@SubclassFetchMode(FetchMode.NONE)
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "persona")
//@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
  @NamedQuery(name = "Persona.findAllById", query = "SELECT p FROM Persona p ORDER BY p.pid"),
  @NamedQuery(name = "Persona.findAllByApeNom", query = "SELECT p FROM Persona p ORDER BY p.apellido, p.nombre"),
  @NamedQuery(name = "Persona.findById", query = "SELECT p FROM Persona p WHERE p.pid = :pid"),
  @NamedQuery(name = "Persona.findByDni", query = "SELECT p FROM Persona p WHERE p.dni = :dni"),
  @NamedQuery(name = "Persona.findByApellido", query = "SELECT p FROM Persona p WHERE p.apellido = :apellido")
})
//@NamedNativeQuery(
//  name = "Persona.personaList",
//  resultSetMapping = "PersonaListMapping",
//  query = "SELECT"
//          + " p.pid, p.dni, p.apellido, p.nombre,"
//          + " a.legajo as 'legAlumno', d.legajo as 'legDocente'"
//          + " FROM persona p"
//          + " LEFT JOIN alumno a ON p.pid = a.pid"
//          + " LEFT JOIN docente d ON p.pid = d.pid"
//)
//@SqlResultSetMapping(
//  name = "PersonaListMapping",
//  classes = {
//    @ConstructorResult(
////      targetClass = utn.frc.dlc.tutor.sac.lib.domain.PersInfo.class,
//      targetClass = utn.frc.dlc.tutor.sac.lib.domain.PersInfo2.class,
//      columns = {
//        @ColumnResult(name = "pid", type = Integer.class),
//        @ColumnResult(name = "dni"),
//        @ColumnResult(name = "apellido"),
//        @ColumnResult(name = "nombre"),
//        @ColumnResult(name = "legAlumno"),
//        @ColumnResult(name = "legDocente"),
//      }
//    )
//  }
//)
public class Persona implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "pid")
  protected Integer pid;
  @Basic(optional = false)
  @Column(name = "dni", length = 64)
  protected String dni;
  @Basic(optional = false)
  @Column(name = "apellido", length = 64)
  protected String apellido;
  @Basic(optional = false)
  @Column(name = "nombre", length = 64)
  protected String nombre;

  public Persona() {
    super();
  }

  public Persona(Integer pid, String dni, String apellido, String nombre) {
    this();
    this.pid = pid;
    this.dni = dni;
    this.apellido = apellido;
    this.nombre = nombre;
  }

  public Persona(String dni, String apellido, String nombre) {
    this(null, dni, apellido, nombre);
  }

  public Integer getPid() {
    return pid;
  }

  public void setPid(Integer pid) {
    this.pid = pid;
  }

  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
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
    if (!(object instanceof Persona)) {
      return false;
    }
    Persona other = (Persona) object;
    if ((this.pid == null && other.pid != null) || (this.pid != null && !this.pid.equals(other.pid))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return String.format("(%3d) %s, %s", pid, apellido, nombre);
  }

}

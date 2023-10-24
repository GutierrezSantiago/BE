package utn.frc.bda.k4.lib.domain.jpa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
//import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author scarafia
 */
@Entity
@Table(name = "materia")
//@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "JPAMateria.findAll", query = "SELECT m FROM JPAMateria m"),
  @NamedQuery(name = "JPAMateria.findAllById", query = "SELECT m FROM JPAMateria m ORDER BY m.id"),
  @NamedQuery(name = "JPAMateria.findAllByNombre", query = "SELECT m FROM JPAMateria m ORDER BY m.nombre"),
  @NamedQuery(name = "JPAMateria.findById", query = "SELECT m FROM JPAMateria m WHERE m.id = :id"),
  @NamedQuery(name = "JPAMateria.findByNombre", query = "SELECT m FROM JPAMateria m WHERE m.nombre = :nombre")
})
public class JPAMateria implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "mid")
  private Integer id;
  @Basic(optional = false)
  @Column(name = "nombre", length = 64)
  private String nombre;
  @Column(name = "descripcion", length = 2147483647)
  private String descripcion;
  
  // Se cambia por relación many to many, evitando mapear tabla intermedia.
  //@OneToMany(cascade = CascadeType.ALL, mappedBy = "materia")
  //private Collection<MateriaDocentes> materiaDocentesCollection;
  @JoinTable(
    name = "materia_docentes",
    joinColumns = {
      @JoinColumn(name = "mid", referencedColumnName = "mid")
    },
    inverseJoinColumns = {
      @JoinColumn(name = "pid", referencedColumnName = "pid")
    }
  )
  @ManyToMany
  private Collection<JPADocente> docentes;

  public JPAMateria() {
    super();
  }

  public JPAMateria(Integer id, String nombre, String descripcion) {
    this();
    this.id = id;
    this.nombre = nombre;
    this.descripcion = descripcion;
  }
  
  public JPAMateria(String nombre, String descripcion) {
    this(null, nombre, descripcion);
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  public Collection<JPADocente> getDocentes() {
    return docentes;
  }

  public void setDocentes(Collection<JPADocente> docentes) {
    this.docentes = docentes;
  }
  
  public int getCantDocentes() {
    return docentes.size();
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
    if (!(object instanceof JPAMateria)) {
      return false;
    }
    JPAMateria other = (JPAMateria) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return String.format("(%2d) %s", id, nombre);
  }
  
}

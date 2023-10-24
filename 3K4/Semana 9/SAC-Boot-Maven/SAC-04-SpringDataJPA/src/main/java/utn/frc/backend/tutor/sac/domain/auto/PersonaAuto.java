package utn.frc.backend.tutor.sac.domain.auto;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "persona", schema = "public", catalog = "sacdb")
public class PersonaAuto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "pid", nullable = false)
    private Integer pid;
    @Basic
    @Column(name = "dni", nullable = false, length = 64)
    private String dni;
    @Basic
    @Column(name = "apellido", nullable = false, length = 64)
    private String apellido;
    @Basic
    @Column(name = "nombre", nullable = false, length = 64)
    private String nombre;
    @OneToOne(mappedBy = "personaByPid")
    private AlumnoAuto alumnoByPid;
    @OneToOne(mappedBy = "personaByPid")
    private DocenteAuto docenteByPid;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonaAuto that = (PersonaAuto) o;
        return Objects.equals(pid, that.pid) && Objects.equals(dni, that.dni) && Objects.equals(apellido, that.apellido) && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, dni, apellido, nombre);
    }

    public AlumnoAuto getAlumnoByPid() {
        return alumnoByPid;
    }

    public void setAlumnoByPid(AlumnoAuto alumnoByPid) {
        this.alumnoByPid = alumnoByPid;
    }

    public DocenteAuto getDocenteByPid() {
        return docenteByPid;
    }

    public void setDocenteByPid(DocenteAuto docenteByPid) {
        this.docenteByPid = docenteByPid;
    }
}

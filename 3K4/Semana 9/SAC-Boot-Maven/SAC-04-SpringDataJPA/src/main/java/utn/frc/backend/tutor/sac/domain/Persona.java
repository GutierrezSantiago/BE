package utn.frc.backend.tutor.sac.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

// ------------------------------------------------------------
// Standard (sin lombok), con:
//   - getters/setters
//   - equals/hashCode
//   - toString
// ------------------------------------------------------------

@Entity
// ------------------------------------------------------------
//@Inheritance(strategy = InheritanceType.JOINED)   // NO good, comentar en clase (ver Alumno)
//@OnDelete(action = OnDeleteAction.NO_ACTION)
// ------------------------------------------------------------
//LONG LIVE PLAIN OLD, SIMPLE, CLASSIC AND VANILLA JDBC APPROACH !!!
public class Persona {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid", nullable = false)
    private Integer pid;
    @Basic
    @Column(name = "dni", nullable = false, length = 64)
    @NotBlank(message = "DNI es obligatorio")
    private String dni;
    @Basic
    @Column(name = "apellido", nullable = false, length = 64)
    @NotBlank(message = "Apellido es obligatorio")
    private String apellido;
    @Basic
    @Column(name = "nombre", nullable = false, length = 64)
    @NotBlank(message = "Nombre es obligatorio")
    private String nombre;

    public Persona() {
        super();
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(pid, persona.pid) && Objects.equals(dni, persona.dni) && Objects.equals(apellido, persona.apellido) && Objects.equals(nombre, persona.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, dni, apellido, nombre);
    }

    @Override
    public String toString() {
        return String.format("(%3d) [%16s] %s, %s", pid, dni, apellido, nombre);
    }

}

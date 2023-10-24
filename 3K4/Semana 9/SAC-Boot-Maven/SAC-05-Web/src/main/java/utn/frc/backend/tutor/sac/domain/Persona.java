package utn.frc.backend.tutor.sac.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Entity
@Getter @Setter @NoArgsConstructor @ToString
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

    public Persona update(Persona persona) {
        dni = persona.dni;
        apellido = persona.apellido;
        nombre = persona.nombre;
        return this;
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
}

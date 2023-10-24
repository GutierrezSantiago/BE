package utn.frc.backend.tutor.sac.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Getter @Setter @NoArgsConstructor @ToString
public class Alumno {
    @Id @Column(name = "pid", nullable = false)
    @NotNull(message = "Identificador es obligatorio")
    private Integer pid;

    @Basic @Column(name = "legajo", nullable = false, length = 16)
    @NotBlank(message = "Legajo es obligatorio")
    private String legajo;

    @OneToOne @JoinColumn(name = "pid")
    private Persona persona;

    @OneToMany(mappedBy = "alumno")
    @JsonIgnore
    private Set<Inscripcion> inscripciones = new HashSet<>();

    public Alumno update(Alumno alumno) {
        legajo = alumno.legajo;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alumno alumno = (Alumno) o;
        return Objects.equals(pid, alumno.pid) && Objects.equals(legajo, alumno.legajo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, legajo);
    }
}

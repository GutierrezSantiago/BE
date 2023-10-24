package utn.frc.backend.tutor.sac.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
public class Docente {
    @Id @Column(name = "pid", nullable = false)
    @NotNull(message = "Identificador es obligatorio")
    private Integer pid;

    @Basic @Column(name = "legajo", nullable = false, length = 16)
    @NotBlank(message = "Legajo es obligatorio")
    private String legajo;

    @OneToOne @JoinColumn(name = "pid")
    private Persona persona;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "docentes"
    )
    @JsonIgnore
    private Set<Materia> materias = new HashSet<>();

    public Docente(Integer pid, String legajo) {
        this.pid = pid;
        this.legajo = legajo;
    }

    public Docente(Persona persona, String legajo) {
        this.pid = persona.getPid();
        this.legajo = legajo;
        this.persona = persona;
    }

    // --------------------------------------------------------
    // - Se opta por la lógica "docentes que dictan tal materia"
    //   en vez de "materias dictadas por tal docente"
    //   (perfectamente se podría implementar ambas).
    // - Ergo, NO se implementan aquí:
    //   - addMateria()
    //   - removeMateria()
    // - Sí se implementan en Materia:
    //   - addDocente()
    //   - removeDocente()
    // - Comentar en clase.
    // - Implementación como actividad práctica para los
    //   alumnos.
    // --------------------------------------------------------

    private void cloneFrom(Docente docente) {
        legajo = docente.legajo;
        persona = docente.persona;
        materias = docente.materias;
    }

    private void cloneFrom(Docente docente, Integer pid) {
        this.pid = pid;
        cloneFrom(docente);
    }

    public void update(Docente docente) {
        cloneFrom(docente);
    }

    public void update(Docente docente, Integer pid) {
        cloneFrom(docente, pid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Docente docente = (Docente) o;
        return Objects.equals(pid, docente.pid) && Objects.equals(legajo, docente.legajo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, legajo);
    }

}

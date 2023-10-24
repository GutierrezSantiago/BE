package utn.frc.backend.tutor.sac.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
// ------------------------------------------------------------
// Ver Curso
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
// ------------------------------------------------------------
public class Materia {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mid", nullable = false)
    private Integer mid;
    @Basic
    @Column(name = "nombre", nullable = false, length = 64)
    @NotBlank(message = "Nombre es obligatorio")
    private String nombre;
    @Basic
    @Column(name = "descripcion", nullable = true, length = -1)
    @NotBlank(message = "Descripción es obligatoria")
    private String descripcion;
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "materia_docentes",
            joinColumns = @JoinColumn(name = "mid"),
            inverseJoinColumns = @JoinColumn(name = "pid")
    )
    @JsonIgnore
    private Set<Docente> docentes = new HashSet<>();

    // --------------------------------------------------------
    // - Se opta por la lógica "docentes que dictan tal materia"
    //   en vez de "materias dictadas por tal docente"
    //   (perfectamente se podría implementar ambas).
    // - Ergo, NO se implementa la contraparte en Docente.
    // - Comentar en clase.
    // - Contraparte como actividad práctica para los alumnos.
    // --------------------------------------------------------
    public void addDocente(Docente docente) {
        docentes.add(docente);
    }

    public void removeDocente(Integer pid) {
        Docente docente = docentes.stream().filter(d -> d.getPid() == pid).findFirst().orElse(null);
        if (docente != null) {
            docentes.remove(docente);
        }
    }
    // --------------------------------------------------------

    private void cloneFrom(Materia materia) {
        nombre = materia.nombre;
        descripcion = materia.descripcion;
    }

    private void cloneFrom(Materia materia, Integer mid) {
        this.mid = mid;
        cloneFrom(materia);
    }

    public void update(Materia materia) {
        cloneFrom(materia);
    }

    public void update(Materia materia, Integer pid) {
        cloneFrom(materia, pid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Materia materia = (Materia) o;
        return Objects.equals(mid, materia.mid) && Objects.equals(nombre, materia.nombre) && Objects.equals(descripcion, materia.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mid, nombre, descripcion);
    }

}

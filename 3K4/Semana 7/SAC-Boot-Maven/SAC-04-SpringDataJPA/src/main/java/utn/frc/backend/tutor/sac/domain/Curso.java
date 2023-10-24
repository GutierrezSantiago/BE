package utn.frc.backend.tutor.sac.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter @Setter @NoArgsConstructor
public class Curso {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid", nullable = false)
    private Integer cid;
    @Basic
    @Column(name = "mid", nullable = false)
    @NotNull(message = "ID de materia es obligatorio")
    private Integer mid;
    @Basic
    @Column(name = "pid", nullable = false)
    @NotNull(message = "ID de docente es obligatorio")
    private Integer pid;
    @Basic
    @Column(name = "nombre", nullable = false, length = 64)
    @NotBlank(message = "Nombre es obligatorio")
    private String nombre;
    @Basic
    @Column(name = "descripcion", nullable = true, length = -1)
    private String descripcion;
    @Basic
    @Column(name = "cupo", nullable = false)
    @NotNull(message = "Cupo es obligatorio")
    private Short cupo;
    @Basic
    @Column(name = "finicio", nullable = false)
    @NotNull(message = "Fecha de inicio es obligatoria")
    private Timestamp finicio;
    @Basic
    @Column(name = "ffin", nullable = true)
    private Timestamp ffin;

    // -- begin -----------------------------------------------
    // Abajo, para relaciones many-to-one, 2 opciones (ver alternativas comentadas):
    //   1. Usar efectivamente @ManyToOne (materia):
    //      - Pro: Es consistente/fiel con la realidad, pues se trata, de hecho, de
    //             una relación many-to-one.
    //      - Con: Requiere anotar la clase asociada (Materia) con
    //             @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}).
    //             De no hacerlo, se lanzará una excepción (a menos que la relación
    //             se defina con FetchType.EAGER) de tipo InvalidDefinitionException.
    //             CAUSA: usando FetchType.LAZY, al momento de ser invocado para
    //                    serialización (por Jackson), éste (el fetch) NO se cuentra
    //                    inicializado todavía.
    //   2. Usar @OneToOne (docente):
    //      - Pro: Funciona (al menos si no necesitamos definir/notar la relación
    //             inversa en la contraparte [Docente], como es el nuestro caso).
    //      - Con: No refleja realidad, pudiendo confundir más adelante a quien
    //             necesite mantener/modificar código (terceros y/o, incluso,
    //             nosotros mismos). Obviamente, esto se puede mitigar mediante
    //             el/los correspondiente/s comentario/s.
    // (LONG LIVE PLAIN OLD, SIMPLE, CLASSIC AND VANILLA JDBC APPROACH !!!)
    // -- end -------------------------------------------------

    // --- begin opción 1 (@ManyToOne) ------------------------
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "mid",
            nullable = false,
            insertable = false,
            updatable = false
    )
    //@OnDelete(action = OnDeleteAction.RESTRICT)
    private Materia materia;
    // --- end opción 1 ---------------------------------------

    // --- begin opción 2 (@OneToOne) -------------------------
    @OneToOne @JoinColumn(name = "pid", insertable = false, updatable = false)
    private Docente docente;
    // --- end opción 2 ---------------------------------------

    @OneToMany(mappedBy = "curso")
    @JsonIgnore
    private Set<Inscripcion> inscripciones = new HashSet<>();

    // Como Set
    public Set<Alumno> getAlumnos() {
        return inscripciones.stream().map(i -> i.getAlumno()).collect(Collectors.toSet());
    }
    // O también como List
    public List<Alumno> getAlumnosList() {
        return inscripciones.stream().map(i -> i.getAlumno()).toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return Objects.equals(cid, curso.cid) && Objects.equals(mid, curso.mid) && Objects.equals(pid, curso.pid) && Objects.equals(nombre, curso.nombre) && Objects.equals(descripcion, curso.descripcion) && Objects.equals(cupo, curso.cupo) && Objects.equals(finicio, curso.finicio) && Objects.equals(ffin, curso.ffin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, mid, pid, nombre, descripcion, cupo, finicio, ffin);
    }

}

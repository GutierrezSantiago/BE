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


// ------------------------------------------------------------
// Introduciendo lombok
// https://jpa-buddy.com/blog/lombok-and-jpa-what-may-go-wrong/
// ------------------------------------------------------------

@Entity
@Getter @Setter @NoArgsConstructor
public class Alumno { // extends Persona {  // NO good; comentar en clase (ver Persona)
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

    public Alumno(Integer pid, String legajo) {
        this.pid = pid;
        this.legajo = legajo;
    }

    public Alumno(Persona persona, String legajo) {
        this.pid = persona.getPid();
        this.legajo = legajo;
        this.persona = persona;
    }

    private void cloneFrom(Alumno alumno) {
        legajo = alumno.legajo;
        persona = alumno.persona;
    }

    private void cloneFrom(Alumno alumno, Integer pid) {
        this.pid = pid;
        cloneFrom(alumno);
    }

    public void update(Alumno alumno) {
        cloneFrom(alumno);
    }

    public void update(Alumno alumno, Integer pid) {
        cloneFrom(alumno, pid);
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

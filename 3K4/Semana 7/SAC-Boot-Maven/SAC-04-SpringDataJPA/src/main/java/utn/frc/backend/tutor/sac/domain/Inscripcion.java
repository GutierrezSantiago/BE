package utn.frc.backend.tutor.sac.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@IdClass(InscripcionPK.class)
@Getter @Setter @NoArgsConstructor
public class Inscripcion {
    // --------------------------------------------------------
    @Id
    @Column(name = "cid", nullable = false)
    private Integer cid;
    @Id
    @Column(name = "pid", nullable = false)
    private Integer pid;
    // --------------------------------------------------------
    ////En caso de usar EmbeddedId, comentar:
    ////  - @IdClass arriba
    ////  - Constructores abajo
    ////  - equals y hashCode abajo
    //@EmbeddedId
    //private InscripcionId iid;
    // --------------------------------------------------------

    @Basic
    @Column(name = "cfinal", nullable = true)
    private Short cfinal;
    @ManyToOne
    @JoinColumn(name = "cid", nullable = false, insertable = false, updatable = false)
    private Curso curso;
    @ManyToOne
    @JoinColumn(name = "pid", nullable = false, insertable = false, updatable = false)
    private Alumno alumno;

    public Inscripcion(Integer cid, Integer pid) {
        // ----------------------------------------------------
        this.cid = cid;
        this.pid = pid;
        // ----------------------------------------------------
        //this.iid = new InscripcionId(cid, pid);
        // ----------------------------------------------------
    }

    public Inscripcion(Integer cid, Integer pid, Short cfinal) {
        this(cid, pid);
        this.cfinal = cfinal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inscripcion that = (Inscripcion) o;
        // ----------------------------------------------------
        return Objects.equals(cid, that.cid) && Objects.equals(pid, that.pid) && Objects.equals(cfinal, that.cfinal);
        // ----------------------------------------------------
        //return Objects.equals(iid, that.iid) && Objects.equals(cfinal, that.cfinal);
        // ----------------------------------------------------
    }

    @Override
    public int hashCode() {
        // ----------------------------------------------------
        return Objects.hash(cid, pid, cfinal);
        // ----------------------------------------------------
        //return Objects.hash(iid, cfinal);
        // ----------------------------------------------------
    }
}

package utn.frc.backend.tutor.sac.domain.auto;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "inscripcion", schema = "public", catalog = "sacdb")
@IdClass(InscripcionAutoPK.class)
public class InscripcionAuto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cid", nullable = false)
    private Integer cid;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "pid", nullable = false)
    private Integer pid;
    @Basic
    @Column(name = "cfinal", nullable = true)
    private Short cfinal;
    @ManyToOne
    @JoinColumn(name = "cid", referencedColumnName = "cid", nullable = false)
    private CursoAuto cursoByCid;
    @ManyToOne
    @JoinColumn(name = "pid", referencedColumnName = "pid", nullable = false)
    private AlumnoAuto alumnoByPid;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Short getCfinal() {
        return cfinal;
    }

    public void setCfinal(Short cfinal) {
        this.cfinal = cfinal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InscripcionAuto that = (InscripcionAuto) o;
        return Objects.equals(cid, that.cid) && Objects.equals(pid, that.pid) && Objects.equals(cfinal, that.cfinal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, pid, cfinal);
    }

    public CursoAuto getCursoByCid() {
        return cursoByCid;
    }

    public void setCursoByCid(CursoAuto cursoByCid) {
        this.cursoByCid = cursoByCid;
    }

    public AlumnoAuto getAlumnoByPid() {
        return alumnoByPid;
    }

    public void setAlumnoByPid(AlumnoAuto alumnoByPid) {
        this.alumnoByPid = alumnoByPid;
    }
}

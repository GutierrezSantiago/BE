package utn.frc.backend.tutor.sac.domain.auto;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "materia_docentes", schema = "public", catalog = "sacdb")
@IdClass(MateriaDocentesAutoPK.class)
public class MateriaDocentesAuto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "mid", nullable = false)
    private Integer mid;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "pid", nullable = false)
    private Integer pid;
    @OneToMany(mappedBy = "materiaDocentes")
    private Collection<CursoAuto> cursos;
    @ManyToOne
    @JoinColumn(name = "mid", referencedColumnName = "mid", nullable = false)
    private MateriaAuto materiaByMid;
    @ManyToOne
    @JoinColumn(name = "pid", referencedColumnName = "pid", nullable = false)
    private DocenteAuto docenteByPid;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MateriaDocentesAuto that = (MateriaDocentesAuto) o;
        return Objects.equals(mid, that.mid) && Objects.equals(pid, that.pid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mid, pid);
    }

    public Collection<CursoAuto> getCursos() {
        return cursos;
    }

    public void setCursos(Collection<CursoAuto> cursos) {
        this.cursos = cursos;
    }

    public MateriaAuto getMateriaByMid() {
        return materiaByMid;
    }

    public void setMateriaByMid(MateriaAuto materiaByMid) {
        this.materiaByMid = materiaByMid;
    }

    public DocenteAuto getDocenteByPid() {
        return docenteByPid;
    }

    public void setDocenteByPid(DocenteAuto docenteByPid) {
        this.docenteByPid = docenteByPid;
    }
}

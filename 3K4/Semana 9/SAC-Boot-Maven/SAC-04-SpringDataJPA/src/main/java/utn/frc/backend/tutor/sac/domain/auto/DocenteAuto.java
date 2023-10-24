package utn.frc.backend.tutor.sac.domain.auto;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "docente", schema = "public", catalog = "sacdb")
public class DocenteAuto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "pid", nullable = false)
    private Integer pid;
    @Basic
    @Column(name = "legajo", nullable = false, length = 16)
    private String legajo;
    @OneToOne
    @JoinColumn(name = "pid", referencedColumnName = "pid", nullable = false)
    private PersonaAuto personaByPid;
    @OneToMany(mappedBy = "docenteByPid")
    private Collection<MateriaDocentesAuto> materiaDocentesByPid;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocenteAuto that = (DocenteAuto) o;
        return Objects.equals(pid, that.pid) && Objects.equals(legajo, that.legajo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, legajo);
    }

    public PersonaAuto getPersonaByPid() {
        return personaByPid;
    }

    public void setPersonaByPid(PersonaAuto personaByPid) {
        this.personaByPid = personaByPid;
    }

    public Collection<MateriaDocentesAuto> getMateriaDocentesByPid() {
        return materiaDocentesByPid;
    }

    public void setMateriaDocentesByPid(Collection<MateriaDocentesAuto> materiaDocentesByPid) {
        this.materiaDocentesByPid = materiaDocentesByPid;
    }
}

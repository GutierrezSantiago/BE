package utn.frc.backend.tutor.sac.domain.auto;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "curso", schema = "public", catalog = "sacdb")
public class CursoAuto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cid", nullable = false)
    private Integer cid;
    @Basic
    @Column(name = "mid", nullable = false)
    private Integer mid;
    @Basic
    @Column(name = "pid", nullable = false)
    private Integer pid;
    @Basic
    @Column(name = "nombre", nullable = false, length = 64)
    private String nombre;
    @Basic
    @Column(name = "descripcion", nullable = true, length = -1)
    private String descripcion;
    @Basic
    @Column(name = "cupo", nullable = false)
    private Short cupo;
    @Basic
    @Column(name = "finicio", nullable = false)
    private Timestamp finicio;
    @Basic
    @Column(name = "ffin", nullable = true)
    private Timestamp ffin;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(
                    name = "mid",
                    referencedColumnName = "mid",
                    nullable = false,
                    insertable = false,
                    updatable = false
            ),
            @JoinColumn(
                    name = "pid",
                    referencedColumnName = "pid",
                    nullable = false,
                    insertable = false,
                    updatable = false
            )
    })
    private MateriaDocentesAuto materiaDocentes;
    @OneToMany(mappedBy = "cursoByCid")
    private Collection<InscripcionAuto> inscripcionsByCid;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Short getCupo() {
        return cupo;
    }

    public void setCupo(Short cupo) {
        this.cupo = cupo;
    }

    public Timestamp getFinicio() {
        return finicio;
    }

    public void setFinicio(Timestamp finicio) {
        this.finicio = finicio;
    }

    public Timestamp getFfin() {
        return ffin;
    }

    public void setFfin(Timestamp ffin) {
        this.ffin = ffin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CursoAuto cursoAuto = (CursoAuto) o;
        return Objects.equals(cid, cursoAuto.cid) && Objects.equals(mid, cursoAuto.mid) && Objects.equals(pid, cursoAuto.pid) && Objects.equals(nombre, cursoAuto.nombre) && Objects.equals(descripcion, cursoAuto.descripcion) && Objects.equals(cupo, cursoAuto.cupo) && Objects.equals(finicio, cursoAuto.finicio) && Objects.equals(ffin, cursoAuto.ffin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, mid, pid, nombre, descripcion, cupo, finicio, ffin);
    }

    public MateriaDocentesAuto getMateriaDocentes() {
        return materiaDocentes;
    }

    public void setMateriaDocentes(MateriaDocentesAuto materiaDocentes) {
        this.materiaDocentes = materiaDocentes;
    }

    public Collection<InscripcionAuto> getInscripcionsByCid() {
        return inscripcionsByCid;
    }

    public void setInscripcionsByCid(Collection<InscripcionAuto> inscripcionsByCid) {
        this.inscripcionsByCid = inscripcionsByCid;
    }
}

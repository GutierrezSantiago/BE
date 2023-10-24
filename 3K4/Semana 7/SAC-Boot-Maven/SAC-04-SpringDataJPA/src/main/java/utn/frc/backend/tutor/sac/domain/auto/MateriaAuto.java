package utn.frc.backend.tutor.sac.domain.auto;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "materia", schema = "public", catalog = "sacdb")
public class MateriaAuto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "mid", nullable = false)
    private Integer mid;
    @Basic
    @Column(name = "nombre", nullable = false, length = 64)
    private String nombre;
    @Basic
    @Column(name = "descripcion", nullable = true, length = -1)
    private String descripcion;
    @OneToMany(mappedBy = "materiaByMid")
    private Collection<MateriaDocentesAuto> materiaDocentesByMid;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MateriaAuto that = (MateriaAuto) o;
        return Objects.equals(mid, that.mid) && Objects.equals(nombre, that.nombre) && Objects.equals(descripcion, that.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mid, nombre, descripcion);
    }

    public Collection<MateriaDocentesAuto> getMateriaDocentesByMid() {
        return materiaDocentesByMid;
    }

    public void setMateriaDocentesByMid(Collection<MateriaDocentesAuto> materiaDocentesByMid) {
        this.materiaDocentesByMid = materiaDocentesByMid;
    }
}

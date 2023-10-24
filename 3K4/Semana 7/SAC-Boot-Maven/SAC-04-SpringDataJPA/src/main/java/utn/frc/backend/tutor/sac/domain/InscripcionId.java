package utn.frc.backend.tutor.sac.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter @Setter @NoArgsConstructor
public class InscripcionId implements Serializable {
    private Integer cid;
    private Integer pid;

    public InscripcionId(Integer cid, Integer pid) {
        this.cid = cid;
        this.pid = pid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InscripcionId that = (InscripcionId) o;
        return Objects.equals(cid, that.cid) && Objects.equals(pid, that.pid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, pid);
    }

}

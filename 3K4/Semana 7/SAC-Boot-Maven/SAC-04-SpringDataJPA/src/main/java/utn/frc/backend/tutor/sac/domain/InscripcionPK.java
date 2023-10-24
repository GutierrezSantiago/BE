package utn.frc.backend.tutor.sac.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/*
    Mencionar Embedded y Embeddable como alternativa
    (https://jpa-buddy.com/blog/the-ultimate-guide-on-composite-ids-in-jpa-entities/)
    (https://www.baeldung.com/jpa-composite-primary-keys)
        * Pros (jpql)
        * Cons (jpql)
*/

@Getter @Setter @NoArgsConstructor
public class InscripcionPK implements Serializable {
    @Id
    @Column(name = "cid", nullable = false)
    private Integer cid;
    @Id
    @Column(name = "pid", nullable = false)
    private Integer pid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InscripcionPK that = (InscripcionPK) o;
        return Objects.equals(cid, that.cid) && Objects.equals(pid, that.pid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, pid);
    }
}

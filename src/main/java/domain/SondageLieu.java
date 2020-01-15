package domain;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("SL")
public class SondageLieu extends Sondage {
    private List<LieuReunion> lieuPossibles;

    @ManyToMany
    @JoinTable(name="SondageLieu_LieuReunion")
    public List<LieuReunion> getLieuPossibles() {
        return lieuPossibles;
    }

    public void setLieuPossibles(List<LieuReunion> lieuPossibles) {
        this.lieuPossibles = lieuPossibles;
    }
}

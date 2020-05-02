package fr.istic.sir.domain;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("PSL")
public class ParticipationSondageLieu extends Participation {

    private LieuReunion lieuChoisi;
    private SondageLieu sondage;

    @OneToOne (cascade = CascadeType.ALL)
    public LieuReunion getLieuChoisi() {
        return lieuChoisi;
    }

    public void setLieuChoisi(LieuReunion lieuChoisi) {
        this.lieuChoisi = lieuChoisi;
    }

    @OneToOne
    public SondageLieu getSondage() {
        return sondage;
    }

    public void setSondage(SondageLieu sondage) {
        this.sondage = sondage;
    }
}


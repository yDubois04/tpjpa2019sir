package fr.istic.sir.domain;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("PSD")
public class ParticipationSondageDate extends Participation {

    private DateReunion dateChoisie;
    private SondageDate sondage;

    @OneToOne (cascade = CascadeType.ALL)
    public DateReunion getDateChoisie() {
        return dateChoisie;
    }

    public void setDateChoisie(DateReunion dateChoisie) {
        this.dateChoisie = dateChoisie;
    }

    @OneToOne
    public SondageDate getSondage() {
        return sondage;
    }

    public void setSondage(SondageDate sondage) {
        this.sondage = sondage;
    }
}

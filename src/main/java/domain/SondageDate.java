package domain;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("SD")
public class SondageDate extends Sondage {
    private List<DateReunion> datesPossibles;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="SondageDate_DateReunion")
    public List<DateReunion> getDatesPossibles() {
        return datesPossibles;
    }

    public void setDatesPossibles(List<DateReunion> datesPossibles) {
        this.datesPossibles = datesPossibles;
    }
}

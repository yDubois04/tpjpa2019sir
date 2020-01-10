package domain;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("SD")
public class SondageDate extends Sondage {
    private List<DateReunion> datesPossibles;

    @Override
    public List<DateReunion> getDatesPossibles() {
        return datesPossibles;
    }

    @Override
    public void setDatesPossibles(List<DateReunion> datesPossibles) {
        this.datesPossibles = datesPossibles;
    }
}

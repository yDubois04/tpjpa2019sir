package fr.istic.sir.dto;

import fr.istic.sir.domain.Sondage;
import fr.istic.sir.domain.SondageDate;

import java.util.ArrayList;
import java.util.List;

public class DateReunionDTO {

    private String date;
    private boolean contientPauseDej;
    private List<String> sondages;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isContientPauseDej() {
        return contientPauseDej;
    }

    public void setContientPauseDej(boolean contientPauseDej) {
        this.contientPauseDej = contientPauseDej;
    }

    public List<String> getSondages() {
        return sondages;
    }

    public void setSondages(List<SondageDate> sondages) {
        ArrayList<String> list = new ArrayList<String>();

        if (sondages != null) {
            for (Sondage sondage : sondages) {
                list.add(sondage.getLien());
            }
        }
        this.sondages = list;
    }
}

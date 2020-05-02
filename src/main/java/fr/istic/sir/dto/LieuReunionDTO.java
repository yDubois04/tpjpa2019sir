package fr.istic.sir.dto;

import fr.istic.sir.domain.LieuReunion;
import fr.istic.sir.domain.Sondage;
import fr.istic.sir.domain.SondageLieu;

import java.util.ArrayList;
import java.util.List;

public class LieuReunionDTO {

    private String nomLieu;
    private List<String> sondages;

    public String getNomLieu() {
        return nomLieu;
    }

    public void setNomLieu(String nomLieu) {
        this.nomLieu = nomLieu;
    }

    public List<String> getSondages() {
        return sondages;
    }

    public void setSondages(List<SondageLieu> sondages) {
        ArrayList<String> list = new ArrayList<String>();
        if (sondages != null) {
            for (Sondage sondage : sondages) {
                list.add(sondage.getLien());
            }
        }
        this.sondages = list;
    }
}

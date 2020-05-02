package fr.istic.sir.dto;

import fr.istic.sir.domain.DateReunion;
import fr.istic.sir.domain.LieuReunion;
import fr.istic.sir.domain.Utilisateur;

import java.util.ArrayList;
import java.util.List;

public class SondageDTO {
    private String lien;
    private String utilisateur;
    private List<String> lieux;
    private List<String> dates;

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        if (utilisateur != null) {
            this.utilisateur = utilisateur.getMail();
        }
    }

    public List<String> getLieux() {
        return lieux;
    }

    public void setLieux(List<LieuReunion> listLieux) {
        ArrayList<String> list = new ArrayList<String>();
        if (listLieux != null) {
            for (LieuReunion lieu : listLieux) {
                list.add(lieu.getNomLieu());
            }
        }
        this.lieux = list;
    }

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<DateReunion> listDates) {
        ArrayList<String> list = new ArrayList<String>();
        if (listDates != null) {
            for (DateReunion date : listDates) {
                list.add(date.getDate());
            }
        }
        this.dates = list;
    }


}

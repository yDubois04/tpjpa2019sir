package fr.istic.sir.dto;

import fr.istic.sir.domain.DateReunion;
import fr.istic.sir.domain.LieuReunion;
import fr.istic.sir.domain.Utilisateur;

import java.util.ArrayList;
import java.util.List;

public class SondageDTO {
    private String lien;
    private String utilisateur;
    private List<LieuReunion> lieux;
    private List<DateReunion> dates;

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

    public List<LieuReunion> getLieux() {
        return lieux;
    }

    public void setLieux(List<LieuReunion> listLieux) {
        ArrayList<LieuReunion> list = new ArrayList<LieuReunion>();
        if (listLieux != null) {
            for (LieuReunion lieu : listLieux) {
                LieuReunion l = new LieuReunion();
                l.setId(lieu.getId());
                l.setNomLieu(lieu.getNomLieu());
                list.add(l);
            }
        }
        this.lieux = list;
    }

    public List<DateReunion> getDates() {
        return dates;
    }

    public void setDates(List<DateReunion> listDates) {
        ArrayList<DateReunion> list = new ArrayList<DateReunion>();
        if (listDates != null) {
            for (DateReunion date : listDates) {
                DateReunion d = new DateReunion();
                d.setId(d.getId());
                d.setContientPauseDej(date.isContientPauseDej());
                d.setDate(date.getDate());
                list.add(d);
            }
        }
        this.dates = list;
    }


}

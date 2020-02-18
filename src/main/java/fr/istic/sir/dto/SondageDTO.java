package fr.istic.sir.dto;

import domain.DateReunion;
import domain.LieuReunion;
import domain.Utilisateur;

import java.util.ArrayList;
import java.util.List;

public class SondageDTO {
    private String lien;
    private List<String> participants;
    private String utilisateur;
    private List<String> lieux;
    private List<String> dates;

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Utilisateur> users) {
        ArrayList <String> names = new ArrayList<String>();
        if (users != null) {
            for (Utilisateur user : users) {
                names.add(user.getNom());
            }
        }
        this.participants = names;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        if (utilisateur !=null) {
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

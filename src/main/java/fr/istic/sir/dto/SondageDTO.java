package fr.istic.sir.dto;

import domain.DateReunion;
import domain.LieuReunion;
import domain.Utilisateur;

import java.util.ArrayList;
import java.util.List;

public class SondageDTO {
    private String lien;
    private List<String> names;
    private String utilisateur;
    private List<String> lieux;
    private List<String> dates;

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<Utilisateur> users) {
        ArrayList <String> names = new ArrayList<String>();
        for (Utilisateur user : users) {
            names.add(user.getNom());
        }
        this.names = names;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur.getMail();
    }


    public List<String> getLieux() {
        return lieux;
    }

    public void setLieux(List<LieuReunion> listLieux) {
        ArrayList<String> list = new ArrayList<String>();
        for (LieuReunion lieu : listLieux) {
            list.add(lieu.getNomLieu());
        }
        this.lieux = list;
    }

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<DateReunion> listDates) {
        ArrayList<String> list = new ArrayList<String>();
        for (DateReunion date : listDates) {
            list.add(date.getDate());
        }
        this.lieux = list;
    }


}

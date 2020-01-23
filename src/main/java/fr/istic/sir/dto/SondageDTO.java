package fr.istic.sir.dto;

import domain.Utilisateur;

import java.util.ArrayList;
import java.util.List;

public class SondageDTO {
    private String lien;
    private List<String> names;
    private String utilisateur;

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

}

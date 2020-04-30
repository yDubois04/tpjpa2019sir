package fr.istic.sir.dto;

import domain.Sondage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UtilisateurDTO {

    private String nom;
    private String prenom;
    private String mail;
    private List<String> lienSondageCrees;
    private List<String> lienSondageParticipes;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<String> getLienSondageCrees() {
        return lienSondageCrees;
    }

    public void setLienSondageCrees (Collection<Sondage> sondages) {
        ArrayList<String> listLien = new ArrayList<String>();
        if (sondages != null) {
            for (Sondage sondage : sondages) {
                listLien.add(sondage.getLien());
            }
        }
        this.lienSondageCrees = listLien;
    }
}

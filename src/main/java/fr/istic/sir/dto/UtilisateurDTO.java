package fr.istic.sir.dto;

import fr.istic.sir.domain.Allergie;
import fr.istic.sir.domain.PrefAlim;
import fr.istic.sir.domain.Sondage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UtilisateurDTO {

    private String nom;
    private String prenom;
    private String mail;
    private Collection<String> lienSondageCrees;
    private Collection<Allergie> allergies;
    private Collection<PrefAlim> preferences;

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

    public Collection<String> getLienSondageCrees() {
        return lienSondageCrees;
    }

    public void setLienSondageCrees (Collection <Sondage> sondages) {
        ArrayList<String> listLien = new ArrayList<String>();
        if (sondages != null) {
            for (Sondage sondage : sondages) {
                listLien.add(sondage.getLien());
            }
        }
        this.lienSondageCrees = listLien;
    }
    public Collection<Allergie> getAllergies() {
        return allergies;
    }

    public void setAllergies(Collection<Allergie> allergies) {
        this.allergies = allergies;
    }

    public Collection<PrefAlim> getPreferences() {
        return preferences;
    }

    public void setPreferences(Collection<PrefAlim> preferences) {
        this.preferences = preferences;
    }
}

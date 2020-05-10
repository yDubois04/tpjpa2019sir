package fr.istic.sir.dao;

import fr.istic.sir.domain.Allergie;
import fr.istic.sir.domain.LieuReunion;
import fr.istic.sir.domain.PrefAlim;
import fr.istic.sir.domain.Utilisateur;
import jpa.EntityManagerHelper;

import java.util.List;

public class UtilisateurDAO {

    public Utilisateur findByEmail (String email) {
        return EntityManagerHelper.getEntityManager().find(Utilisateur.class, email);

    }

    public Utilisateur save (Utilisateur u ) {
        EntityManagerHelper.beginTransaction();
        if (u.getMail() != null) {
            EntityManagerHelper.getEntityManager().merge(u);

        } else {
            EntityManagerHelper.getEntityManager().persist(u);
        }
        EntityManagerHelper.commit();
        return u;
    }

    public List<Utilisateur> findAll() {
        return EntityManagerHelper.getEntityManager().createQuery("select u from Utilisateur as u", Utilisateur.class)
                .getResultList();
    }
    public void updateAllergies (String email, List<Allergie> allergies) {
        Utilisateur u = this.findByEmail(email);
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        u.setAllergies(allergies);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    public void updatePrefAlim (String email, List<PrefAlim> prefs) {
        Utilisateur u = this.findByEmail(email);
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        u.setPrefAlim(prefs);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }
}

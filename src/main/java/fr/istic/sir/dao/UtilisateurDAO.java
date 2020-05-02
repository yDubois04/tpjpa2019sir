package fr.istic.sir.dao;

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
}

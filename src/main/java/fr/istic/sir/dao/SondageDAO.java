package fr.istic.sir.dao;

import domain.Sondage;
import domain.SondageDate;
import domain.SondageLieu;
import jpa.EntityManagerHelper;
import org.modelmapper.ModelMapper;

import java.util.List;

public class SondageDAO {

    public Sondage findByLien (String lien) {
        return EntityManagerHelper.getEntityManager().find(Sondage.class, lien);
    }

    public Sondage save(Sondage s) {
        EntityManagerHelper.beginTransaction();
        if (s.getLien() != null) {
            EntityManagerHelper.getEntityManager().merge(s);

        } else {
            EntityManagerHelper.getEntityManager().persist(s);
        }
        EntityManagerHelper.commit();
        return s;
    }

    public List<Sondage> findAll() {
        return EntityManagerHelper.getEntityManager().createQuery("select s from Sondage as s", Sondage.class)
                .getResultList();
    }

    public List<SondageDate> findSondageDate (){
        return EntityManagerHelper.getEntityManager().createQuery("select s from SondageDate as s", SondageDate.class)
                .getResultList();
    }

    public List<SondageLieu> findSondageLieu (){
        return EntityManagerHelper.getEntityManager().createQuery("select s from SondageLieu as s", SondageLieu.class)
                .getResultList();
    }

    public List<Sondage> findSondageByUtilisateur(String email) {
        return EntityManagerHelper.getEntityManager().createQuery("select s from Sondage as s where s.utilisateur_id = :mail", Sondage.class)
                .setParameter("mail",email).getResultList();
    }
}

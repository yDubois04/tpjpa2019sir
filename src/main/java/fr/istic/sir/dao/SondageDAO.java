package fr.istic.sir.dao;

import fr.istic.sir.domain.*;
import jpa.EntityManagerHelper;
import java.util.List;

public class SondageDAO {

    public Sondage findByLien (String lien) {
        return EntityManagerHelper.getEntityManager().find(Sondage.class, lien);
    }

    public SondageDate findSDByLien (String lien) {
        return EntityManagerHelper.getEntityManager().find(SondageDate.class, lien);
    }

    public SondageLieu findSLByLien (String lien) {
        return EntityManagerHelper.getEntityManager().find(SondageLieu.class, lien);
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

    public List <LieuReunion> findLieux () {
        return EntityManagerHelper.getEntityManager().createQuery("select l from LieuReunion as l", LieuReunion.class)
                .getResultList();
    }

    public List <DateReunion> findDates () {
        return EntityManagerHelper.getEntityManager().createQuery("select d from DateReunion as d", DateReunion.class)
                .getResultList();
    }

    public DateReunion findDate (String lienS, String date) {
        return EntityManagerHelper.
                getEntityManager()
                .createQuery("select d" +
                                     " from DateReunion as d" +
                                      " JOIN d.sondages s" +
                                      " WHERE s.lien = :lien" +
                                      " AND d.date = :date", DateReunion.class)
                .setParameter("lien", lienS)
                .setParameter("date", date)
                .getSingleResult();
    }

    public LieuReunion findLieu (String lienS, String lieu) {
        return EntityManagerHelper.
                getEntityManager()
                .createQuery("select l" +
                        " from LieuReunion as l" +
                        " JOIN l.sondages s" +
                        " WHERE s.lien = :lien" +
                        " AND l.nomLieu = :lieu", LieuReunion.class)
                .setParameter("lien", lienS)
                .setParameter("lieu", lieu)
                .getSingleResult();
    }
}

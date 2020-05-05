package fr.istic.sir.dao;

import fr.istic.sir.domain.Participation;
import fr.istic.sir.domain.ParticipationSondageDate;
import fr.istic.sir.domain.ParticipationSondageLieu;
import jpa.EntityManagerHelper;

import java.util.List;

public class ParticipationDAO {


    public Participation findById (String id) {
        return EntityManagerHelper.getEntityManager().find(Participation.class, id);
    }

    public Participation save(Participation s) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(s);
        EntityManagerHelper.commit();
        return s;
    }

    public List<ParticipationSondageDate> findAllParticipationSDate (){
        return EntityManagerHelper.getEntityManager().createQuery("select p from ParticipationSondageDate as p", ParticipationSondageDate.class)
                .getResultList();
    }

    public List<ParticipationSondageLieu> findAllParticipationSLieu (){
        return EntityManagerHelper.getEntityManager().createQuery("select p from ParticipationSondageLieu as p", ParticipationSondageLieu.class)
                .getResultList();
    }

    public List<ParticipationSondageLieu> countParticipationsSL (){
        return EntityManagerHelper.getEntityManager()
                .createQuery("SELECT s.sondage.lien, s.lieuChoisi.nomLieu, COUNT(s)" +
                        " FROM ParticipationSondageLieu as s" +
                        " GROUP BY s.sondage.lien, s.lieuChoisi.nomLieu")
                .getResultList();
    }

    public List<ParticipationSondageDate> countParticipationsSD (){
        return EntityManagerHelper.getEntityManager()
                .createQuery("SELECT s.sondage.lien, s.dateChoisie.date, COUNT(s)" +
                        " FROM ParticipationSondageDate as s" +
                        " GROUP BY s.sondage.lien, s.dateChoisie.date")
                .getResultList();
    }

}

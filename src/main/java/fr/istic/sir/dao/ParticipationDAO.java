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
}

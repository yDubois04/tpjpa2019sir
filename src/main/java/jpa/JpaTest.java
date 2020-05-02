package jpa;

import fr.istic.sir.domain.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JpaTest {

	EntityManager manager;
	Utilisateur u1;
	Utilisateur u2;
	Utilisateur u3;

	public JpaTest(EntityManager manager){
		this.manager = manager;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManager manager = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		JpaTest test = new JpaTest(manager);
		tx.begin();
		try {
			test.createUsers();
			test.createSondages();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

		test.displayUserList();
		test.displaySondageDate();
		test.displaySondageLieu();

		manager.close();
	}

	public void createSondages () {
		SondageDate sDate = new SondageDate();
		ArrayList <DateReunion> dates = new ArrayList<DateReunion>();
		DateReunion date1 = new DateReunion();
		date1.setDate("01/03/2020");
		DateReunion date2 = new DateReunion();
		date1.setDate("05/03/2020");
		Collections.addAll(dates,date1,date2);
		sDate.setDatesPossibles(dates);
		sDate.setCreateur(u1);
		sDate.setLien("sondage1.com");
		ArrayList <Utilisateur> participants = new ArrayList<Utilisateur>();
		Collections.addAll(participants,u2);
		manager.persist(sDate);

		SondageLieu sLieu = new SondageLieu();
		ArrayList <LieuReunion> lieux = new ArrayList<LieuReunion>();
		LieuReunion lieu1 = new LieuReunion();
		lieu1.setNomLieu("Bureau");
		LieuReunion lieu2 = new LieuReunion();
		lieu2.setNomLieu("Salle 3");
		Collections.addAll(lieux,lieu1,lieu2);
		sLieu.setLieuPossibles(lieux);
		sLieu.setCreateur(u3);
		sLieu.setLien("sondage2.com");
		manager.persist(sLieu);
	}

	public void createUsers(){
		u1 = new Utilisateur();
		u1.setNom("user");
		u1.setMail("user.one@mail.com");
		u1.setPrenom("one");
		manager.persist(u1);

		u2 = new Utilisateur();
		u2.setNom("user");
		u2.setMail("user.two@mail.com");
		u2.setPrenom("two");
		manager.persist(u2);

		u3 = new Utilisateur();
		u3.setNom("user");
		u3.setMail("user.three@mail.com");
		u3.setPrenom("three");
		manager.persist(u3);
	}

	public void displayUserList(){
		String s = "SELECT u FROM Utilisateur as u";

		Query q = manager.createQuery(s,Utilisateur.class);
		List<Utilisateur> res = q.getResultList();

		for(Utilisateur u : res){
			System.err.println(u.getMail());
		}
	}

	public void displaySondageLieu(){
		String s = "SELECT s FROM SondageLieu as s";

		Query q = manager.createQuery(s,SondageLieu.class);
		List<SondageLieu> res = q.getResultList();

		for(SondageLieu sondageLieu : res){
			System.err.println(sondageLieu.getLieuPossibles());
		}
	}


	public void displaySondageDate(){
		String s = "SELECT s FROM SondageDate as s";

		Query q = manager.createQuery(s,SondageDate.class);
		List<SondageDate> res = q.getResultList();

		for(SondageDate sondageDate : res){
			System.err.println(sondageDate.getDatesPossibles());
		}
	}

}

package jpa;

import domain.Utilisateur;

import javax.persistence.*;
import java.util.List;

public class JpaTest {

	EntityManager manager;

	public JpaTest(EntityManager manager){
		this.manager = manager;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();

		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {

			test.createUsers();


		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

		test.userList();

		manager.close();
		factory.close();
	}

	public void createUsers(){
		Utilisateur u1 = new Utilisateur();
		u1.setNom("user");
		u1.setMail("user.one@mail.com");
		u1.setPrenom("one");
		manager.persist(u1);

		Utilisateur u2 = new Utilisateur();
		u2.setNom("user");
		u2.setMail("user.two@mail.com");
		u2.setPrenom("two");
		manager.persist(u2);

		Utilisateur u3 = new Utilisateur();
		u3.setNom("user");
		u3.setMail("user.three@mail.com");
		u3.setPrenom("three");
		manager.persist(u3);
	}

	public void userList(){
		String s = "SELECT u FROM Utilisateur as u";

		Query q = manager.createQuery(s,Utilisateur.class);
		List<Utilisateur> res = q.getResultList();

		for(Utilisateur u : res){
			System.err.println(u.getNom());
		}
	}

}

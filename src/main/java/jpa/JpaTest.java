package jpa;

import domain.Utilisateur;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
//		String s = "SELECT e FROM Person as e where e.name=:name";

//		Query q = manager.createQuery(s,Person.class);
//		q.setParameter("name", "martin");
//		List<Person> res = q.getResultList();

//		System.err.println(res.size());
//		System.err.println(res.get(0).getName());

		manager.close();
		factory.close();
	}

	public void createUsers(){
		Utilisateur u1 = new Utilisateur();
		u1.setName("user");
		u1.setMail("user.one@mail.com");
		u1.setPrenom("one");
		manager.persist(u1);

		Utilisateur u2 = new Utilisateur();
		u2.setName("user");
		u2.setMail("user.two@mail.com");
		u2.setPrenom("two");
		manager.persist(u2);

		Utilisateur u3 = new Utilisateur();
		u3.setName("user");
		u3.setMail("user.three@mail.com");
		u3.setPrenom("three");
		manager.persist(u3);
	}

}

package fr.istic.sir.domain;

import javax.persistence.*;

@Entity
public class Allergie {
	
	@Id
	@GeneratedValue
	private long id;

	private String allergie;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAllergie() {
		return allergie;
	}

	public void setAllergie(String allergie) {
		this.allergie = allergie;
	}

	@Override
	public String toString() {
		return "Utilisateur [allergie=" + allergie + "]";
	}

}

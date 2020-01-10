package domain;

import javax.persistence.*;

@Entity
public class Allergie {
	
	@Id
	private String allergie;

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

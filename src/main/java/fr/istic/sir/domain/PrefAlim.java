package fr.istic.sir.domain;

import javax.persistence.*;

@Entity
public class PrefAlim {
	
	@Id
	private String prefAlim;

	public String getPrefAlim() {
		return prefAlim;
	}

	public void setPrefAlim(String prefAlim) {
		this.prefAlim = prefAlim;
	}

	@Override
	public String toString() {
		return "Utilisateur [allergie=" + prefAlim + "]";
	}
	
}

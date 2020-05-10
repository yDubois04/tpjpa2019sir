package fr.istic.sir.domain;

import javax.persistence.*;

@Entity
public class PrefAlim {
	
	@Id
	@GeneratedValue
	private long id;
	private String prefAlim;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

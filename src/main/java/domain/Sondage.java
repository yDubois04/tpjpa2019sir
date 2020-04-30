package domain;

import java.util.List;

import javax.persistence.*;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Sondage {
	
	private String lien;
	private Utilisateur createur;

	@Id
	public String getLien() {
		return lien;
	}

	public void setLien(String lien) {
		this.lien = lien;
	}

	@ManyToOne
	@JoinColumn (name = "utilisateur_id")
	public Utilisateur getCreateur() {
		return createur;
	}

	public void setCreateur(Utilisateur createur) {
		this.createur = createur;
	}

	@Override
	public String toString() {
		return "Utilisateur [lien=" + lien + ", createur=" + createur.toString() + "]";
	}

}

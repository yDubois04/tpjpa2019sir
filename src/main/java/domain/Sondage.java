package domain;

import java.util.List;

import javax.persistence.*;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Sondage {
	
	private String lien;
	private Reunion reunion;
	private List<Utilisateur> participants;
	private Utilisateur createur;

	@Id
	public String getLien() {
		return lien;
	}

	public void setLien(String lien) {
		this.lien = lien;
	}

	@ManyToMany (mappedBy = "sondages")
	public List<Utilisateur> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Utilisateur> participants) {
		this.participants = participants;
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
		return "Utilisateur [lien=" + lien + ", reunion=" + reunion + ", createur=" + createur + ", nbParticipants=" + participants.size() + "]";
	}

}

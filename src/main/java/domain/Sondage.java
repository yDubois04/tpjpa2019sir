package domain;

import java.util.List;

import javax.persistence.*;
@Entity
public class Sondage {
	
	private String lien;
	private List<DateReunion> datesPossibles;
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

	@ManyToMany
	@JoinTable(name = "Sondage_DateReunion")
	public List<DateReunion> getDatesPossibles() {
		return datesPossibles;
	}

	public void setDatesPossibles(List<DateReunion> datesPossibles) {
		this.datesPossibles = datesPossibles;
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
	public String toString () {
		return "Sondage [lien ="+lien+"]";
	}

}

package domain;

import java.util.List;

import javax.persistence.*;
@Entity
public class Sondage {
	
	private String lien;
	@Transient
	private List<DateReunion> datesPossibles;
	@Transient
	private Reunion reunion;
	@Transient
	private List<Utilisateur> participants;
	@Transient
	private Utilisateur createur;
	

	@Id
	public String getLien() {
		return lien;
	}

	public void setLien(String lien) {
		this.lien = lien;
	}
	
	@Override
	public String toString () {
		return "Sondage [lien ="+lien+"]";
	}

}

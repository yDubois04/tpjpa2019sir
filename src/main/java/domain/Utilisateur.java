package domain;

import java.util.Collection;
import java.util.List;

import javax.persistence.*;

@Entity
public class Utilisateur {
	
	private String name;
	private String prenom;
	private String mail;
	private Collection<PrefAlim> prefAlim;
	private Collection<Allergie> allergies;
	private Collection<Sondage> sondages;
	private Collection<Sondage> sondagesCrees;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	@Id
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

	@ManyToMany
	public Collection<PrefAlim> getPrefAlim() {
		return prefAlim;
	}

	public void setPrefAlim(Collection<PrefAlim> prefAlim) {
		this.prefAlim = prefAlim;
	}

	@ManyToMany
	public Collection<Allergie> getAllergies() {
		return allergies;
	}

	public void setAllergies(Collection<Allergie> allergies) {
		this.allergies = allergies;
	}

	@ManyToMany
	@JoinTable(name = "Utilisateur_Sondage")
	public Collection<Sondage> getSondages() {
		return sondages;
	}

	public void setSondages(Collection<Sondage> sondages) {
		this.sondages = sondages;
	}

	@OneToMany (mappedBy = "createur")
	public Collection<Sondage> getSondagesCrees() {
		return sondagesCrees;
	}

	public void setSondagesCrees(Collection<Sondage> sondagesCrees) {
		this.sondagesCrees = sondagesCrees;
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + mail + ", name=" + name + "]";
	}

}

package domain;

import java.util.Collection;
import java.util.List;

import javax.persistence.*;

@Entity
public class Utilisateur {
	
	private String name;
	private String prenom;
	private String mail;
	@Transient
	private Collection<PrefAlim> prefAlim;
	@Transient
	private Collection<Allergie> allergies;
	@Transient
	private Collection<Sondage> sondages;
	
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
	/*
	@ManyToMany
	public Collection<Allergie> getAllergies() {
		return allergies;
	}
	public void setAllergies(List<Allergie> allergies) {
		this.allergies = allergies;
	}
	
	@ManyToMany
	public Collection<PrefAlim> getPrefAlim() {
		return prefAlim;
	}
	public void setPrefAlim(List<PrefAlim> prefAlim) {
		this.prefAlim = prefAlim;
	}
*/
	@Override
	public String toString() {
		return "Utilisateur [id=" + mail + ", name=" + name + "]";
	}

}

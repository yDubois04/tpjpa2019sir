package domain;

import javax.persistence.*;

@Entity
public class Reunion {
	
	private String intitule;
	private String resume;
	@Id
	@GeneratedValue
	private long id;
	@Transient
	private Reunion reunion;
	
	
	
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", intitule=" + intitule + ", resume=" + resume + "]";
	}

}

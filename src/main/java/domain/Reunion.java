package domain;

import javax.persistence.*;

@Entity
public class Reunion {
	
	private String intitule;
	private String resume;
	private long id;
	private DateReunion dateReunion;
    private Sondage sondage;

    @Id
    @GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

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

	@ManyToOne
	@JoinColumn(name = "date_id")
	public DateReunion getDateReunion() {
		return dateReunion;
	}

	public void setDateReunion(DateReunion dateReunion) {
        this.dateReunion = dateReunion;
    }

    @OneToOne
    public Sondage getSondage() {
        return sondage;
    }

    public void setSondage(Sondage sondage) {
        this.sondage = sondage;
    }

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", intitule=" + intitule + ", resume=" + resume + "]";
	}

}

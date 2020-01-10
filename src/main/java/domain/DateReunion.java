package domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class DateReunion {

	private long id;
	private Date date;
	private boolean contientPauseDej;
	private List <Sondage> sondages;
	private List <Reunion> reunion;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isContientPauseDej() {
		return contientPauseDej;
	}
	public void setContientPauseDej(boolean contientPauseDej) {
		this.contientPauseDej = contientPauseDej;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@OneToMany(mappedBy = "dateReunion")
	public List<Reunion> getReunion() {
		return reunion;
	}

	public void setReunion(List<Reunion> reunion) {
		this.reunion = reunion;
	}

	@ManyToMany (mappedBy = "datesPossibles")
	public List<Sondage> getSondages() {
		return sondages;
	}

	public void setSondages(List<Sondage> sondages) {
		this.sondages = sondages;
	}
}

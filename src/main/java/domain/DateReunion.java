package domain;

import java.util.List;

import javax.persistence.*;

@Entity
public class DateReunion {

	private long id;
	private String date;
	private boolean contientPauseDej;
	private List <SondageDate> sondages;
	private List<Reunion> reunions;

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@OneToMany(mappedBy = "dateReunion")
	public List<Reunion> getReunions() {
		return reunions;
	}

	public void setReunions(List<Reunion> reunion) {
		this.reunions = reunion;
	}

	@ManyToMany (mappedBy = "datesPossibles", cascade = CascadeType.ALL)
	public List<SondageDate> getSondages() {
		return sondages;
	}

	public void setSondages(List<SondageDate> sondages) {
		this.sondages = sondages;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public boolean isContientPauseDej() {
		return contientPauseDej;
	}
	public void setContientPauseDej(boolean contientPauseDej) {
		this.contientPauseDej = contientPauseDej;
	}


	@Override
    public String toString() {
        return "Date [id=" + id + ", date=" + date + ", pauseDej=" + contientPauseDej + "]";
    }
}

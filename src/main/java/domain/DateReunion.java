package domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class DateReunion {

	private long id;
	private Date date;
	private boolean contientPauseDej;
	@Transient 
	private List <Sondage> sondages;
	@Transient
	private Reunion reunion;
	
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
	

}

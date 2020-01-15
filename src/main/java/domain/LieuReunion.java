package domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class LieuReunion {
    private long id;
    private String nomLieu;
    private List<SondageLieu> sondages;
    private List<Reunion> reunions;

    public String getNomLieu() {
        return nomLieu;
    }
    public void setNomLieu(String nomLieu) {
        this.nomLieu = nomLieu;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "lieuReunion")
    public List<Reunion> getReunions() {
        return reunions;
    }

    public void setReunions(List<Reunion> reunion) {
        this.reunions = reunion;
    }

    @ManyToMany(mappedBy = "lieuPossibles")
    public List<SondageLieu> getSondages() {
        return sondages;
    }

    public void setSondages(List<SondageLieu> sondages) {
        this.sondages = sondages;
    }

    @Override
    public String toString() {
        return "Utilisateur [id=" + id + ", lieu=" + nomLieu + "]";
    }
}

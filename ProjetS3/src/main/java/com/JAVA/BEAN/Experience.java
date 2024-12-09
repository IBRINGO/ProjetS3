package com.JAVA.BEAN;
import java.util.Date;

public class Experience {
	private int idExperience;
    private String poste;
    private String entreprise;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private int idCandidat;

    // Getters and Setters
    public int getIdExperience() {
        return idExperience;
    }
    public void setIdExperience(int idExperience) {
        this.idExperience = idExperience;
    }
    public String getPoste() {
        return poste;
    }
    public void setPoste(String poste) {
        this.poste = poste;
    }
    public String getEntreprise() {
        return entreprise;
    }
    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    public Date getDateFin() {
        return dateFin;
    }
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    
    public int getIdCandidat() {
        return idCandidat;
    }
    public void setIdCandidat(int idCandidat) {
        this.idCandidat = idCandidat;
    }

}

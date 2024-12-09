package com.JAVA.BEAN;

import java.util.*;

public class Formation {
	private int idFormation;
    private String titre;
    private String domaine;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private boolean certificatAttribue;
    private int admin;
    private List<Competence> competencesAssociees;

    // Getters and Setters
    public int getIdFormation() {
        return idFormation;
    }
    public void setIdFormation(int idFormation) {
        this.idFormation = idFormation;
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getDomaine() {
        return domaine;
    }
    public void setDomaine(String domaine) {
        this.domaine = domaine;
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
    public boolean isCertificatAttribue() {
        return certificatAttribue;
    }
    public void setCertificatAttribue(boolean certificatAttribue) {
        this.certificatAttribue = certificatAttribue;
    }
    public int getAdmin() {
        return admin;
    }
    public void setAdmin(int admin) {
        this.admin = admin;
    }
    public List<Competence> getCompetencesAssociees() {
        return competencesAssociees;
    }
    public void setCompetencesAssociees(List<Competence> competencesAssociees) {
        this.competencesAssociees = competencesAssociees;
    }
}


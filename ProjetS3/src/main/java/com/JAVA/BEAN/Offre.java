package com.JAVA.BEAN;

import java.util.*;

public class Offre {
	private int idOffre;
    private String titre;
    private String description;
    private List<Competence> competencesRequises;
    private Date datePublication;
    private Date dateLimite;
    private int recruteur;
    private List<Candidature> candidaturesRecues;

    // Getters and Setters
    public int getIdOffre() {
        return idOffre;
    }
    public void setIdOffre(int idOffre) {
        this.idOffre = idOffre;
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<Competence> getCompetencesRequises() {
        return competencesRequises;
    }
    public void setCompetencesRequises(List<Competence> competencesRequises) {
        this.competencesRequises = competencesRequises;
    }
    public Date getDatePublication() {
        return datePublication;
    }
    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }
    public Date getDateLimite() {
        return dateLimite;
    }
    public void setDateLimite(Date dateLimite) {
        this.dateLimite = dateLimite;
    }
    public int getRecruteur() {
        return recruteur;
    }
    public void setRecruteur(int recruteur) {
        this.recruteur = recruteur;
    }
    public List<Candidature> getCandidaturesRecues() {
        return candidaturesRecues;
    }
    public void setCandidaturesRecues(List<Candidature> candidaturesRecues) {
        this.candidaturesRecues = candidaturesRecues;
    }
}

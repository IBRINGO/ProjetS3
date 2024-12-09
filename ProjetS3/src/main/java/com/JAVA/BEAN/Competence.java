package com.JAVA.BEAN;

public class Competence {
	private int idCompetence;
    private String nom;
    private String description;
    private String niveauRequis;

    // Getters and Setters
    public int getIdCompetence() {
        return idCompetence;
    }
    public void setIdCompetence(int idCompetence) {
        this.idCompetence = idCompetence;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getNiveauRequis() {
        return niveauRequis;
    }
    public void setNiveauRequis(String niveauRequis) {
        this.niveauRequis = niveauRequis;
    }
}

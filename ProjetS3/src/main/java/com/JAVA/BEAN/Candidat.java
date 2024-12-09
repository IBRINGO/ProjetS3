package com.JAVA.BEAN;

import java.util.*;

public class Candidat extends User {
    private List<Competence> competences = new ArrayList<>();
    private List<Formation> formationsSuivies = new ArrayList<>();
    private List<Experience> experiences;
    private List<Offre> offresPostulees = new ArrayList<>();
    private List<Certificat> certificatsObtenus = new ArrayList<>();
    private String telephone;
    private String ville;
    private String adresse;
    private Date dateNaissance;

    public Candidat(String nom, String prenom, String email, String password) {
        super(nom, prenom, email, password);
    }
    
 public Candidat() {
		// TODO Auto-generated constructor stub
	}

	// Getters et Setters
    public List<Competence> getCompetences() {
        return competences;
    }

    public void setCompetences(List<Competence> competences) {
        this.competences = competences;
    }
    
    public List<Experience> getExperiences() {
        return experiences;
    }
    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }
    
    public List<Formation> getFormationsSuivies() {
        return formationsSuivies;
    }

    public void setFormationsSuivies(List<Formation> formationsSuivies) {
        this.formationsSuivies = formationsSuivies;
    }
    
    public List<Offre> getOffresPostulees() {
        return offresPostulees;
    }

    public void setOffresPostulees(List<Offre> offresPostulees) {
        this.offresPostulees = offresPostulees;
    }
    
    public List<Certificat> getCertificatsObtenus() {
        return certificatsObtenus;
    }

    public void setCertificatsObtenus(List<Certificat> certificatsObtenus) {
        this.certificatsObtenus = certificatsObtenus;
    }
    

    public void definirCompetences() {
    }

   
    public void inscrireFormations(Formation formation) {
        formationsSuivies.add(formation);
    }
    
    
    public void passerUnTest() {
    }

    public void postulerOffres(Offre offre) {
        offresPostulees.add(offre);
    }
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

	
}

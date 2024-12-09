package com.JAVA.BEAN;

import java.util.*;

public class Recruteur extends User {
	
    private String entreprise;
    private String telephone;
    private String siteWeb;
    private String adresse;
    private List<Offre> offrePubliees = new ArrayList<>();

 // Getters and Setters
    public String getEntreprise() {
        return entreprise;
    }
    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }
    
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }
    
    public List<Offre> getOffresPubliees() {
        return this.offrePubliees;
    }
    public void setOffresPubliees(List<Offre> offresPubliees) {
        this.offrePubliees = offresPubliees;
    }
    public void publierOffre(Offre offre) {
        offrePubliees.add(offre);
    }

    public void modifierOffre(Offre offre) {
    }

    public void supprimerOffre(Offre offre) {
        offrePubliees.remove(offre);
    }
}

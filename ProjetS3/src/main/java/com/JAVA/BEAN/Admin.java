package com.JAVA.BEAN;

import java.util.List;

public class Admin extends User {
	
	private List<Formation> formationsProposees;
    private String statistiquesPlateforme;

    // Getters and Setters
    public List<Formation> getFormationsProposees() {
        return formationsProposees;
    }
    public void setFormationsProposees(List<Formation> formationsProposees) {
        this.formationsProposees = formationsProposees;
    }
    public String getStatistiquesPlateforme() {
        return statistiquesPlateforme;
    }
    public void setStatistiquesPlateforme(String statistiquesPlateforme) {
        this.statistiquesPlateforme = statistiquesPlateforme;
    }

}

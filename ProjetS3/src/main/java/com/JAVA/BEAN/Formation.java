package com.JAVA.BEAN;

import java.util.*;

public class Formation {
    private String titre;
    private String domaine;
    private String dateDebut;
    private String dateFin;
    private List<Competence> competences = new ArrayList<>();

    public Formation(String titre, String domaine, String dateDebut, String dateFin) {
        this.titre = titre;
        this.domaine = domaine;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public void ajouterCompetence(Competence competence) {
        competences.add(competence);
    }

    public void supprimerCompetence(Competence competence) {
        competences.remove(competence);
    }
}

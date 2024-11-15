package com.JAVA.BEAN;

import java.util.*;

public class Candidat extends User {
    private List<Competence> competences = new ArrayList<>();
    private List<Formation> formationsSuivies = new ArrayList<>();
    private List<Offre> offresPostulees = new ArrayList<>();
    private List<Certificat> certificatsObtenus = new ArrayList<>();

    public Candidat(String nom, String prenom, String email, String password) {
        super(nom, prenom, email, password);
    }

    public void definirCompetences() {
        // Définir les compétences du candidat
    }

    public void inscrireFormations(Formation formation) {
        formationsSuivies.add(formation);
    }

    public void passerUnTest() {
        // Implémentation du passage de test
    }

    public void postulerOffres(Offre offre) {
        offresPostulees.add(offre);
    }
}

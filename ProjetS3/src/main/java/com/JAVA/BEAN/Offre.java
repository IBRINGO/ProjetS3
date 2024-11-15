package com.JAVA.BEAN;

import java.util.*;

public class Offre {
    private String titre;
    private String description;
    private String datePublication;
    private String dateLimite;
    private List<Competence> competencesRequises = new ArrayList<>();
    private String entreprise;

    public Offre(String titre, String description, String datePublication, String dateLimite, String entreprise) {
        this.titre = titre;
        this.description = description;
        this.datePublication = datePublication;
        this.dateLimite = dateLimite;
        this.entreprise = entreprise;
    }

    public void consulter() {
        // Afficher les détails de l'offre
    }

    public void supprimer() {
        // Supprimer l'offre
    }

    public void ajouter() {
        // Ajouter une nouvelle offre
    }

    public void modifier() {
        // Modifier les détails de l'offre
    }
}

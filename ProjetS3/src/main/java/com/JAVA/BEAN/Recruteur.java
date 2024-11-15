package com.JAVA.BEAN;

import java.util.*;

public class Recruteur extends User {
    private List<Offre> offrePubliees = new ArrayList<>();

    public Recruteur(String nom, String prenom, String email, String password) {
        super(nom, prenom, email, password);
    }

    public void publierOffre(Offre offre) {
        offrePubliees.add(offre);
    }

    public void modifierOffre(Offre offre) {
        // Modifier l'offre
    }

    public void supprimerOffre(Offre offre) {
        offrePubliees.remove(offre);
    }
}

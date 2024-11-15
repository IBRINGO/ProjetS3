package com.JAVA.BEAN;

public class Certificat {
    private String titre;
    private String dateObtention;
    private Formation formationAssociee;

    public Certificat(String titre, String dateObtention, Formation formationAssociee) {
        this.titre = titre;
        this.dateObtention = dateObtention;
        this.formationAssociee = formationAssociee;
    }
}

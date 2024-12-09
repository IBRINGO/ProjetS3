package com.JAVA.DAO;

import java.util.List;

import com.JAVA.BEAN.Recruteur;

public interface RecruteurDao {
	void addRecruteur(Recruteur recruteur);
    void updateRecruteur(Recruteur recruteur);
    void deleteRecruteur(int idUtilisateur);
    Recruteur findRecruteurById(int idUtilisateur);
    List<Recruteur> listRecruteurs();
	
}

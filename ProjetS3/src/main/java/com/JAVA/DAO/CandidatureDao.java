package com.JAVA.DAO;

import java.util.List;

import com.JAVA.BEAN.Candidature;

public interface CandidatureDao {
	
	void ajouterCandidature(Candidature candidature) throws DAOException;
    void modifierCandidature(Candidature candidature) throws DAOException;
    void supprimerCandidature(int idCandidature) throws DAOException;
    Candidature trouverCandidatureParId(int idCandidature) throws DAOException;
    List<Candidature> listerCandidatures() throws DAOException;
    List<Candidature> trouverCandidaturesParCandidat(int idCandidat) throws DAOException;
    List<Candidature> trouverCandidaturesParOffre(int idOffre) throws DAOException;
	
}

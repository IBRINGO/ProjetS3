package com.JAVA.DAO;

import java.util.List;

import com.JAVA.BEAN.Competence;

public interface CompetenceDao {
	
	void addCompetence(Competence competence) throws DAOException;
    void updateCompetence(Competence competence) throws DAOException;
    void deleteCompetence(int idCompetence) throws DAOException;
    Competence findCompetenceById(int idCompetence) throws DAOException;
    List<Competence> listCompetences() throws DAOException;
    List<Competence> listCompetencesByCandidat(int idCandidat) throws DAOException;
	List<Competence> listerCompetencesParCandidat(int candidatId) throws DAOException;

}

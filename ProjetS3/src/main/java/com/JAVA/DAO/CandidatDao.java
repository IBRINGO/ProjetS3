package com.JAVA.DAO;


import com.JAVA.BEAN.Candidat;
import java.util.List;

public interface CandidatDao {
    void addCandidat(Candidat candidat) throws DAOException;
    List<Candidat> listCandidats() throws DAOException;
    void updateCandidat(Candidat candidat) throws DAOException;
    void deleteCandidat(int id) throws DAOException;
    Candidat findCandidatById(int id);
}


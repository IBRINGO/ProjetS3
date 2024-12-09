package com.JAVA.DAO;

import com.JAVA.BEAN.Formation;
import java.util.List;

public interface FormationDao {
    void addFormation(Formation formation) throws DAOException;
    List<Formation> listFormations() throws DAOException;
    void updateFormation(Formation formation) throws DAOException;
    void deleteFormation(int id) throws DAOException;
    Formation findFormationById(int id);
}

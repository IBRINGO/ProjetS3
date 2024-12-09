package com.JAVA.DAO;

import com.JAVA.BEAN.Offre;
import java.util.List;

public interface OffreDao {
    void addOffre(Offre offre) throws DAOException;
    List<Offre> listOffres() throws DAOException;
    void updateOffre(Offre offre) throws DAOException;
    void deleteOffre(int id) throws DAOException;
    Offre findOffreById(int id);
}


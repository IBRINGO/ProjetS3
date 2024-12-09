package com.JAVA.DAO;

import com.JAVA.BEAN.Candidature;
import com.JAVA.BEAN.Competence;
import com.JAVA.BEAN.Offre;
import com.JAVA.BEAN.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OffreDaoImpl implements OffreDao {
    private DAOFactory daoFactory;

    public OffreDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void addOffre(Offre offre) throws DAOException {
        String sql = "INSERT INTO offres (titre, description, datePublication, dateLimite, idRecruteur) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, offre.getTitre());
            statement.setString(2, offre.getDescription());
            statement.setDate(3, (Date)offre.getDatePublication());
            statement.setDate(4, (Date)offre.getDateLimite());
            statement.setInt(5, offre.getRecruteur());
            statement.executeUpdate();
            
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int offreId = generatedKeys.getInt(1);
                    ajouterOffreCompetences(offreId, offre.getCompetencesRequises());
                }
            }
            
        } catch (SQLException e) {
            throw new DAOException("Error adding offre", e);
        }
    }
    
    private void ajouterOffreCompetences(int offreId, List<Competence> competencesRequises) throws DAOException {
        String sql = "INSERT INTO offre_competences (idOffre, idCompetence) VALUES (?, ?)";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Competence competence : competencesRequises) {
                statement.setInt(1, offreId);
                statement.setInt(2, competence.getIdCompetence());
                statement.addBatch(); // Ajoute la requête au lot pour optimisation
            }
            statement.executeBatch(); // Exécute toutes les requêtes en une seule transaction
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de l'ajout des compétences requises pour l'offre", e);
        }
    }



    @Override
    public List<Offre> listOffres() throws DAOException {
        List<Offre> offres = new ArrayList<>();
        String sql = "SELECT * FROM offres";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Offre offre = new Offre();
                offre.setIdOffre(resultSet.getInt("id"));
                offre.setTitre(resultSet.getString("titre"));
                offre.setDescription(resultSet.getString("description"));
                offre.setDatePublication(resultSet.getDate("datePublication"));
                offre.setDateLimite(resultSet.getDate("dateLimite"));
                offre.setRecruteur(resultSet.getInt("idRecruteur"));
                offres.add(offre);
            }
        } catch (SQLException e) {
            throw new DAOException("Error listing offres", e);
        }
        return offres;
    }

    @Override
    public void updateOffre(Offre offre) throws DAOException {
        String sql = "UPDATE offres SET titre = ?, description = ?, datePublication = ?, dateLimite = ? WHERE id = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, offre.getTitre());
            statement.setString(2, offre.getDescription());
            statement.setDate(3, (Date)offre.getDatePublication());
            statement.setDate(4, (Date)offre.getDateLimite());
            statement.setInt(5, offre.getIdOffre());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error updating offre", e);
        }
    }

    @Override
    public void deleteOffre(int id) throws DAOException {
        String sql = "DELETE FROM offres WHERE id = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error deleting offre", e);
        }
    }

	@Override
	public Offre findOffreById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}

package com.JAVA.DAO;

import com.JAVA.BEAN.Candidat;
import com.JAVA.BEAN.Competence;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompetenceDaoImpl implements CompetenceDao {
    private DAOFactory daoFactory;

    public CompetenceDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void addCompetence(Competence competence) throws DAOException {
        String sql = "INSERT INTO competences (nom, description, niveauRequis) VALUES (?, ?, ?)";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, competence.getNom());
            statement.setString(2, competence.getDescription());
            statement.setString(3, competence.getNiveauRequis());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de l'ajout de la compétence", e);
        }
    }

    @Override
    public List<Competence> listCompetences() throws DAOException {
        List<Competence> competences = new ArrayList<>();
        String sql = "SELECT * FROM competences";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Competence competence = new Competence();
                competence.setIdCompetence(resultSet.getInt("idCompetence"));
                competence.setNom(resultSet.getString("nom"));
                competence.setDescription(resultSet.getString("description"));
                competence.setNiveauRequis(resultSet.getString("niveauRequis"));
                competences.add(competence);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la récupération des compétences", e);
        }
        return competences;
    }

    @Override
    public List<Competence> listerCompetencesParCandidat(int candidatId) throws DAOException {
        List<Competence> competences = new ArrayList<>();
        String sql = "SELECT c.* FROM competences c INNER JOIN candidat_competence cc ON c.idCompetence = cc.idCompetence WHERE cc.idUtilisateur = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, candidatId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Competence competence = new Competence();
                    competence.setIdCompetence(resultSet.getInt("idCompetence"));
                    competence.setNom(resultSet.getString("nom"));
                    competence.setDescription(resultSet.getString("description"));
                    competence.setNiveauRequis(resultSet.getString("niveauRequis"));
                    competences.add(competence);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la récupération des compétences pour un candidat", e);
        }
        return competences;
    }

	@Override
	public void updateCompetence(Competence competence) throws DAOException {
		String sql = "UPDATE candidats SET nom = ?, description = ?, niveauRequis = ? WHERE idCompetence = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, competence.getNom());
            statement.setString(2, competence.getDescription());
            statement.setString(3, competence.getNiveauRequis());
            statement.setInt(4, competence.getIdCompetence());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la modification de la competence", e);
        }		
	}

	@Override
	public void deleteCompetence(int idCompetence) throws DAOException {
		String sql = "DELETE FROM competences WHERE idCompetence = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idCompetence);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la suppression du candidat", e);
        }
		
	}

	@Override
	public Competence findCompetenceById(int idCompetence) throws DAOException {
		String sql = "SELECT * FROM competences WHERE idCompetence = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idCompetence);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Competence competence = new Competence();
                    competence.setNom(resultSet.getString("nom"));
                    competence.setDescription(resultSet.getString("description"));
                    competence.setNiveauRequis(resultSet.getString("niveauRequis"));
                    return competence;
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la recherche de la competence", e);
        }
        return null;
    }
	

	@Override
	public List<Competence> listCompetencesByCandidat(int idCandidat) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
}

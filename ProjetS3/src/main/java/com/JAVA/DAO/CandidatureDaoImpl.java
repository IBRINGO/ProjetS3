package com.JAVA.DAO;

import com.JAVA.BEAN.Candidature;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidatureDaoImpl implements CandidatureDao {
    private DAOFactory daoFactory;

    public CandidatureDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void ajouterCandidature(Candidature candidature) throws DAOException {
        String sql = "INSERT INTO candidatures (dateSoumission, statut, idOffre, idCandidat) VALUES (?, ?, ?, ?)";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(candidature.getDateSoumission().getTime()));
            statement.setString(2, candidature.getStatut());
            statement.setInt(3, candidature.getIdOffre());
            statement.setInt(4, candidature.getIdCandidat());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de l'ajout de la candidature", e);
        }
    }

    @Override
    public Candidature trouverCandidatureParId(int id) throws DAOException {
        String sql = "SELECT * FROM candidatures WHERE id = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return map(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la recherche de la candidature", e);
        }
        return null;
    }

    @Override
    public List<Candidature> listerCandidatures() throws DAOException {
        List<Candidature> candidatures = new ArrayList<>();
        String sql = "SELECT * FROM candidatures";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                candidatures.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la liste des candidatures", e);
        }
        return candidatures;
    }

    @Override
    public void modifierCandidature(Candidature candidature) throws DAOException {
        String sql = "UPDATE candidatures SET dateSoumission = ?, statut = ?, idOffre = ?, idCandidat = ? WHERE id = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(candidature.getDateSoumission().getTime()));
            statement.setString(2, candidature.getStatut());
            statement.setInt(3, candidature.getIdOffre());
            statement.setInt(4, candidature.getIdCandidat());
            statement.setInt(5, candidature.getIdCandidature());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la mise à jour de la candidature", e);
        }
    }

    @Override
    public void supprimerCandidature(int id) throws DAOException {
        String sql = "DELETE FROM candidatures WHERE id = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la suppression de la candidature", e);
        }
    }

    @Override
    public List<Candidature> trouverCandidaturesParCandidat(int idCandidat) throws DAOException {
        List<Candidature> candidatures = new ArrayList<>();
        String sql = "SELECT * FROM candidatures WHERE idCandidat = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idCandidat);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    candidatures.add(map(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la récupération des candidatures du candidat", e);
        }
        return candidatures;
    }

    @Override
    public List<Candidature> trouverCandidaturesParOffre(int idOffre) throws DAOException {
        List<Candidature> candidatures = new ArrayList<>();
        String sql = "SELECT * FROM candidatures WHERE idOffre = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idOffre);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    candidatures.add(map(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la récupération des candidatures pour l'offre", e);
        }
        return candidatures;
    }

    // Méthode de mappage pour convertir un ResultSet en objet Candidature
    private Candidature map(ResultSet resultSet) throws SQLException {
        Candidature candidature = new Candidature();
        candidature.setIdCandidature(resultSet.getInt("id"));
        candidature.setDateSoumission(resultSet.getDate("dateSoumission"));
        candidature.setStatut(resultSet.getString("statut"));
        candidature.setIdOffre(resultSet.getInt("idOffre"));
        candidature.setIdCandidat(resultSet.getInt("idCandidat"));
        return candidature;
    }

}

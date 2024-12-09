package com.JAVA.DAO;

import com.JAVA.BEAN.Formation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FormationDaoImpl implements FormationDao {
    private DAOFactory daoFactory;

    public FormationDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void addFormation(Formation formation) throws DAOException {
    	String sql = "INSERT INTO formations (titre, description, dateDebut, dateFin, certificatAttribue, idAdmin, domaine) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, formation.getTitre());
            statement.setString(2, formation.getDescription());
            statement.setDate(3, new java.sql.Date(formation.getDateDebut().getTime()));
            statement.setDate(4, new java.sql.Date(formation.getDateFin().getTime()));
            statement.setBoolean(5, formation.isCertificatAttribue());
            statement.setInt(6, formation.getAdmin());
            statement.setString(7, formation.getDomaine());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    formation.setIdFormation(generatedKeys.getInt(1));
                }
            }

            // Ajouter les compétences associées
            /*if (formation.getCompetencesAssociees() != null) {
                associerCompetencesAFormation(formation.getIdFormation(), formation.getCompetencesAssociees());
            }*/
        } catch (SQLException e) {
        	System.out.println("=== DEBUG REQUÊTE INSERT ===");
        	System.out.println("Paramètres : "+e);
            throw new DAOException("Erreur lors de l'ajout de la formation", e);
        }
    }

    @Override
    public List<Formation> listFormations() throws DAOException {
        List<Formation> formations = new ArrayList<>();
        String sql = "SELECT * FROM formations";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Formation formation = new Formation();
                formation.setIdFormation(resultSet.getInt("idFormation"));
                formation.setTitre(resultSet.getString("titre"));
                formation.setDescription(resultSet.getString("description"));
                formation.setDomaine(resultSet.getString("domaine"));
                formation.setDateDebut(resultSet.getDate("dateDebut"));
                formation.setDateFin(resultSet.getDate("dateFin"));
                formation.setAdmin(resultSet.getInt("idAdmin"));
                formation.setCertificatAttribue(resultSet.getBoolean("certificatAttribue"));
                formations.add(formation);
            }
        } catch (SQLException e) {
        	System.out.println("=== DEBUG REQUÊTE SELECT ===");
        	System.out.println("Paramètres : "+e);
            throw new DAOException("Error listing formations", e);
        }
        return formations;
    }

    @Override
    public void updateFormation(Formation formation) throws DAOException {
    	String sql = "UPDATE formations SET titre = ?, description = ?, dateDebut = ?, dateFin = ?, certificatAttribue = ?, idAdmin = ? WHERE idFormation = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, formation.getTitre());
            statement.setString(2, formation.getDescription());
            statement.setDate(3, new java.sql.Date(formation.getDateDebut().getTime()));
            statement.setDate(4, new java.sql.Date(formation.getDateFin().getTime()));
            statement.setBoolean(5, formation.isCertificatAttribue());
            statement.setInt(6, formation.getAdmin());
            statement.setString(7, formation.getDomaine());
            statement.setInt(8, formation.getIdFormation());
            statement.executeUpdate();

            // Mettre à jour les compétences associées
            /*supprimerCompetencesDeFormation(formation.getIdFormation());
            if (formation.getCompetencesAssociees() != null) {
                associerCompetencesAFormation(formation.getIdFormation(), formation.getCompetencesAssociees());
            }*/
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la mise à jour de la formation", e);
        }
    }

    @Override
    public void deleteFormation(int id) throws DAOException {
        String sql = "DELETE FROM formations WHERE id = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error deleting formation", e);
        }
    }

    @Override
    public Formation findFormationById(int id) throws DAOException {
        String query = "SELECT * FROM formations WHERE id = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Formation formation = new Formation();
                    formation.setIdFormation(resultSet.getInt("id"));
                    formation.setTitre(resultSet.getString("titre"));
                    formation.setDescription(resultSet.getString("description"));
                    formation.setDomaine(resultSet.getString("domaine"));
                    formation.setDateDebut(resultSet.getDate("dateDebut"));
                    formation.setDateFin(resultSet.getDate("dateFin"));
                    formation.setDomaine(resultSet.getString("domaine"));
                    return formation;
                }
            }
        } catch (SQLException e) {
        	System.out.println("=== DEBUG REQUÊTE ===");
        	System.out.println("Paramètres : "+e);
            throw new DAOException("Error finding formation", e);
        }
        return null;
    }

	}

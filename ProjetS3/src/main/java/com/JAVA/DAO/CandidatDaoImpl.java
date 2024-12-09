package com.JAVA.DAO;

import com.JAVA.BEAN.Candidat;
import com.JAVA.BEAN.Competence;
import com.JAVA.BEAN.Experience;
import com.JAVA.BEAN.Formation;
import com.JAVA.BEAN.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidatDaoImpl implements CandidatDao {
    private DAOFactory daoFactory;

    public CandidatDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void addCandidat(Candidat candidat) throws DAOException {
        String sql = "INSERT INTO candidats (idUtilisateur, dateNaissance, telephone, addresse, ville) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, candidat.getId());
            statement.setDate(2, (Date) candidat.getDateNaissance());
            statement.setString(3, candidat.getTelephone());
            statement.setString(4, candidat.getAdresse());
            statement.setString(5, candidat.getVille());
            statement.executeUpdate();

            // Récupération de l'ID généré
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int candidatId = generatedKeys.getInt(1);
                    User user = new User(candidat.getName(), candidat.getFirstName(), candidat.getEmail(), candidat.getPassword());
                    user.setId(candidatId);
                    candidat.setId(candidatId);
                    addUser(user);
                    ajouterCompetencesCandidat(candidatId, candidat.getCompetences());
                    ajouterExperiencesCandidat(candidatId, candidat.getExperiences());
                    ajouterFormationsSuiviesCandidat(candidatId, candidat.getFormationsSuivies());
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de l'ajout du candidat", e);
        }
    }

    private void ajouterCompetencesCandidat(int candidatId, List<Competence> competences) throws DAOException {
        String sql = "INSERT INTO candidat_competence (idUtilisateur, idCompetence) VALUES (?, ?)";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Competence competence : competences) {
                statement.setInt(1, candidatId);
                statement.setInt(2, competence.getIdCompetence());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de l'ajout des compétences du candidat", e);
        }
    }

    private void ajouterExperiencesCandidat(int candidatId, List<Experience> experiences) throws DAOException {
        String sql = "INSERT INTO candidat_experience (idUtilisateur, idExperience) VALUES (?, ?)";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Experience experience : experiences) {
                statement.setInt(1, candidatId);
                statement.setInt(2, experience.getIdExperience());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de l'ajout des expériences du candidat", e);
        }
    }

    private void ajouterFormationsSuiviesCandidat(int candidatId, List<Formation> formations) throws DAOException {
        String sql = "INSERT INTO candidat_formation (idUtilisateur, idFormation) VALUES (?, ?)";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Formation formation : formations) {
                statement.setInt(1, candidatId);
                statement.setInt(2, formation.getIdFormation());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de l'ajout des formations suivies par le candidat", e);
        }
    }
    private void addUser(User user) throws DAOException {
        String sql = "INSERT INTO utilisateurs (id, nom, prenom, email, motDePasse, typeUtilisateur) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getType());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error adding user", e);
        }
    }

    @Override
    public List<Candidat> listCandidats() throws DAOException {
        List<Candidat> candidats = new ArrayList<>();
        String sql = "SELECT * FROM candidats";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Candidat candidat = new Candidat();
                candidat.setId(resultSet.getInt("idUtilisateur"));
                candidat.setDateNaissance(resultSet.getDate("dateNaissance"));
                candidat.setTelephone(resultSet.getString("telephone"));
                candidat.setAdresse(resultSet.getString("adresse"));
                candidat.setVille(resultSet.getString("ville"));
                candidat.setCompetences(recupererCompetencesCandidat(candidat.getId()));
                candidat.setExperiences(recupererExperiencesCandidat(candidat.getId()));
                candidat.setFormationsSuivies(recupererFormationsSuiviesCandidat(candidat.getId()));
                candidats.add(candidat);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la récupération des candidats", e);
        }
        return candidats;
    }

    private List<Competence> recupererCompetencesCandidat(int candidatId) throws DAOException {
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
                    competences.add(competence);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la récupération des compétences du candidat", e);
        }
        return competences;
    }

    private List<Experience> recupererExperiencesCandidat(int candidatId) throws DAOException {
        List<Experience> experiences = new ArrayList<>();
        String sql = "SELECT e.* FROM experiences e INNER JOIN candidat_experience ce ON e.idExperience = ce.idExperience WHERE ce.idUtilisateur = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, candidatId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Experience experience = new Experience();
                    experience.setIdExperience(resultSet.getInt("idExperience"));
                    experience.setDescription(resultSet.getString("description"));
                    experiences.add(experience);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la récupération des expériences du candidat", e);
        }
        return experiences;
    }

    private List<Formation> recupererFormationsSuiviesCandidat(int candidatId) throws DAOException {
        List<Formation> formations = new ArrayList<>();
        String sql = "SELECT f.* FROM formations f INNER JOIN candidat_formation cf ON f.idFormation = cf.idFormation WHERE cf.idUtilisateur = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, candidatId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Formation formation = new Formation();
                    formation.setIdFormation(resultSet.getInt("idFormation"));
                    formation.setTitre(resultSet.getString("titre"));
                    formations.add(formation);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la récupération des formations du candidat", e);
        }
        return formations;
    }

	@Override
	public void updateCandidat(Candidat candidat) throws DAOException {
		String sql = "UPDATE candidats SET nom = ?, prenom = ?, email = ?, motDePasse = ? WHERE idUtilisateur = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, candidat.getName());
            statement.setString(2, candidat.getFirstName());
            statement.setString(3, candidat.getEmail());
            statement.setString(4, candidat.getPassword());
            statement.setInt(8, candidat.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la modification du candidat", e);
        }
	}

	@Override
	public void deleteCandidat(int id) throws DAOException {
		String sql = "DELETE FROM candidats WHERE idUtilisateur = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la suppression du candidat", e);
        }
		
	}

	@Override
	public Candidat findCandidatById(int id) {
		String sql = "SELECT * FROM candidats WHERE idUtilisateur = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Candidat candidat = new Candidat();
                    candidat.setId(resultSet.getInt("idUtilisateur"));
                    candidat.setDateNaissance(resultSet.getDate("dateNaissance"));
                    candidat.setTelephone(resultSet.getString("telephone"));
                    candidat.setAdresse(resultSet.getString("adresse"));
                    candidat.setVille(resultSet.getString("ville"));
                    return candidat;
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la recherche du candidat", e);
        }
        return null;
    }
}


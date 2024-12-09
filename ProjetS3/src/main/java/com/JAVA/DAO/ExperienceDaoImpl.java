package com.JAVA.DAO;

import com.JAVA.BEAN.Experience;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExperienceDaoImpl implements ExperienceDao {
    private DAOFactory daoFactory;

    public ExperienceDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void addExperience(Experience experience) throws DAOException {
        String sql = "INSERT INTO experiences (poste, entreprise, description, dateDebut, dateFin, idUtilisateur) VALUES (?,?,?,?,?,?)";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(3, experience.getDescription());
            statement.setString(1, experience.getPoste());
            statement.setString(2, experience.getEntreprise());
            statement.setDate(4, (Date) experience.getDateDebut());
            statement.setDate(5, (Date) experience.getDateFin());
            statement.setInt(6, experience.getIdCandidat());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de l'ajout de l'expérience", e);
        }
    }

    @Override
    public List<Experience> listExperiences() throws DAOException {
        List<Experience> experiences = new ArrayList<>();
        String sql = "SELECT * FROM experiences";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Experience experience = new Experience();
                experience.setIdExperience(resultSet.getInt("idExperience"));
                experience.setEntreprise(resultSet.getString("entreprise"));
                experience.setDescription(resultSet.getString("description"));
                experience.setPoste(resultSet.getString("poste"));
                experience.setDateDebut(resultSet.getDate("dateDebut"));
                experience.setDateFin(resultSet.getDate("description"));
                experience.setIdCandidat(resultSet.getInt("idUtilisateur"));
                experiences.add(experience);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la récupération des expériences", e);
        }
        return experiences;
    }

	@Override
	public void updateExperience(Experience experience) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteExperience(int idExperience) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Experience findExperienceParId(int idExperience) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

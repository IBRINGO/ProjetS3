package com.JAVA.DAO;

import com.JAVA.BEAN.Recruteur;
import com.JAVA.BEAN.User;
import com.JAVA.BEAN.Offre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecruteurDaoImpl implements RecruteurDao {
    private DAOFactory daoFactory;

    public RecruteurDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void addRecruteur(Recruteur recruteur) throws DAOException {
        String sql = "INSERT INTO recruteurs (idRecructeur, entreprise, telephone, adresse, siteWeb) VALUES (?, ?)";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        	statement.setInt(1, recruteur.getId());
            statement.setString(2, recruteur.getEntreprise());
            statement.setString(3, recruteur.getTelephone());
            statement.setString(4, recruteur.getAdresse());
            statement.setString(5, recruteur.getSiteWeb());
            statement.executeUpdate();
            
         // Récupération de l'ID généré
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int recruteurId = generatedKeys.getInt(1);
                    User user = new User(
                    		recruteur.getName(), 
                    		recruteur.getFirstName(), 
                    		recruteur.getEmail(), 
                    		recruteur.getPassword());
                    user.setId(recruteurId);
                    recruteur.setId(recruteurId);
                    addUser(user);
                }
            }
            
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de l'ajout du recruteur", e);
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
    public Recruteur findRecruteurById(int id) throws DAOException {
        String sql = "SELECT * FROM recruteurs WHERE id = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Recruteur recruteur = new Recruteur();
                    recruteur.setId(resultSet.getInt("id"));
                    recruteur.setEntreprise(resultSet.getString("entreprise"));
                    recruteur.setTelephone(resultSet.getString("telephone"));
                    recruteur.setAdresse(resultSet.getString("adresse"));
                    recruteur.setSiteWeb(resultSet.getString("siteWeb"));
                    // Récupérer les offres publiées par ce recruteur
                    recruteur.setOffresPubliees(trouverOffresParRecruteurId(recruteur.getId()));

                    return recruteur;
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la recherche du recruteur", e);
        }
        return null;
    }

    @Override
    public List<Recruteur> listRecruteurs() throws DAOException {
        List<Recruteur> recruteurs = new ArrayList<>();
        String sql = "SELECT * FROM recruteurs";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Recruteur recruteur = new Recruteur();
                recruteur.setId(resultSet.getInt("id"));
                recruteur.setEntreprise(resultSet.getString("entreprise"));
                recruteur.setTelephone(resultSet.getString("telephone"));
                recruteur.setAdresse(resultSet.getString("adresse"));
                recruteur.setSiteWeb(resultSet.getString("siteWeb"));

                // Récupérer les offres publiées par ce recruteur
                recruteur.setOffresPubliees(trouverOffresParRecruteurId(recruteur.getId()));

                recruteurs.add(recruteur);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la liste des recruteurs", e);
        }
        return recruteurs;
    }

    @Override
    public void updateRecruteur(Recruteur recruteur) throws DAOException {
        String sql = "UPDATE recruteurs SET entreprise = ?, telephone = ?, adresse = ?, siteWeb = ? WHERE id = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, recruteur.getEntreprise());
            statement.setString(2, recruteur.getTelephone());
            statement.setString(3, recruteur.getAdresse());
            statement.setString(4, recruteur.getSiteWeb());
            statement.setInt(5, recruteur.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la mise à jour du recruteur", e);
        }
    }

    @Override
    public void deleteRecruteur(int id) throws DAOException {
        String sql = "DELETE FROM recruteurs WHERE id = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la suppression du recruteur", e);
        }
    }

    /**
     * Méthode pour récupérer les offres publiées par un recruteur.
     *
     * @param recruteurId L'ID du recruteur
     * @return Liste des offres
     * @throws DAOException
     */
    private List<Offre> trouverOffresParRecruteurId(int recruteurId) throws DAOException {
        List<Offre> offres = new ArrayList<>();
        String sql = "SELECT * FROM offres WHERE idRecuteur = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, recruteurId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Offre offre = new Offre();
                    offre.setIdOffre(resultSet.getInt("id"));
                    offre.setTitre(resultSet.getString("titre"));
                    offre.setDescription(resultSet.getString("description"));
                    offre.setDatePublication(resultSet.getDate("datePublication"));
                    offre.setDateLimite(resultSet.getDate("dateLimite"));
                    offres.add(offre);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la récupération des offres publiées", e);
        }
        return offres;
    }
}

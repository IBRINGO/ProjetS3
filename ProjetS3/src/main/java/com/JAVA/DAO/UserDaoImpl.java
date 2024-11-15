package com.JAVA.DAO;

import com.JAVA.BEAN.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private DAOFactory daoFactory;

    public UserDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void addUser(User user) throws DAOException {
        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error adding user", e);
        }
    }

    @Override
    public void updateUser(int id, String name, String email, String password) throws DAOException {
        String query = "UPDATE users SET name = ?, email = ?, password = ? WHERE id = ?";
        try (Connection conn = daoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error updating user", e);
        }
    }

    @Override
    public void deleteUser(int id) throws DAOException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error deleting user", e);
        }
    }

    @Override
    public List<User> listUsers() throws DAOException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, name, firstname, email, password FROM users";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getString("id"));
                user.setName(resultSet.getString("name"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException("Error listing users", e);
        }
        return users;
    }
}
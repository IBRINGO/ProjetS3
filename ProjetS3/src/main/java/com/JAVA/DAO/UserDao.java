package com.JAVA.DAO;

import com.JAVA.BEAN.User;
import java.util.*;

public interface UserDao {
    void addUser(User user) throws DAOException;
    List<User> listUsers() throws DAOException;
    void updateUser(int id, String name, String email, String password) throws DAOException;
    void deleteUser(int id) throws DAOException;
    public User findUserByEmailAndPassword(String email, String password);
}
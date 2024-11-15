package com.JAVA.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.JAVA.DAO.DAOFactory;

/**
 * Servlet implementation class UserController
 */

import com.JAVA.DAO.UserDao;
import com.JAVA.BEAN.User;
import java.io.IOException;
import java.util.List;


@WebServlet("/users")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private UserDao userDao;

    @Override
    public void init() {
    	DAOFactory daoFactory = DAOFactory.getInstance();
        this.userDao = daoFactory.getUserDao(); // Assurez-vous que UserDAO est initialisé correctement
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Récupération de la liste des utilisateurs
            List<User> userList = userDao.listUsers();
            
            // Envoi de la liste à la page JSP
            request.setAttribute("userList", userList);
            
            // Redirection vers la page JSP pour affichage
            request.getRequestDispatcher("users.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error retrieving users", e);
        }
    }
}
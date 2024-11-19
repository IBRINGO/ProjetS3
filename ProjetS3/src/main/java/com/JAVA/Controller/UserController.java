package com.JAVA.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.JAVA.DAO.DAOException;
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
        this.userDao = daoFactory.getUserDao(); 
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
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    	 String action = request.getParameter("action");

         if ("signin".equals(action)) {
         	
         	  String username = request.getParameter("username");
               String password = request.getParameter("password");

               try {
                   User user = userDao.findUserByEmailAndPassword(username, password);
                   if (user != null) {
                       response.sendRedirect("welcome.jsp");
                   } else {
                       request.setAttribute("error", "Invalid username or password.");
                       request.getRequestDispatcher("loginPage.jsp").forward(request, response);
                   }
               } catch (DAOException e) {
                   throw new ServletException("Error during sign in", e);
               }
             //handleSignIn(request, response);
         } else if ("signup".equals(action)) {
         	
         	 String username = request.getParameter("userfname");
              String userfname = request.getParameter("username");
              String email = request.getParameter("email");
              String password = request.getParameter("password");

              try {
                  userDao.addUser(new User(username, userfname, email, password));
                  response.sendRedirect("loginPage.jsp");
              } catch (DAOException e) {
                  throw new ServletException("Error during sign up", e);
              }
             //handleSignUp(request, response);
         } else {
             response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action non reconnue.");
         }
    	
    }
    
}
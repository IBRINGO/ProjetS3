package com.JAVA.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.JAVA.BEAN.User;
import com.JAVA.DAO.DAOException;
import com.JAVA.DAO.DAOFactory;
import com.JAVA.DAO.UserDao;
import java.io.IOException;

/**
 * Servlet implementation class AuthenServlet
 */
@WebServlet("/AuthenServlet")
public class AuthenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    
    private UserDao userDao;

    @Override
    public void init() {
    	DAOFactory daoFactory = DAOFactory.getInstance();
        this.userDao = daoFactory.getUserDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("signin".equals(action)) {
        	
        	  String email = request.getParameter("email");
              String password = request.getParameter("password");

              try {
                  User user = userDao.findUserByEmailAndPassword(email, password);
                  if (user != null) {
                      response.sendRedirect("welcome.jsp");
                  } else {
                      request.setAttribute("error", "Invalid username or password.");
                      request.getRequestDispatcher("index.jsp").forward(request, response);
                  }
              } catch (DAOException e) {
                  throw new ServletException("Error during sign in", e);
              }
            //handleSignIn(request, response);
        } else if ("signup".equals(action)) {
        	 User user = new User();
        	 user.setFirstName( request.getParameter("userfname"));
             user.setName(request.getParameter("username"));
             user.setEmail(request.getParameter("email"));
             user.setPassword(request.getParameter("password"));
             try {
                 userDao.addUser(user);
                 response.sendRedirect("index.jsp");
             } catch (DAOException e) {
                 throw new ServletException("Error during sign up", e);
             }
            //handleSignUp(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action non reconnue.");
        }
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
}

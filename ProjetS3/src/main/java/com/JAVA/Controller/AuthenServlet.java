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
    private static final String PAGE_JSP = "/template/index.jsp";
    private static final String SIGNIN_PAGE = "template/login.jsp";
    private static final String SIGNUP_PAGE = "template/signup.jsp";

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
                      request.getRequestDispatcher("PAGE_JSP").forward(request, response);
                  } else {
                      request.setAttribute("error", "Invalid username or password.");
                      request.getRequestDispatcher(SIGNIN_PAGE).forward(request, response);
                  }
              } catch (DAOException e) {
                  throw new ServletException("Error during sign in", e);
              }
            //handleSignIn(request, response);
        } else if ("signup".equals(action)) {
        	 User user = new User();
        	 user.setFirstName( request.getParameter("fname"));
             user.setName(request.getParameter("name"));
             user.setEmail(request.getParameter("email"));
             user.setPassword(request.getParameter("password"));
             user.setType(request.getParameter("type"));
             
             try {
                 userDao.addUser(user);
                 
                 this.getServletContext().getRequestDispatcher(PAGE_JSP).forward(request, response);
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
        request.getRequestDispatcher(PAGE_JSP).forward(request, response);
	}
}

package com.JAVA.Controller;

import java.io.IOException;
import java.util.List;

import com.JAVA.BEAN.Competence;
import com.JAVA.DAO.CompetenceDao;
import com.JAVA.DAO.DAOException;
import com.JAVA.DAO.DAOFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CompetenceController")
public class CompetenceController extends HttpServlet {
    private static final String COMPETENCES = "competences";
    private static final String PAGE_JSP = "/Recruitment/competence.jsp";
    private static final String EDIT_PAGE = "/Recruitment/edit-competence.jsp";
    private CompetenceDao competenceDao;
    private DAOFactory daoFactory = DAOFactory.getInstance();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        this.competenceDao = daoFactory.getCompetenceDao();
        List<Competence> competences = competenceDao.listCompetences();

        String action = request.getParameter("action");
        if (action != null && "delete".equals(action)) {
            String competenceId = request.getParameter("id");
            try {
                competenceDao.deleteCompetence(Integer.parseInt(competenceId));
                request.setAttribute("message", "Compétence supprimée avec succès !");
            } catch (DAOException e) {
                request.setAttribute("error", "Erreur lors de la suppression de la compétence : " + e.getMessage());
            }
        } else if (action != null && "updating".equals(action)) {
            int competenceId = Integer.parseInt(request.getParameter("id"));
            try {
                Competence competence = competenceDao.findCompetenceById(competenceId);
                request.setAttribute("competence", competence);
            } catch (DAOException e) {
                request.setAttribute("error", "Erreur : compétence introuvable. " + e.getMessage());
            }
            this.getServletContext().getRequestDispatcher(EDIT_PAGE).forward(request, response);
            return;
        }

        request.setAttribute(COMPETENCES, competences);
        this.getServletContext().getRequestDispatcher(PAGE_JSP).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        this.competenceDao = daoFactory.getCompetenceDao();

        String action = request.getParameter("action");
        if (action != null) {
            if ("add".equals(action)) {
                String nom = request.getParameter("nom");
                String description = request.getParameter("description");

                Competence competence = new Competence();
                competence.setNom(nom);
                competence.setDescription(description);

                try {
                    competenceDao.addCompetence(competence);
                    request.setAttribute("message", "Compétence ajoutée avec succès !");
                } catch (DAOException e) {
                    request.setAttribute("error", "Erreur lors de l'ajout de la compétence : " + e.getMessage());
                }
            } else if ("update".equals(action)) {
                int competenceId = Integer.parseInt(request.getParameter("id"));
                Competence competence = new Competence();
                competence.setIdCompetence(competenceId);
                competence.setNom(request.getParameter("nom"));
                competence.setDescription(request.getParameter("description"));

                try {
                    competenceDao.updateCompetence(competence);
                    request.setAttribute("message", "Compétence mise à jour avec succès !");
                } catch (DAOException e) {
                    request.setAttribute("error", "Erreur lors de la mise à jour de la compétence : " + e.getMessage());
                }
            }
        }

        List<Competence> competences = competenceDao.listCompetences();
        request.setAttribute(COMPETENCES, competences);
        this.getServletContext().getRequestDispatcher(PAGE_JSP).forward(request, response);
    }
}

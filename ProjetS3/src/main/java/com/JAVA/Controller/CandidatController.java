package com.JAVA.Controller;

import java.io.IOException;
import java.util.List;

import com.JAVA.BEAN.Candidat;
import com.JAVA.DAO.CandidatDao;
import com.JAVA.DAO.DAOException;
import com.JAVA.DAO.DAOFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CandidatController")
public class CandidatController extends HttpServlet {
    private static final String CANDIDATS = "candidats";
    private static final String PAGE_JSP = "/Recruitment/candidat.jsp";
    private static final String EDIT_PAGE = "/Recruitment/edit-candidat.jsp";
    private CandidatDao candidatDao;
    private DAOFactory daoFactory = DAOFactory.getInstance();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        this.candidatDao = daoFactory.getCandidatDao();
        List<Candidat> candidats = candidatDao.listCandidats();

        String action = request.getParameter("action");
        if (action != null && "delete".equals(action)) {
            String candidatId = request.getParameter("id");
            try {
                candidatDao.deleteCandidat(Integer.parseInt(candidatId));
                request.setAttribute("message", "Candidat supprimé avec succès !");
            } catch (DAOException e) {
                request.setAttribute("error", "Erreur lors de la suppression du candidat : " + e.getMessage());
            }
        } else if (action != null && "updating".equals(action)) {
            int candidatId = Integer.parseInt(request.getParameter("id"));
            try {
                Candidat candidat = candidatDao.findCandidatById(candidatId);
                request.setAttribute("candidat", candidat);
            } catch (DAOException e) {
                request.setAttribute("error", "Erreur : candidat introuvable. " + e.getMessage());
            }
            this.getServletContext().getRequestDispatcher(EDIT_PAGE).forward(request, response);
            return;
        }

        request.setAttribute(CANDIDATS, candidats);
        this.getServletContext().getRequestDispatcher(PAGE_JSP).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        this.candidatDao = daoFactory.getCandidatDao();

        String action = request.getParameter("action");
        if (action != null) {
            if ("add".equals(action)) {
                String nom = request.getParameter("nom");
                String email = request.getParameter("email");
                String telephone = request.getParameter("telephone");
                String ville = request.getParameter("ville");
                String adresse = request.getParameter("adresse");

                Candidat candidat = new Candidat();
                candidat.setName(nom);
                candidat.setEmail(email);
                candidat.setTelephone(telephone);
                candidat.setVille(ville);
                candidat.setAdresse(adresse);

                try {
                    candidatDao.addCandidat(candidat);
                    request.setAttribute("message", "Candidat ajouté avec succès !");
                } catch (DAOException e) {
                    request.setAttribute("error", "Erreur lors de l'ajout du candidat : " + e.getMessage());
                }
            } else if ("update".equals(action)) {
                int candidatId = Integer.parseInt(request.getParameter("id"));
                Candidat candidat = new Candidat();
                candidat.setId(candidatId);
                candidat.setName(request.getParameter("nom"));
                candidat.setEmail(request.getParameter("email"));
                candidat.setTelephone(request.getParameter("telephone"));
                candidat.setVille(request.getParameter("ville"));
                candidat.setAdresse(request.getParameter("adresse"));

                try {
                    candidatDao.updateCandidat(candidat);
                    request.setAttribute("message", "Candidat mis à jour avec succès !");
                } catch (DAOException e) {
                    request.setAttribute("error", "Erreur lors de la mise à jour du candidat : " + e.getMessage());
                }
            }
        }

        List<Candidat> candidats = candidatDao.listCandidats();
        request.setAttribute(CANDIDATS, candidats);
        this.getServletContext().getRequestDispatcher(PAGE_JSP).forward(request, response);
    }
}

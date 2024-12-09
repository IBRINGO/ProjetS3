package com.JAVA.Controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.JAVA.BEAN.Candidature;
import com.JAVA.DAO.CandidatureDao;
import com.JAVA.DAO.DAOException;
import com.JAVA.DAO.DAOFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CandidatureController")
public class CandidatureController extends HttpServlet {
    private static final String CANDIDATURES = "candidatures";
    private static final String PAGE_JSP = "/recuiter/candidature.jsp";
    private static final String EDIT_PAGE = "/Recruitment/edit-candidature.jsp";
    private CandidatureDao candidatureDao;
    private DAOFactory daoFactory = DAOFactory.getInstance();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        this.candidatureDao = daoFactory.getCandidatureDao();
        List<Candidature> candidatures = candidatureDao.listerCandidatures();

        String action = request.getParameter("action");
        if (action != null && "delete".equals(action)) {
            String candidatureId = request.getParameter("id");
            try {
                candidatureDao.supprimerCandidature(Integer.parseInt(candidatureId));
                request.setAttribute("message", "Candidature supprimée avec succès !");
            } catch (DAOException e) {
                request.setAttribute("error", "Erreur lors de la suppression de la candidature : " + e.getMessage());
            }
        } else if (action != null && "updating".equals(action)) {
            int candidatureId = Integer.parseInt(request.getParameter("id"));
            try {
                Candidature candidature = candidatureDao.trouverCandidatureParId(candidatureId);
                request.setAttribute("candidature", candidature);
            } catch (DAOException e) {
                request.setAttribute("error", "Erreur : candidature introuvable. " + e.getMessage());
            }
            this.getServletContext().getRequestDispatcher(EDIT_PAGE).forward(request, response);
            return;
        }

        request.setAttribute(CANDIDATURES, candidatures);
        this.getServletContext().getRequestDispatcher(PAGE_JSP).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        this.candidatureDao = daoFactory.getCandidatureDao();

        String action = request.getParameter("action");
        if (action != null) {
            if ("add".equals(action)) {
                String statut = request.getParameter("statut");
                int idOffre = Integer.parseInt(request.getParameter("idOffre"));
                int idCandidat = Integer.parseInt(request.getParameter("idCandidat"));
                
                Candidature candidature = new Candidature();
                candidature.setStatut(statut);
                candidature.setIdOffre(idOffre);
                candidature.setIdCandidat(idCandidat);

                try {
                    candidatureDao.ajouterCandidature(candidature);
                    request.setAttribute("message", "Candidature ajoutée avec succès !");
                } catch (DAOException e) {
                    request.setAttribute("error", "Erreur lors de l'ajout de la candidature : " + e.getMessage());
                }
            } else if ("update".equals(action)) {
                int candidatureId = Integer.parseInt(request.getParameter("id"));
                Candidature candidature = new Candidature();
                candidature.setIdCandidature(candidatureId);
                candidature.setStatut(request.getParameter("statut"));
                //candidature.setDateSoumission(request.getParameter("dateSoumission"));
                candidature.setIdOffre(Integer.parseInt(request.getParameter("idOffre")));
                candidature.setIdCandidat(Integer.parseInt(request.getParameter("idCandidat")));

                try {
                    candidatureDao.modifierCandidature(candidature);
                    request.setAttribute("message", "Candidature mise à jour avec succès !");
                } catch (DAOException e) {
                    request.setAttribute("error", "Erreur lors de la mise à jour de la candidature : " + e.getMessage());
                }
            }
        }

        List<Candidature> candidatures = candidatureDao.listerCandidatures();
        request.setAttribute(CANDIDATURES, candidatures);
        this.getServletContext().getRequestDispatcher(PAGE_JSP).forward(request, response);
    }
}

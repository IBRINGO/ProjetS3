package com.JAVA.Controller;

import com.JAVA.BEAN.Formation;
import com.JAVA.DAO.DAOException;
import com.JAVA.DAO.DAOFactory;
import com.JAVA.DAO.FormationDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/FormationController")
public class FormationController extends HttpServlet {
    private static final String FORMATIONS = "formations";
    private static final String FORMATION_PAGE = "/admin/training.jsp";
    private static final String EDIT_PAGE = "/admin/editFormation.jsp";
    private FormationDao formationDao;

    @Override
    public void init() throws ServletException {
        this.formationDao = DAOFactory.getInstance().getFormationDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("delete".equals(action)) {
                int idFormation = Integer.parseInt(request.getParameter("idFormation"));
                formationDao.deleteFormation(idFormation);
                request.setAttribute("message", "Formation supprimée avec succès !");
            } else if ("edit".equals(action)) {
                int idFormation = Integer.parseInt(request.getParameter("idFormation"));
                Formation formation = formationDao.findFormationById(idFormation);
                request.setAttribute("formation", formation);
                this.getServletContext().getRequestDispatcher(EDIT_PAGE).forward(request, response);
                return;
            }
        } catch (DAOException e) {
            request.setAttribute("error", "Erreur : " + e.getMessage());
        }

        List<Formation> formations = formationDao.listFormations();
        request.setAttribute(FORMATIONS, formations);
        this.getServletContext().getRequestDispatcher(FORMATION_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                Formation formation = buildFormationFromRequest(request);
                formationDao.addFormation(formation);
                request.setAttribute("message", "Formation ajoutée avec succès !");
            } else if ("update".equals(action)) {
                Formation formation = buildFormationFromRequest(request);
                formation.setIdFormation(Integer.parseInt(request.getParameter("idFormation")));
                formationDao.updateFormation(formation);
                request.setAttribute("message", "Formation mise à jour avec succès !");
            }
        } catch (DAOException | ParseException e) {
            request.setAttribute("error", "Erreur : " + e.getMessage());
        }

        List<Formation> formations = formationDao.listFormations();
        request.setAttribute(FORMATIONS, formations);
        this.getServletContext().getRequestDispatcher(FORMATION_PAGE).forward(request, response);
    }

    private Formation buildFormationFromRequest(HttpServletRequest request) throws ParseException {
        Formation formation = new Formation();
        formation.setTitre(request.getParameter("titre"));
        formation.setDomaine(request.getParameter("sector"));
        formation.setDescription(request.getParameter("description"));
        formation.setDateDebut(parseDate(request.getParameter("dateDebut")));
        formation.setDateFin(parseDate(request.getParameter("dateFin")));
        formation.setAdmin(7);
        formation.setCertificatAttribue(Boolean.parseBoolean(request.getParameter("certificatAttribue")));
        return formation;
    }

    private Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(dateString);
    }
}

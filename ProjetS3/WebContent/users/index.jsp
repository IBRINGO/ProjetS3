<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.JAVA.BEAN.Offre, java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Interface Candidat</title>
    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="stylesheet" href="assets/css/template.css"> <!-- Styles du template -->
</head>
<body>
    <header class="template-header">
        <div class="container">
            <h1>Bienvenue, <%= session.getAttribute("nomCandidat") %></h1>
            <nav class="template-nav">
                <ul>
                    <li><a href="CandidatController?action=viewProfile">Mon Profil</a></li>
                    <li><a href="OffreController?action=listOffers">Offres d'emploi</a></li>
                    <li><a href="CandidatureController?action=listApplications">Mes Candidatures</a></li>
                    <li><a href="logout.jsp">Déconnexion</a></li>
                </ul>
            </nav>
        </div>
    </header>
    <main class="template-main">
        <div class="container">
            <h2>Tableau de bord</h2>
            <section class="template-section">
                <h3>Dernières offres disponibles</h3>
                <div class="offer-list">
                    <%-- Affichage dynamique des offres --%>
                    <%
                        
                        List<Offre> offres = (List<Offre>) request.getAttribute("offres");
                        if (offres != null) {
                            for (Offre offre : offres) {
                    %>
                    <div class="offer-card">
                        <h4><%= offre.getTitre() %></h4>
                        <p><%= offre.getDescription() %></p>
                        <p>Compétences requises : <%= offre.getCompetencesRequises() %></p>
                        <a class="btn btn-primary" href="CandidatureController?action=apply&offreId=<%= offre.getIdOffre() %>">Postuler</a>
                    </div>
                    <% 
                            }
                        } else {
                    %>
                    <p>Aucune offre disponible pour l'instant.</p>
                    <% } %>
                </div>
            </section>
        </div>
    </main>
    <footer class="template-footer">
        <div class="container">
            <p>&copy; 2024 Plateforme de Suivi de Recrutement</p>
        </div>
    </footer>
</body>
</html>

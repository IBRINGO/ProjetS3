<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.JAVA.BEAN.*, java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Interface Recruteur</title>
    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="stylesheet" href="assets/css/template.css"> <!-- Styles du template -->
</head>
<body>
    <header class="template-header">
        <div class="container">
            <h1>Bienvenue, <%= session.getAttribute("nomRecruteur") %></h1>
            <nav class="template-nav">
                <ul>
                    <li><a href="RecruteurController?action=viewProfile">Mon Profil</a></li>
                    <li><a href="OffreController?action=viewMyOffers">Mes Offres</a></li>
                    <li><a href="OffreController?action=addOffer">Publier une Offre</a></li>
                    <li><a href="logout.jsp">Déconnexion</a></li>
                </ul>
            </nav>
        </div>
    </header>
    <main class="template-main">
        <div class="container">
            <h2>Tableau de bord</h2>
            <section class="template-section">
                <h3>Mes Offres Publiées</h3>
                <div class="offer-list">
                    <%-- Affichage dynamique des offres du recruteur --%>
                    <%
                        List<Offre> mesOffres = (List<Offre>) request.getAttribute("mesOffres");
                        if (mesOffres != null) {
                            for (Offre offre : mesOffres) {
                    %>
                    <div class="offer-card">
                        <h4><%= offre.getTitre() %></h4>
                        <p><%= offre.getDescription() %></p>
                        <p>Nombre de candidatures reçues : <%= offre.getCandidaturesRecues().size() %></p>
                        <a class="btn btn-secondary" href="CandidatureController?action=view&offreId=<%= offre.getIdOffre() %>">Voir les Candidatures</a>
                    </div>
                    <% 
                            }
                        } else {
                    %>
                    <p>Aucune offre publiée pour l'instant.</p>
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

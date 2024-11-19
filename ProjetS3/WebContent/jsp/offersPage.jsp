<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Offres</title>
    <link rel="stylesheet" href="stylesoffer.css"> <!-- Assurez-vous d'avoir un fichier CSS -->
</head>
<body>
    <div class="section">
        <div class="section-header">
            <h2>Gestion des Offres</h2>
            <a href="AddOfferServlet" class="btn-add">
                <i>➕</i> Ajouter une Offre
            </a>
        </div>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Titre</th>
                    <th>Entreprise</th>
                    <th>Date de Publication</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                
               
            </tbody>
        </table>
    </div>
</body>
</html>

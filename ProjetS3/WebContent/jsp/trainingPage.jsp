<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Formations</title>
    <link rel="stylesheet" href="styles.css"> <!-- Assurez-vous d'avoir un fichier CSS -->
</head>
<body>
    <div class="section">
        <div class="section-header">
            <h2>Gestion des Formations</h2>
            <a href="AddTrainingServlet" class="btn-add">
                <i>➕</i> Ajouter une Formation
            </a>
        </div>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Durée</th>
                    <th>Institution</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
     
            </tbody>
        </table>
    </div>
</body>
</html>

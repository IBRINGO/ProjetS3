<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Formations</title>
    <link href="css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Manage Formations</h1>
        <!-- Message area -->
        <c:if test="${not empty message}">
            <div class="alert alert-success">${message}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <!-- Form to add a new formation -->
        <form action="FormationController" method="post" class="mb-4">
            <input type="hidden" name="action" value="add">
            <div class="form-group">
                <label for="titre">Titre:</label>
                <input type="text" class="form-control" id="titre" name="titre" required>
            </div>
            <div class="form-group">
                <label for="sector">Domaine:</label>
                <input type="text" class="form-control" id="sector" name="sector" required>
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
            </div>
            <div class="form-group">
                <label for="dateDebut">Date de Début:</label>
                <input type="date" class="form-control" id="dateDebut" name="dateDebut" required>
            </div>
            <div class="form-group">
                <label for="dateFin">Date de Fin:</label>
                <input type="date" class="form-control" id="dateFin" name="dateFin" required>
            </div>
            <div class="form-group">
                <label for="certificatAttribue">Certificat Attribué:</label>
                <select class="form-control" id="certificatAttribue" name="certificatAttribue" required>
                    <option value="true">Oui</option>
                    <option value="false">Non</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Ajouter Formation</button>
        </form>

        <!-- Table displaying formations -->
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Titre</th>
                    <th>Description</th>
                    <th>Date de Début</th>
                    <th>Date de Fin</th>
                    <th>Certificat Attribué</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="formation" items="${formations}">
                    <tr>
                        <td>${formation.idFormation}</td>
                        <td>${formation.titre}</td>
                        <td>${formation.description}</td>
                        <td>${formation.dateDebut}</td>
                        <td>${formation.dateFin}</td>
                        <td>${formation.certificatAttribue ? 'Oui' : 'Non'}</td>
                        <td>
                            <form action="FormationController" method="post" class="d-inline">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="idFormation" value="${formation.idFormation}">
                                <button type="submit" class="btn btn-danger btn-sm">Supprimer</button>
                            </form>
                            <a href="FormationController?action=edit&idFormation=${formation.idFormation}" class="btn btn-warning btn-sm">Modifier</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>

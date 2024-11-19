<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        body {
            display: flex;
            height: 100vh;
            background-color: #f4f4f9;
        }

        /* Menu vertical */
        .sidebar {
            width: 250px;
            background-color: #2c3e50;
            color: #fff;
            padding-top: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .sidebar h2 {
            margin-bottom: 20px;
            font-size: 20px;
            text-transform: uppercase;
            letter-spacing: 1px;
        }

        .sidebar ul {
            list-style-type: none;
            width: 100%;
        }

        .sidebar ul li {
            width: 100%;
        }

        .sidebar ul li a {
            display: block;
            padding: 15px;
            color: #fff;
            text-decoration: none;
            text-transform: capitalize;
            font-size: 16px;
            transition: 0.3s;
        }

        .sidebar ul li a:hover {
            background-color: #34495e;
            padding-left: 25px;
        }

        /* Contenu principal */
        .main-content {
            flex-grow: 1;
            padding: 20px;
        }

        .main-content h1 {
            margin-bottom: 20px;
            font-size: 24px;
            color: #2c3e50;
        }

        .main-content p {
            font-size: 16px;
            line-height: 1.6;
        }
    </style>
</head>
<body>
    <div class="sidebar">
        <h2>Dashboard</h2>
        <ul>
            <li><a href="#consult-offers">Consulter les offres</a></li>
            <li><a href="#apply">Candidater à une offre</a></li>
            <li><a href="#profile">Gérer son profil</a></li>
            <li><a href="#define-skills">Définir ses compétences</a></li>
            <li><a href="#training">S'inscrire à une formation</a></li>
            <li><a href="#evaluation">Passer une évaluation</a></li>
            <li><a href="#manage-offers">Gérer les offres (Recruteur)</a></li>
            <li><a href="#manage-users">Gérer les utilisateurs (Admin)</a></li>
            <li><a href="#manage-trainings">Gérer les formations (Admin)</a></li>
            <li><a href="#logout">Déconnexion</a></li>
        </ul>
    </div>
    <div class="main-content">
        <h1>Bienvenue sur le Dashboard</h1>
        <p>
            Utilisez le menu à gauche pour naviguer entre les différentes options :
            <ul>
                <li><strong>Candidat</strong> : consultez des offres, postulez, ou gérez votre profil.</li>
                <li><strong>Recruteur</strong> : publiez, modifiez ou supprimez des offres.</li>
                <li><strong>Admin</strong> : gérez les utilisateurs et les formations.</li>
            </ul>
        </p>
    </div>
</body>
</html>

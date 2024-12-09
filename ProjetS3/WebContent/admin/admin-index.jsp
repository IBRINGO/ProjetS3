<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <title>Admin Dashboard</title>
    <link href="admin/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
    <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
        <a class="navbar-brand ps-3" href="adminDashboard.jsp">Admin Panel</a>
    </nav>
    <div id="layoutSidenav">
        <div id="layoutSidenav_nav">
            <nav class="sb-sidenav accordion sb-sidenav-dark">
                <div class="sb-sidenav-menu">
                    <div class="nav">
                        <a class="nav-link" href="adminDashboard.jsp">
                            <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                            Dashboard
                        </a>
                        <a class="nav-link" href="manageUsers.jsp">
                            <div class="sb-nav-link-icon"><i class="fas fa-users"></i></div>
                            Manage Users
                        </a>
                        <a class="nav-link" href="manageCompetences.jsp">
                            <div class="sb-nav-link-icon"><i class="fas fa-brain"></i></div>
                            Manage Competences
                        </a>
                        <a class="nav-link" href="FormationController">
                            <div class="sb-nav-link-icon"><i class="fas fa-book"></i></div>
                            Manage Formations
                        </a>
                        <a class="nav-link" href="manageCandidatures.jsp">
                            <div class="sb-nav-link-icon"><i class="fas fa-file-alt"></i></div>
                            Manage Candidatures
                        </a>
                        <a class="nav-link" href="manageOffres.jsp">
                            <div class="sb-nav-link-icon"><i class="fas fa-briefcase"></i></div>
                            Manage Offres
                        </a>
                    </div>
                </div>
            </nav>
        </div>
        <div id="layoutSidenav_content">
            <main>
                <!-- Content specific to the admin -->
            </main>
        </div>
    </div>
</body>
</html>

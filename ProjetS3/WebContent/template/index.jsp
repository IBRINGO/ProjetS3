 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="template/styles.css">
    <title>E-Learning Platform</title>
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm sticky-top">
        <div class="container-fluid">
            <a class="navbar-brand fw-bold" href="#">SkillUp</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item"><a class="nav-link" href="#">Courses</a></li>
                    <li class="nav-item"><a class="nav-link" href="template/recruiter.jsp">For Recruiters</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">For Trainees</a></li>
                    <li class="nav-item"><a class="btn btn-primary  px-4 signup-btn" href="template/login.jsp">Log in</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Hero Section -->
   <!-- Hero Section -->
<header class="hero-section d-flex align-items-center">
    <div class="container">
        <div class="row align-items-center">
            <!-- Left Content -->
            <div class="col-md-6 text-center text-md-start">
                <h1 class=" display-4 fw-bold">Empower Your Career Journey</h1>
                <p class="lead text-muted">Bringing together trainees and recruiters to unlock potential.</p>
                <a class="btn btn-success btn-lg rounded-pill px-5 me-3 trainee-btn"  href="template/signup.jsp">Join as Trainee</a>
                <a class="btn btn-outline-primary btn-lg rounded-pill px-5 recruiter-btn" href="template/recruiter.jsp">Join as Recruiter</a>
            </div>
            <!-- Right Image -->
            <div class="col-md-6 text-center">
                <img src="template/home_banner.jpg" alt="Hero" class="img-fluid rounded">
            </div>
        </div>
    </div>
</header>


    <!-- Features Section -->
    <section class="features py-5">
        <div class="container text-center">
            <h2 class="fw-bold  mb-4">Why Choose SkillUp?</h2>
            <div class="row">
                <div class="col-md-4">
                    <img src="template/remote.jpg" alt="Icon 1" class="mb-3">
                    <h5 class="fw-bold">Flexible Learning</h5>
                    <p class="text-muted">Learn anytime, anywhere at your own pace.</p>
                </div>
                <div class="col-md-4">
                    <img src="template/mentor.jpg" alt="Icon 2" class="mb-3">
                    <h5 class="fw-bold">Expert Mentors</h5>
                    <p class="text-muted">Get guidance from industry professionals.</p>
                </div>
                <div class="col-md-4">
                    <img src="template/ready.jpg" alt="Icon 3" class="mb-3">
                    <h5 class="fw-bold">Job-Ready Skills</h5>
                    <p class="text-muted">Master the skills that employers look for.</p>
                </div>
            </div>
        </div>
    </section>

    <!-- Footer -->
    <footer class="text-center py-4">
        <p class="text-muted"><a href="">&copy; 2024 SkillUp.</a> All rights reserved.</p>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

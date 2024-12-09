<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - SkillUp</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="template/styles1.css">
</head>
<body>
    <!-- Main Container -->
    <div class="d-flex login-container">
        <!-- Left Side (Testimonial and Image) -->
        <div class="left-side d-flex flex-column justify-content-center align-items-center">
            <div class="testimonial text-center">
                <p class="testimonial-text">
                    "My job as a developer means that I’m always up to date in my area of expertise. It’s great to be able to share this with students."
                </p>
                <p class="testimonial-author">
                    Faycal Zehana - Development and Data Mentor, iOS Developer at Parrot
                </p>
            </div>
            
        </div>

        <!-- Right Side (Login Form) -->
        <div class="right-side d-flex justify-content-center align-items-center">
            <div class="login-form">
                <h1 class="text-center mb-4">Login to SkillUp</h1>
                <form action="../AuthenServlet" method="post">
                    <input type="hidden"  name="action" value="signin" >
                    <div class="mb-3">
                        <label for="email" class="form-label">Email Address</label>
                        <input type="email" name="email" class="form-control" id="email" placeholder="Enter your email" required>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password"  name="password" class="form-control" id="password" placeholder="Enter your password" required>
                    </div>
                    <button type="submit" class="btn btn-primary w-100">Log in</button>
                    <div class="mt-3 text-center">
                        <a href="#" class="text-muted">Forgot password?</a>
                    </div>
                    <div class="mt-4 text-center">
                        <p class="text-muted">Don't have an account? <a href="template/signup.jsp">Sign Up</a></p>
                    </div>
                </form>
            </div>
        </div>
    </div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

</html>
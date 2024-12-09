<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Signup - SkillUp</title>
    <link rel="stylesheet" href="styles2.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    
</head>
<body>
    <div class="container">
        <!-- Left Side -->
        
        <div class="left-side d-flex justify-content-center align-items-center" id="left-side">
            
            <div class="welcome-message text-center">
                <h2>Welcome, Recruiters!</h2>
                <p>Join us to find top talent and streamline your hiring process</p>
               
            </div>
        
    </div>
        <!-- Right Side -->
        <div class="right-side d-flex justify-content-center align-items-center">
            <div class="signup-form">
                <h1 class="text-center mb-4" id="decider">Sign up as Recruiter</h1>
                <form action="../AuthenServlet" method="post">
                    <div class="mb-3">
                        <input type="hidden" class="form-control" name="action" id="type" value="signup">
                        <input type="hidden" class="form-control" name="type" id="name" value="Recruteur">
                        <label for="name" class="form-label">First Name</label>
                        <input type="text" class="form-control" name="fname" id="name" placeholder="Enter your full name" required>
                    </div>
                    <div class="mb-3">
                        <label for="name" class="form-label">Last Name</label>
                        <input type="text" class="form-control" name="name" id="name" placeholder="Enter your full name" required>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email Address</label>
                        <input type="email" class="form-control" name="email" id="email" placeholder="Enter your email" required>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" class="form-control" name="password" id="password" placeholder="Enter your password" required>
                    </div>
                    <div class="mb-3">
                        <label for="confirm-password" class="form-label">Confirm Password</label>
                        <input type="password" class="form-control" id="confirm-password" placeholder="Confirm your password" required>
                    </div>
                    <button type="submit" class="btn btn-primary w-100">Sign Up</button>
                    <div class="mt-4 text-center">
                        <p class="text-muted">Already have an account? <a href="login.html">Log In</a></p>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="script.js"></script>
</body>
</html>

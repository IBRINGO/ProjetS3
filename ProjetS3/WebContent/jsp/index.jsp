<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>Authentification</title>
    
</head>
<body>
    <div class="container">
        <h2>S'authentifier</h2>
        <form action="AuthenServlet?action=signin" method="POST">
            <input type="text" name="email" placeholder="Email" required>
            <input type="password" name="password" placeholder="Password" required>
            <button type="submit">Login</button>
        </form>
        <div class="link">
        	 <span>Je n'ai pas un compte? S'enregistrer&nbsp; </span>  
            <a href="signUpPage.jsp">ici</a>
        </div>
    </div>
</body>
</html>

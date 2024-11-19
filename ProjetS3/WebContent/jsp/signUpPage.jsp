<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  
   
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>inscription</title>
</head>
<body>
    <div class="container">
        <h2>S'enregistrer</h2>
        <form action="AuthenServlet?action=signup" method="POST">
            <input type="text" name="userfname" placeholder="Your firstname" required>
            <input type="text" name="username" placeholder="Your lastname" required>
            <input type="email" name="email" placeholder="Your email" required>
            <input type="password" name="password" placeholder="Your password" required>
            <button type="submit">s'inscrire</button>
        </form>
        <div class="link">
        	<span>J'ai déjà un compte?</span>
            <a href="index.jsp"> se connecter</a>
        </div>
    </div>
</body>
</html>

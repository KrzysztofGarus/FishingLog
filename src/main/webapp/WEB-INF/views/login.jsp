<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Rejestracja</title>
</head>
<body>
<h1>Formularz logowania użytkownika</h1>
<form action="/login" method="post">

  <label for="username">Adres email:</label>
  <input type="email" id="username" name="username" required><br><br>

  <label for="password">Hasło:</label>
  <input type="password" id="password" name="password" required><br><br>

  <input type="submit" value="Zaloguj">
  <sec:csrfInput/>
</form>
</body>
</html>
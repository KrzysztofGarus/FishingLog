<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Rejestracja</title>
</head>
<body>
<h1>Formularz logowania użytkownika</h1>
<form action="/user/login" method="post">

  <label for="email">Adres email:</label>
  <input type="email" id="email" name="email" required><br><br>

  <label for="password">Hasło:</label>
  <input type="password" id="password" name="password" required><br><br>

  <input type="submit" value="Zaloguj">
</form>
</body>
</html>
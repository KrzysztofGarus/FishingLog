<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Rejestracja</title>
</head>
<body>
<h1>Formularz rejestracji użytkownika</h1>
<form action="/user/register" method="post">
    <label for="name">Imię:</label>
    <input type="text" id="name" name="name" required><br><br>

    <label for="surname">Nazwisko:</label>
    <input type="text" id="surname" name="surname" required><br><br>

    <label for="license">Numer licencji:</label>
    <input type="text" id="license" name="license" required><br><br>

    <label for="email">Adres email:</label>
    <input type="email" id="email" name="email" required><br><br>

    <label for="password">Hasło:</label>
    <input type="password" id="password" name="password" required><br><br>

    <input type="submit" value="Zarejestruj">
</form>
</body>
</html>
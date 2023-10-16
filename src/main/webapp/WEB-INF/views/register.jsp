<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <meta charset="UTF-8">
    <title>Rejestracja</title>
</head>
<body>
<div class="mx-auto" style="width: 500px; padding-top: 20px;">
    <h1>Formularz rejestracji użytkownika</h1>
    <form:form method="post" modelAttribute="userDto">
        <div class="mb-3">
            <label for="name" class="form-label">Imię</label>
            <form:input type="text" class="form-control" path="name" placeholder="Wpisz imię"/>
        </div>
        <div class="mb-3">
            <label for="surname" class="form-label">Nazwisko</label>
            <form:input type="text" class="form-control" path="surname" placeholder="Wpisz nazwisko"/>
        </div>
        <div class="mb-3">
            <label for="license" class="form-label">Numer licencji</label>
            <form:input type="number" class="form-control" path="license" placeholder="Wpisz numer karty wędkarskiej"/>
        </div>
        <div class="mb-3">
            <label for="username" class="form-label">E-mail</label>
            <form:input type="email" class="form-control" path="username" placeholder="Wpisz adres e-mail"/>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Hasło</label>
            <form:input type="password" class="form-control" path="password" placeholder="Wpisz hasło"/>
        </div>
        <form:button type="submit" class="btn btn-primary">Zarejestruj</form:button>
        <sec:csrfInput/>
    </form:form>
</div>

</body>
</html>
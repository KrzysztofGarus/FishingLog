<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Nazwy Ryb</title>
    <link rel="stylesheet" href=<c:url value="/css/style.css"/>>
</head>
<body>
<div>
    <a class="btn btn-primary" href='<c:url value="/admin/dashboard"/>' role="button">Dashboard</a>
    <a class="btn btn-success" href='<c:url value="/admin/fish/add"/>' role="button">Dodaj Rybę</a>
</div>
<div>
    <table class="styled-table">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${fishNameList}" var="fishName">
            <tr>
                <td>${fishName.id}</td>
                <td>${fishName.name}</td>
                <td>
                    <a class="btn btn-warning" href='<c:url value="/admin/fish/update?id=${fishName.id}"/>' role="button">Edytuj</a>
                    <a class="btn btn-danger" href='<c:url value="/admin/fish/delete?id=${fishName.id}"/>' role="button">Usuń</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

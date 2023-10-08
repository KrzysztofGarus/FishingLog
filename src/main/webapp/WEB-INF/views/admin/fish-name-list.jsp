<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Nazwy Ryb</title>
    <link rel="stylesheet" href=<c:url value="/css/style.css"/>>
</head>
<body>
<div>
    <a href='<c:url value="/admin/fish/add"/>'>Dodaj Rybę</a>
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
                <td><a href="/admin/fish/update?id=${fishName.id}">Edytuj</a><br/>
                    <a href="/admin/fish/delete?id=${fishName.id}">Usuń</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

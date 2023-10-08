<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Nazwy spotów</title>
    <link rel="stylesheet" href=<c:url value="/css/style.css"/>>
</head>
<body>
<div>
    <a href='<c:url value="/admin/spot/add"/>'>Dodaj Spot</a>
</div>
<div>
    <table class="styled-table">
        <tr>
            <th>Name</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${fishingSpotList}" var="fishingSpot">
            <tr>
                <td>${fishingSpot.name}</td>
                <td><a href="/admin/spot/details?id=${fishingSpot.id}">Szczegóły</a><br/>
                    <a href="/admin/spot/update?id=${fishingSpot.id}">Edytuj</a><br/>
                    <a href="/admin/spot/delete?id=${fishingSpot.id}">Usuń</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

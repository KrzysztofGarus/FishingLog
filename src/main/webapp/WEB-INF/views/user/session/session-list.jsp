<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sesje wędkarskie</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href=<c:url value="/css/style.css"/>>
</head>
<body>
<div>
    <a class="btn btn-primary" href='<c:url value="/user/session/add"/>' role="button">Dodaj sesję</a>
</div>
<div>
    <table class="styled-table">
        <tr>
            <th>Date</th>
            <th>Spot name</th>
            <th>Fishes</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${fishingSessions}" var="fishingSession">
            <tr>
                <td>${fishingSession.date}</td>
                <td>${fishingSession.fishingSpot.name}</td>
                <td>
                    <ul>
                        <c:forEach items="${fishingSession.fishList}" var="fish">
                            <li>${fish.fishName.name} / ${fish.weight} kg / ${fish.length} cm <a href="/user/fish/update?id=${fish.id}">Edytuj</a>
                                <a href="/user/fish/delete?id=${fish.id}">Usuń</a></li>

                        </c:forEach>
                        <li>
                            <a class="btn btn-success" href='<c:url value="/user/fish/add?sessionId=${fishingSession.id}"/>' role="button">Dodaj rybę</a>
                        </li>
                    </ul>
                </td>
                <td><a class="btn btn-warning" href='<c:url value="/user/session/update?id=${fishingSession.id}"/>' role="button">Edytuj</a>
                    <a class="btn btn-danger" href='<c:url value="/user/session/delete?id=${fishingSession.id}"/>' role="button">Usuń</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

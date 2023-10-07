<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sesje wędkarskie</title>
    <link rel="stylesheet" href=<c:url value="/css/style.css"/>>
</head>
<body>
<div>
    <a href='<c:url value="/user/session/add"/>'>Dodaj sesję</a>
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
                            <a href="/user/fish/add?sessionId=${fishingSession.id}">Dodaj rybę</a>
                        </li>
                    </ul>
                </td>
                <td><a href="/user/session/update?id=${fishingSession.id}">Edytuj</a><br/>
                    <a href="/user/session/delete?id=${fishingSession.id}">Usuń</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

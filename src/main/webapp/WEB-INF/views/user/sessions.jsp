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
    <table>
        <tr>
            <th>Id</th>
            <th>Date</th>
            <th>Spot name</th>
            <th>Fishes</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${fishingSessions}" var="fishingSession">
            <tr>
                <td>${fishingSession.id}</td>
                <td>${fishingSession.date}</td>
                <td>${fishingSession.fishingSpot.name}</td>
                <td>
                    <ul>
                        <c:forEach items="${fishingSession.fishList}" var="fish">
                            <li>${fish.name} ${fish.weight} kg ${fish.length} cm</li>
                        </c:forEach>
                    </ul>
                </td>
                <td><a href="/views/sessions/update?id=${fishingSession.id}">Edytuj</a><br/>
                    <a href="/views/sessions/delete?id=${fishingSession.id}">Usuń</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Usuń sesję</title>
</head>
<body>
<h1>Usuń sesję</h1>
<p>Czy chcesz usunąć sesję?</p>
<p>Data: ${fishingSession.date}</p>
<p>Spot: ${fishingSession.fishingSpot.name}</p>
<form:form method="post" action="${pageContext.request.contextPath}/user/session/delete" csrf="${_csrf.parameterName}" csrf_token="${_csrf.token}">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="hidden" name="id" value="${fishingSession.id}" />
    <button type="submit">Usuń sesję</button>
</form:form>
</body>
</html>

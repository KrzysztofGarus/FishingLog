<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Usuń Rybę</title>
</head>
<body>
<h1>Usuń Rybę</h1>
<p>Czy chcesz usunąć rybę?</p>
<p>Nazwa: ${fish.fishName.name}</p>
<p>Waga: ${fish.weight}</p>
<p>Długość: ${fish.length}</p>
<form:form method="post" action="${pageContext.request.contextPath}/user/fish/delete" csrf="${_csrf.parameterName}" csrf_token="${_csrf.token}">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="hidden" name="fishId" value="${fish.id}" />
    <input type="hidden" name="sessionId" value="${fish.fishingSession.id}" />
    <button type="submit">Usuń rybę</button>
</form:form>
</body>
</html>
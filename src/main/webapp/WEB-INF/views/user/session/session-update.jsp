<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edytuj Sesję</title>
</head>
<body>
<form:form method="post" modelAttribute="fishingSession">
    <c:set var="currentDate" value="<fmt:formatDate>" />
    Data: <form:input path="date" type="date" value="${currentDate}" />
    Spot: <form:select path="fishingSpot.id" items="${spotList}" itemLabel="name" itemValue="id" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <form:button>Zapisz</form:button>
</form:form>
</body>
</html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edytuj Rybę</title>
</head>
<body>
<form:form method="post" modelAttribute="fish">
    Nazwa: <form:select path="fishName.id" items="${fishNames}" itemLabel="name" itemValue="id" value="${fish.fishName.name}" />
    Waga: <form:input path="weight" type="number" min="0" max="9999" step="0.1"/>
    Długość: <form:input path="length" type="number" min="0" max="999" step="0.1"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <form:button>Zapisz zmiany</form:button>
</form:form>
</body>
</html>
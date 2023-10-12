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
<form:form method="post" modelAttribute="fishName">
    Nazwa: <form:input path="name" value="${fishName.name}"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <form:button>Zapisz zmiany</form:button>
</form:form>
</body>
</html>

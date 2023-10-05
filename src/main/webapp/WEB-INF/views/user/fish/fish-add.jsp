<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Dodaj Rybę</title>
</head>
<body>
<form:form method="post" modelAttribute="fish">
  Nazwa: <form:input path="name" type="text"/>
  Waga: <form:input path="weight" type="number" min="0" max="9999" step="0.1"/>
  Długość: <form:input path="length" type="number" min="0" max="999" step="0.1"/>
  <form:input path="fishingSession" type="hidden" value="${fishingSession}"/>
  <form:button>Dodaj rybę</form:button>
</form:form>
</body>
</html>
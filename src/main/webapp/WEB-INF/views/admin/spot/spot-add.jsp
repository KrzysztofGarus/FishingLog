<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dodaj nowy spot</title>
</head>
<body>
<form:form method="post" modelAttribute="fishingSpot">
    Nazwa: <form:input path="name"/>
    <form:button>Dodaj spot</form:button>
</form:form>
</body>
</html>
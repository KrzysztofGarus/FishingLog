<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dodaj Sesję</title>
</head>
<body>
<form:form method="post" modelAttribute="fishingSession">
    Data: <form:input path="date" type="date" />
    Spot: <form:select path="fishingSpot.id" items="${spotList}" itemLabel="name" itemValue="id" />
    <form:button>Zacznij wędkować</form:button>
</form:form>
</body>
</html>
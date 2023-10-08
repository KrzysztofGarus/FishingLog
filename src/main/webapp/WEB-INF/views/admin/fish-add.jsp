<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dodaj nową rybę</title>
</head>
<body>
<form:form method="post" modelAttribute="fishName">
    Nazwa: <form:input path="name"/>
    <form:button>Dodaj rybę</form:button>
</form:form>
</body>
</html>
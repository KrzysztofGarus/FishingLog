<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <style>
        body {
            display: flex;
            flex-direction: column; /* Ustawienie kierunku na kolumnowy */
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }

        .content {
            margin-top: 20px; /* Dodatkowy margines między elementami */
        }

        img {
            max-width: 100%;
            max-height: 100%;
        }
    </style>
</head>
<body>
<div class="content">
    <h1>${errorMsg}</h1>
</div>
<div class="content">
    <img src="${pageContext.request.contextPath}/img/img.png">
</div>
</body>
</html>

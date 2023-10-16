<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Lista sesji</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/dashboard/dashboard.css" rel="stylesheet">
</head>
<body>
<!-- Header -->
<jsp:include page="app-header.jsp"/>
<!-- Header -->

<div class="container-fluid">
    <div class="row">
        <!-- Admin side menu -->
        <jsp:include page="user-menu.jsp"/>
        <!-- Admin side menu -->

        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <!-- Page Name -->
                <h2 class="h2">Lista sesji</h2>
                <!-- Page Name -->
            </div>
            <!-- Page Content -->
            <div class="container-fluid">
                <div class="row" style="text-align: left">
                    <div>
                        <a class="btn btn-success" href='<c:url value="/user/session/add"/>' role="button">Dodaj sesję</a>
                    </div>
                </div>
                <div class="row" style="text-align: center">
                    <div class="mx-lg-1" style="width: 900px; padding-top: 20px">
                        <table class="table table-striped">
                            <tr>
                                <th>Data</th>
                                <th>Łowisko</th>
                                <th>Ryby</th>
                                <th>Akcja</th>
                            </tr>
                            <c:forEach items="${fishingSessions}" var="fishingSession">
                                <tr>
                                    <td>${fishingSession.date}</td>
                                    <td>${fishingSession.fishingSpot.name}</td>
                                    <td>
                                        <ul class="list-unstyled">
                                            <c:forEach items="${fishingSession.fishList}" var="fish">
                                                <li>${fish.fishName.name} / ${fish.weight} kg / ${fish.length} cm <a href="${pageContext.request.contextPath}/user/fish/update?id=${fish.id}">Edytuj</a>
                                                    <a href="${pageContext.request.contextPath}/user/fish/delete?id=${fish.id}">Usuń</a></li>

                                            </c:forEach>
                                            <li>
                                                <a class="btn btn-success" href='<c:url value="/user/fish/add?sessionId=${fishingSession.id}"/>' role="button">Dodaj rybę</a>
                                            </li>
                                        </ul>
                                    </td>
                                    <td><a class="btn btn-warning" href='<c:url value="/user/session/update?id=${fishingSession.id}"/>' role="button">Edytuj</a>
                                        <a class="btn btn-danger" href='<c:url value="/user/session/delete?id=${fishingSession.id}"/>' role="button">Usuń</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js" integrity="sha384-uO3SXW5IuS1ZpFPKugNNWqTZRRglnUJK6UAZ/gxOX80nxEkN9NcGZTftn6RzhGWE" crossorigin="anonymous"></script><script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js" integrity="sha384-zNy6FEbO50N+Cg5wap8IKA4M/ZnLJgzc6w2NqACZaK0u0FXfOWRRJOnQtpZun8ha" crossorigin="anonymous"></script><script src="/js/dashboard/dashboard.js"></script>

</body>
</html>



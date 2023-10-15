<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Lista łowisk</title>

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
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load("current", {packages:["corechart"]});
        google.charts.setOnLoadCallback(drawChart);
        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['Fish Name', 'Count'],
                <c:forEach var="fishCount" items="${fishNamesCount}">
                ['${fishCount.name}', ${fishCount.count}],
                </c:forEach>
            ]);

            var options = {
                title: 'Rozkład procentowy złowionych ryb',
                pieHole: 0.4,
            };

            var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
            chart.draw(data, options);
        }
    </script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load("current", {packages:["calendar"]});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {
            var dataTable = new google.visualization.DataTable();
            dataTable.addColumn({ type: 'date', id: 'Date' });
            dataTable.addColumn({ type: 'number', id: 'Count' });
            dataTable.addRows([
                <c:forEach var="sdc" items="${SessionDateCount}">
                [new Date(${sdc.stringDate}), ${sdc.count}],
                </c:forEach>
            ]);

            var chart = new google.visualization.Calendar(document.getElementById('calendar_basic'));

            var options = {
                title: "Kalendarz sesji wędkarskich",
                height: 350,
                noDataPattern: {
                    backgroundColor: '#008000',
                    color: '#98d498'
                }
            };

            chart.draw(dataTable, options);
        }
    </script>
</head>
<body>
<!-- Header -->
<jsp:include page="app-header.jsp"/>
<!-- Header -->

<div class="container-fluid">
    <div class="row">
        <!-- Admin side menu -->
        <jsp:include page="admin-menu.jsp"/>
        <!-- Admin side menu -->

        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <!-- Page Name -->
                <h2 class="h2">Szczegóły łowiska</h2>
                <!-- Page Name -->
            </div>
            <!-- Page Content -->
            <div class="container-fluid" style="text-align: center">
                <div id="calendar_basic" style="width: 1000px; height: 250px;"></div>
                <div style="display: flex;">
                    <div class="mx-lg-1" style="width: 600px; padding-top: 20px">
                        <table class="table table-striped">
                            <tr>
                                <th>Nazwa</th>
                                <th>Ilość</th>
                                <th>Suma wagi</th>
                            </tr>
                            <c:forEach items="${fishNamesCount}" var="fish">
                                <tr>
                                    <td>${fish.name}</td>
                                    <td>${fish.count}</td>
                                    <td>${fish.sumWeight}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <div id="donutchart" style="width: 600px; height: 400px;"></div>
                </div>
            </div>
        </main>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js" integrity="sha384-uO3SXW5IuS1ZpFPKugNNWqTZRRglnUJK6UAZ/gxOX80nxEkN9NcGZTftn6RzhGWE" crossorigin="anonymous"></script><script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js" integrity="sha384-zNy6FEbO50N+Cg5wap8IKA4M/ZnLJgzc6w2NqACZaK0u0FXfOWRRJOnQtpZun8ha" crossorigin="anonymous"></script><script src="/js/dashboard/dashboard.js"></script>

</body>
</html>
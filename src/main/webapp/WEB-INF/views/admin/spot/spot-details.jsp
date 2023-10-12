<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en-US">
<head>
    <style>
        body {
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh; /* Minimalna wysokość, aby wypełnić cały widok */
        }

        header, footer {
            width: 100%;
            box-sizing: border-box;
            padding: 20px;
        }

        .content {
            flex: 1; /* Zajmuje dostępną przestrzeń, reszta wysokości */
            display: flex;
        }

        .left-column {
            width: 60%;
            box-sizing: border-box;
            padding: 20px;
            height: 100%;
            overflow-y: auto;
        }
        .right-column {
            width: 40%;
            box-sizing: border-box;
            padding: 20px;
            height: 100%; /
            overflow-y: auto;
        }

        footer {
            margin-top: auto;
        }
    </style>
    <link rel="stylesheet" href=<c:url value="/css/style.css"/>>
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
<header>
    <h2>Spot info</h2>
</header>
<div class="content">
    <div class="left-column">
        <div id="calendar_basic" style="width: 1000px; height: 350px;"></div>
    </div>
    <div class="right-column">
        <div id="donutchart" style="width: 600px; height: 400px;"></div>
    </div>
</div>

<footer>
    <table style="width: 100%;">
        <tr>
            <td align="center">
                <table class="styled-table">
                    <tr>
                        <th>Name</th>
                        <th>Count</th>
                        <th>Sum weight</th>
                    </tr>
                    <c:forEach items="${fishNamesCount}" var="fish">
                        <tr>
                            <td>${fish.name}</td>
                            <td>${fish.count}</td>
                            <td>${fish.sumWeight}</td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
    </table>
</footer>
</body>
</html>






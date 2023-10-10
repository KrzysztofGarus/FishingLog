<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en-US">
<head>
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
                title: 'Wykres złowionych ryb',
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
                title: "Fishing Spot Attendance",
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
<div id="donutchart" style="width: 900px; height: 500px;"></div>
<div id="calendar_basic" style="width: 1000px; height: 350px;"></div>
</body>
</html>






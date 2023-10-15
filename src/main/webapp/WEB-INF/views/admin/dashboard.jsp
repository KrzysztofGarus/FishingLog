<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Admin Dashboard</title>

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
    google.charts.load("current", {packages:["calendar"]});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {
      var dataTable = new google.visualization.DataTable();
      dataTable.addColumn({ type: 'date', id: 'Date' });
      dataTable.addColumn({ type: 'number', id: 'Count' });
      dataTable.addRows([
        <c:forEach var="sdc" items="${sessionDateCount}">
        [new Date(${sdc.stringDate}), ${sdc.count}],
        </c:forEach>
      ]);

      var chart = new google.visualization.Calendar(document.getElementById('calendar_basic'));

      var options = {
        title: "Kalendarz wszystkich sesji wędkarskich",
        height: 500,
        width: 1200,
        calendar: { cellSize: 20},
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
        <h2 class="h2">Dashboard</h2>
        <!-- Page Name -->
      </div>
        <!-- Page Content -->
      <div class="container-fluid">
        <div class="row">
          <div class="col-lg-3 col-6">
            <div class="card widget-flat">
              <div class="card-body">
                <div class="float-end">
                  <i class="mdi mdi-account-multiple widget-icon"></i>
                </div>
                <h5 class="text-muted fw-normal mt-0" title="Number of Customers">Użytkownicy</h5>
                <h3 class="mt-3 mb-3">${activeUsersCount}</h3>
                <p class="mb-0 text-muted">
                  <span class="text-nowrap">Liczba aktywnych</span>
                </p>
              </div>
            </div>
          </div>

          <div class="col-lg-3 col-6">

            <div class="card widget-flat">
              <div class="card-body">
                <div class="float-end">
                  <i class="mdi mdi-account-multiple widget-icon"></i>
                </div>
                <h5 class="text-muted fw-normal mt-0" title="Number of Customers">Sesje</h5>
                <h3 class="mt-3 mb-3">${fishingSessionsCount}</h3>
                <p class="mb-0 text-muted">
                  <span class="text-nowrap">Suma wszystkich sesji</span>
                </p>
              </div>
            </div>
          </div>

          <div class="col-lg-3 col-6">

            <div class="card widget-flat">
              <div class="card-body">
                <div class="float-end">
                  <i class="mdi mdi-account-multiple widget-icon"></i>
                </div>
                <h5 class="text-muted fw-normal mt-0" title="Number of Customers">Ryby</h5>
                <h3 class="mt-3 mb-3">${fishCount}</h3>
                <p class="mb-0 text-muted">
                  <span class="text-nowrap">Ilość złowionych ryb</span>
                </p>
              </div>
            </div>
          </div>

          <div class="col-lg-3 col-6">
            <div class="card widget-flat">
              <div class="card-body">
                <div class="float-end">
                  <i class="mdi mdi-account-multiple widget-icon"></i>
                </div>
                <h5 class="text-muted fw-normal mt-0" title="Number of Customers">Popularne łowisko</h5>
                <h3 class="mt-3 mb-3">${popularSpot}</h3>
                <p class="mb-0 text-muted">
                  <span class="text-nowrap">Najwięcej sesji wędkarskich</span>
                </p>
              </div>
            </div>
          </div>
        </div>
        <div class="row" style="padding-top: 20px">
          <div id="calendar_basic" style="width: 500px; height: 100px;"></div>
        </div>
      </div>
    </main>
  </div>
</div>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js" integrity="sha384-uO3SXW5IuS1ZpFPKugNNWqTZRRglnUJK6UAZ/gxOX80nxEkN9NcGZTftn6RzhGWE" crossorigin="anonymous"></script><script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js" integrity="sha384-zNy6FEbO50N+Cg5wap8IKA4M/ZnLJgzc6w2NqACZaK0u0FXfOWRRJOnQtpZun8ha" crossorigin="anonymous"></script><script src="/js/dashboard/dashboard.js"></script>

</body>
</html>
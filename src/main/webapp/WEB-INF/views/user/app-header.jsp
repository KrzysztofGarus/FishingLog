<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3" href="#">Fishing App v1.0</a>
    <div class="navbar-nav">
        <div class="nav-item text-nowrap">
            <a class="btn btn-outline-success" href='<c:url value="/logout"/>' role="button">Logout</a>
        </div>
    </div>
</header>
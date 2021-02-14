<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 05.01.2021
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="${sessionScope.rb}" var="rb"/>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">


    <title><fmt:message key="home.header" bundle="${rb}"/></title>
</head>
<c:import url="MainHeader.jsp"/>
<div>
    <h1 class="display-6"><p class="text-center"><fmt:message key="intro.header" bundle="${rb}"/></p></h1>
    <p class="lead text-center"><fmt:message key="intro.1" bundle="${rb}"/></p>
    <p class="text-secondary text-center"><fmt:message key="intro.2" bundle="${rb}"/></p>
</div>
<img src="<c:url value="/pharmacy-background.jpg"/>" class="img-fluid" alt="pharmacy">

<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4">
        <h3><p class="lead text-primary text-center"><fmt:message key="abilities.header" bundle="${rb}"/></p></h3>
        <ul class="list-style">
            <li><fmt:message key="abilities.list.1" bundle="${rb}"/></li>
            <li><fmt:message key="abilities.list.2" bundle="${rb}"/></li>
            <li><fmt:message key="abilities.list.3" bundle="${rb}"/></li>
        </ul>
    </div>
    <div class="col-md-4"></div>
</div>
______________________________________________________________________________________________________________________________________________________________________________________________
<div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-6">
        <h3><p class="lead text-primary text-center"><fmt:message key="abilities.order.header" bundle="${rb}"/></p></h3>
        <ul>
            <li><fmt:message key="abilities.order.1" bundle="${rb}"/></li>
            <li><fmt:message key="abilities.order.2" bundle="${rb}"/></li>
        </ul>
    </div>
    <div class="col-md-3"></div>
</div>
______________________________________________________________________________________________________________________________________________________________________________________________

<div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-9">
        <h2><p class="lead text-danger text-center"><fmt:message key="attention.header" bundle="${rb}"/></p></h2>
        <h3><p class="lead text-dark text-center"><fmt:message key="attention.info" bundle="${rb}"/></p></h3>
        <ul>
            <li><fmt:message key="attention.info.1" bundle="${rb}"/></li>
            <li><fmt:message key="attention.info.2" bundle="${rb}"/></li>
            <li><fmt:message key="attention.info.3" bundle="${rb}"/></li>
        </ul>
    </div>
    <div class="col-md-2"></div>
</div>
______________________________________________________________________________________________________________________________________________________________________________________________

<div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-9">
        <h2><p class="lead text-danger text-center"><fmt:message key="prescription.header" bundle="${rb}"/></p></h2>
        <ul>
            <li><fmt:message key="prescription.list.1" bundle="${rb}"/></li>
            <li><fmt:message key="prescription.list.2" bundle="${rb}"/></li>
            <li><fmt:message key="prescription.list.3" bundle="${rb}"/></li>
        </ul>
    </div>
    <div class="col-md-2"></div>
</div>

______________________________________________________________________________________________________________________________________________________________________________________________
<div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-9">
        <h2><p class="lead text-danger text-center"><fmt:message key="staff.header" bundle="${rb}"/></p></h2>
        <h3><p class="lead text-dark text-center"><fmt:message key="staff.info" bundle="${rb}"/></p></h3>
        <ul>
            <li><fmt:message key="staff.list.1" bundle="${rb}"/></li>
            <li><fmt:message key="staff.list.2" bundle="${rb}"/></li>
            <li><fmt:message key="staff.list.3" bundle="${rb}"/></li>
        </ul>
    </div>
    <div class="col-md-2"></div>
</div>
______________________________________________________________________________________________________________________________________________________________________________________________

<h2><p class="text-center"><fmt:message key="join" bundle="${rb}"/></p></h2>
</body>
</html>

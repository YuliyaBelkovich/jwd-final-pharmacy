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
    <h1 class="display-6"><p class="text-center">SACRED HEART PHARMACY is more than you think!</p></h1>
    <p class="lead text-center">Our pharmacy offers not only a broad range of medications, herbals, and wellness
        cosmetics — with the help of our service you can make a doctor's appointment, refill your prescription and buy
        the prescribed medication!</p>
    <p class="text-secondary text-center">All products and medications undergo state registration and certification by
        the relevant authorities of the Ministry of Health of the Republic of Belarus.</p>
</div>
<img src="<c:url value="/pharmacy-background.jpg"/>" class="img-fluid" alt="pharmacy">

<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4">
        <h3><p class="lead text-primary text-center">YOU CAN</p></h3>
        <ul class="list-style">
            <li>Buy OTC and prescribed medications</li>
            <li>Make an appointment for a consult or to get a prescription</li>
            <li>Request to refill your prescription in case of the prescription expiration</li>
        </ul>
    </div>
    <div class="col-md-4"></div>
</div>
______________________________________________________________________________________________________________________________________________________________________________________________
<div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-6">
        <h3><p class="lead text-primary text-center">How to order? It's easy!</p></h3>
        <ul>
            <li>Choose needed products in the online catalog or by the search, purchase</li>
            <li>Await an e-mail with the order number and a call from our operator to confirm the date and time of receiving an order</li>
        </ul>
    </div>
    <div class="col-md-3"></div>
</div>
______________________________________________________________________________________________________________________________________________________________________________________________

<div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-9">
        <h2><p class="lead text-danger text-center">Attention!</p></h2>
        <h3><p class="lead text-dark text-center">Some types of medications require a prescription. To purchase them without the prescription you need to:</p></h3>
        <ul>
            <li>Register in our system</li>
            <li>Choose a needed specialist and make an appointment</li>
            <li>Await an e-mail with the order number and a call from our operator to confirm the appointment date and time</li>
        </ul>
    </div>
    <div class="col-md-2"></div>
</div>
______________________________________________________________________________________________________________________________________________________________________________________________

<h3><p class="lead text-primary">To refill an expiring prescription you need to:</p></h3>
<ul>
<li>Log in to the system by your name and password</li>
    <li>Find your doctor and fill out the application form for the refill</li>
    <li>Wait for the refill to appear in 2-3 workdays</li>
</ul>
______________________________________________________________________________________________________________________________________________________________________________________________

<h3><p class="lead text-primary">For med staff:</p></h3>
<p class="text-dark">To join our med staff base you need to sign up to our system by choosing the med staff category. We will need 2-3 workdays to confirm your status as a medic professional. This will give you access to:</p>
<ul>
    <li>Control prescriptions of your patients</li>
    <li>Easily contact your patients if needed</li>
    <li>Quickly refill prescriptions</li>
</ul>
______________________________________________________________________________________________________________________________________________________________________________________________

<h2><p class="text-center">Come join!</p></h2>
</body>
</html>

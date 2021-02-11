<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 11.01.2021
  Time: 23:45
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
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">


    <title><fmt:message key="order.header" bundle="${rb}"/></title>
</head>
<body>
<c:import url="MainHeader.jsp"/>
<div class="row">
    <div class="col-md-5">
        <ul class="list-group">
            <c:if test="${sessionScope.user_role=='DOCTOR' or sessionScope.user_role=='PHARMACIST'}">
                <small>
                    <label for="result_id">id</label>
                    <li class="list-group-item list-group-item-light" id="result_id"><c:out
                            value="${order.id}"/></li>
                </small>
            </c:if>
            <label for="result_price"><fmt:message key="order.total" bundle="${rb}"/></label>
            <li class="list-group-item list-group-item-primary" id="result_price"><c:out
                    value="${order.price}"/></li>
            <label for="result_patient"><fmt:message key="order.patient" bundle="${rb}"/></label>
            <a href="/pharmacy?command=go_to_patient_page&patient_id=${order.patientId}">
                <li class="list-group-item list-group-item-primary" id="result_patient"><c:out
                        value="${order.patientId}"/></li>
            </a>
            <label for="order_status"><fmt:message key="order.status" bundle="${rb}"/></label>
            <li class="list-group-item" id="order_status"><c:out value="${order.status}"/></li>
        </ul>
        <div>
            <p class="text=info"><fmt:message key="order.medicine" bundle="${rb}"/></p>
            <c:forEach items="${order.orderedMedicines}" var="medicine">
                <ul class="list-group">
                    <label for="medicine_id">id</label>
                    <li class="list-group-item list-group-item-info" id="medicine_id"><c:out
                            value="${medicine.key}"/></li>
                    <label for="medicine_amount"><fmt:message key="order.amount" bundle="${rb}"/></label>
                    <li class="list-group-item" id="medicine_amount"><c:out value="${medicine.value}"/></li>
                    <li class="list-group-item">...</li>
                </ul>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>

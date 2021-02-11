<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 11.01.2021
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
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


    <title><fmt:message key="payment.header" bundle="${rb}"/></title>
</head>
<body>
<c:import url="MainHeader.jsp"/>
<div>
    <ul class="list-group">
        <small>
            <label for="result_id">id</label>
            <a href="/pharmacy?command=go_to_payment_page&payment_id=${payment.id}">
                <li class="list-group-item list-group-item-light" id="result_id"><c:out
                        value="${payment.id}"/></li>
            </a>
        </small>
        <label for="result_iban"><fmt:message key="payment.pay.IBAN" bundle="${rb}"/></label>
        <li class="list-group-item list-group-item-primary" id="result_iban"><c:out
                value="${payment.iban}"/></li>
        <label for="result_sum"><fmt:message key="payment.sum" bundle="${rb}"/></label>
        <li class="list-group-item list-group-item-primary" id="result_sum"><c:out
                value="${payment.sum}"/></li>
        <label for="result_date"><fmt:message key="payment.date" bundle="${rb}"/></label>
        <li class="list-group-item" id="result_date"><c:out
                value="${f:formatLocalDateTime(appointment.dateTime, 'yyyy-MM-dd HH:mm')}"/></li>
    </ul>
</div>
</body>
</html>

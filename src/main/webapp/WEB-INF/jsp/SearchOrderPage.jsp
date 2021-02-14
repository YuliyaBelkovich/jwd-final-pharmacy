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


    <title><fmt:message key="search.order" bundle="${rb}"/> </title>
</head>
<body>
<c:import url="MainHeader.jsp"/>
<div class="row">
    <div class="col-md-5">
<c:if test="${sessionScope.user_role=='DOCTOR' or sessionScope.user_role=='PHARMACIST'}">
        <form action="${pageContext.request.contextPath}/pharmacy" method="GET">
            <input type="hidden" name="command" value="search_order">
            <div class="form-group">
                <label for="id">id</label>
                <input type="number" name="order_id" min="0" step="1" class="form-control" id="id"
                       placeholder="<fmt:message key="order.id.placeholder" bundle="${rb}"/>">
            </div>
            <div class="form-group">
                <label for="sum"><fmt:message key="order.total" bundle="${rb}"/></label>
                <input type="number" name="order_price" min="0" step="0.01" class="form-control" id="sum"
                       placeholder="<fmt:message key="order.price.placeholder" bundle="${rb}"/>">
            </div>
            <div class="form-group">
                <label for="patient"><fmt:message key="order.patient" bundle="${rb}"/></label>
                <input type="number" name="order_patient"min="0"step="1" class="form-control" id="patient"
                       placeholder="<fmt:message key="order.patient.placeholder" bundle="${rb}"/>">
            </div>
            <button type="submit" class="btn btn-success"><fmt:message key="search.find" bundle="${rb}"/></button>
        </form>
</c:if>
    </div>
    <div class="col-md-5">
        <p class="text-danger">${requestScope.Error}</p>
        <p class="text-info">${requestScope.Message}</p>
        <p class="text-primary">${param.message}</p>
        <p class="text-danger">${param.error}</p>

        <%--            DISPLAY SEARCH RESULT--%>

        <c:forEach items="${Order}" var="order">

            <div>
                <ul class="list-group">
                    <c:if test="${sessionScope.user_role=='DOCTOR' or sessionScope.user_role=='PHARMACIST'}">
                    <small>
                        <label for="result_id">id</label>
                        <a href="/pharmacy?command=go_to_order_page&order_id=${order.id}">
                            <li class="list-group-item list-group-item-light" id="result_id"><c:out
                                    value="${order.id}"/></li>
                        </a>
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
                    <label for="order_status"<fmt:message key="order.status" bundle="${rb}"/></label>
                    <li class="list-group-item" id="order_status"><c:out value="${order.status}"/></li>
                    <li class="list-group-item">...</li>
                </ul>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>

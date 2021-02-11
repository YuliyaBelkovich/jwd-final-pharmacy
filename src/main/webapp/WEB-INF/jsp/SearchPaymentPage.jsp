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


    <title><fmt:message key="search.payment" bundle="${rb}"/></title>
</head>
<body>
<c:import url="MainHeader.jsp"/>
<div class="row">
    <div class="col-md-5">
<c:if test="${sessionScope.user_role=='DOCTOR' or sessionScope.user_role=='PHARMACIST'}">
        <form action="${pageContext.request.contextPath}/pharmacy" method="GET">
            <input type="hidden" name="command" value="search_payment">
            <div class="form-group">
                <label for="id">id</label>
                <input type="number" name="payment_id" min="0" step="1" class="form-control" id="id"
                       placeholder="<fmt:message key="payment.id.placeholder" bundle="${rb}"/>">
            </div>
            <div class="form-group">
                <label for="sum"><fmt:message key="payment.sum" bundle="${rb}"/></label>
                <input type="number" name="payment_sum" min="0" class="form-control" id="sum"
                       placeholder="<fmt:message key="payment.sum.placeholder" bundle="${rb}"/>">
            </div>
            <div class="form-group">
                <label for="iban"><fmt:message key="payment.pay.IBAN" bundle="${rb}"/></label>
                <input type="number" name="payment_iban" class="form-control" id="iban"
                       placeholder="<fmt:message key="payment.iban.placeholder" bundle="${rb}"/>">
            </div>
            <div class="form-group">
                <label for="date"><fmt:message key="payment.date" bundle="${rb}"/></label>
                <input type="date" name="payment_date" class="form-control" id="date"
                       placeholder="<fmt:message key="payment.date.placeholder" bundle="${rb}"/>">
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
        <c:forEach items="${Payment}" var="payment">

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
                            value="${payment.IBAN}"/></li>
                    <label for="result_sum"><fmt:message key="payment.sum" bundle="${rb}"/></label>
                    <li class="list-group-item list-group-item-primary" id="result_sum"><c:out
                            value="${payment.sum}"/></li>
                    <label for="result_date"><fmt:message key="payment.date" bundle="${rb}"/></label>
                    <li class="list-group-item" id="result_date"><c:out
                            value="${f:formatLocalDateTime(payment.dateTime, 'yyyy-MM-dd HH:mm')}"/></li>
                    <li class="list-group-item">...</li>

                </ul>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>

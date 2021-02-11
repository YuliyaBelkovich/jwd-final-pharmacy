<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 14.01.2021
  Time: 9:07
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


    <title><fmt:message key="basket.header" bundle="${rb}"/> </title>
</head>
<body>
<c:import url="MainHeader.jsp"/>

<%--MEDICINES--%>
<h2><p class="text-info"><fmt:message key="basket.header" bundle="${rb}"/> </p></h2>
<c:if test="${sessionScope.basket != null && sessionScope.basketPrice != 0.0}">
<div class="row">
    <div class="col-md-4"></div>
    <c:forEach items="${sessionScope.basket.basketMedicine}" var="medicine">
    <div class="col-md-8">

        <div class="col-md-6">
            <div>
                <ul class="list-group">
                    <li class="list-group-item list-group-item-primary"><c:out value="${medicine.key.name}"/></li>
                    <li class="list-group-item "><c:out value="${medicine.key.dose}"/></li>
                    <li class="list-group-item "><c:out value="${medicine.key.recipeRequirement}"/></li>
                    <li class="list-group-item list-group-item-info"><c:out value="${medicine.key.price}"/></li>
                    <li class="list-group-item list-group-item-success"><c:out value="${medicine.value}"/></li>
                </ul>
            </div>
        </div>
        <div class="col-md-2">
            <p class="text-primary">${param.message}</p>
            <p class="text-danger">${param.error}</p>
            <p>
                <a href="/pharmacy?command=remove_from_basket&medicine_id=${medicine.key.id}&url=/pharmacy?command=go_to_basket_page">
                    <button type="button" class="btn btn-danger"><fmt:message key="basket.button.remove" bundle="${rb}"/> </button>
                </a></p>
        </div>

    </div>
    </c:forEach>

    <p class="text-lg-left"><fmt:message key="basket.total" bundle="${rb}"/> <c:out value="${sessionScope.basketPrice}"/> $</p>
    <a href="/pharmacy?command=add_order&order_patient=${sessionScope.user_id}">
        <button type="button" class="btn btn-success inline"><fmt:message key="basket.button.order" bundle="${rb}"/> </button>
    </a>
    </c:if>
    <c:if test="${sessionScope.basket== null or sessionScope.basketPrice == 0.0}">
    <h3><p class="text-info"><fmt:message key="basket.empty" bundle="${rb}"/> </p></h3>
    </c:if>
</body>
</html>

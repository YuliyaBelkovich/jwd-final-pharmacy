<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 05.01.2021
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
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


    <title><fmt:message key="user.page.header" bundle="${rb}"/></title>
</head>
<body>
<c:import url="MainHeader.jsp"/>
<p class="text-primary">${param.message}</p>
<p class="text-danger">${param.error}</p>
<nav>
    <div class="nav nav-tabs" id="nav-tab" role="tablist">
        <a class="nav-link active" id="profile-tab" role="tab" href="#profile" data-bs-toggle="tab"
           aria-controls="profile" aria-selected="true">
            <fmt:message key="user.page.profile" bundle="${rb}"/>
        </a>
        <c:if test="${sessionScope.user_role=='DOCTOR'}">
            <a class="nav-link" id="timeslot-tab" role="tab" href="#timeslot" data-bs-toggle="tab"
               aria-controls="timeslot" aria-selected="false"><fmt:message key="user.page.timeslot.new" bundle="${rb}"/></a>
            </li>
        </c:if>
        <c:if test="${sessionScope.user_role=='PHARMACIST'}">
            <a class="nav-link" id="medicine-tab" role="tab" href="#medicine" data-bs-toggle="tab"
               aria-controls="medicine" aria-selected="false"><fmt:message key="user.page.medicine.new" bundle="${rb}"/></a>
        </c:if>
        <c:if test="${sessionScope.user_role=='PATIENT'}">
            <a class="nav-link" id="bank-tab" role="tab" href="#bank" data-bs-toggle="tab"
               aria-controls="bank" aria-selected="false"><fmt:message key="user.page.bank.new" bundle="${rb}"/></a>
        </c:if>
    </div>
</nav>
<div class="tab-content" id="nav-tabContent">
    <div class="tab-pane fade show active" id="profile" role="tabpanel" aria-labelledby="profile-tab">
        <div class="row">
            <div class="col-md-3">
                <nav class="nav flex-column">
                    <c:if test="${sessionScope.user_role=='PATIENT'}">
                        <a class="nav-link"
                           href="/pharmacy?command=search_order&order_patient=${sessionScope.user_id}"><fmt:message key="user.page.order" bundle="${rb}"/></a>
                    </c:if>
                    <c:if test="${sessionScope.user_role=='PATIENT'}">
                        <a class="nav-link"
                           href="/pharmacy?command=search_appointment&appointment_patient=${sessionScope.user_id}"><fmt:message key="user.page.appointment" bundle="${rb}"/></a>
                    </c:if>
                    <c:if test="${sessionScope.user_role=='DOCTOR'}">

                        <a class="nav-link"
                           href="/pharmacy?command=search_appointment&appointment_doctor=${sessionScope.user_id}"><fmt:message key="user.page.appointment" bundle="${rb}"/></a>

                    </c:if>
                    <c:if test="${sessionScope.user_role=='PATIENT'}">

                        <a class="nav-link"
                           href="/pharmacy?command=search_recipe&recipe_patient=${sessionScope.user_id}"><fmt:message key="user.page.recipe.patient" bundle="${rb}"/></a>

                    </c:if>
                    <c:if test="${sessionScope.user_role=='DOCTOR'}">

                        <a class="nav-link"
                           href="/pharmacy?command=search_recipe&recipe_doctor=${sessionScope.user_id}"><fmt:message key="user.page.recipe.doctor" bundle="${rb}"/></a>

                    </c:if>
                    <c:if test="${sessionScope.user_role=='DOCTOR'}">

                        <a class="nav-link"
                           href="/pharmacy?command=search_window&window_doctor=${sessionScope.user_id}"><fmt:message key="user.page.timeslot" bundle="${rb}"/></a>

                    </c:if>
                    <c:if test="${sessionScope.user_role=='PATIENT'}">

                        <a class="nav-link"
                           href="/pharmacy?command=search_request&request_patient=${sessionScope.user_id}"><fmt:message key="user.page.request" bundle="${rb}"/></a>

                    </c:if>
                    <c:if test="${sessionScope.user_role=='DOCTOR'}">
                        <a class="nav-link"
                           href="/pharmacy?command=search_request&request_doctor=${sessionScope.user_id}"><fmt:message key="user.page.request" bundle="${rb}"/></a>
                    </c:if>
                    <c:if test="${sessionScope.user_role=='PATIENT'}">
                        <a class="nav-link"
                           href="/pharmacy?command=search_bank_account&bank_account_patient=${sessionScope.user_id}"><fmt:message key="user.page.bank" bundle="${rb}"/></a>
                    </c:if>
                </nav>
            </div>
            <div class="col-md-4">
                <%--        USER INFO--%>
                <div>
                    <ul class="list-group">
                        <label for="id">id</label>
                        <li class="list-group-item  list-group-item-light" id="id">${sessionScope.user_id}</li>

                        <label for="name"><fmt:message key="user.name" bundle="${rb}"/></label>
                        <li class="list-group-item " id="name">${sessionScope.user_name}</li>

                        <label for="email"><fmt:message key="user.email" bundle="${rb}"/></label>
                        <li class="list-group-item " id="email">${sessionScope.user_email}</li>
                        <label for="role"><fmt:message key="user.role" bundle="${rb}"/></label>
                        <li class="list-group-item " id="role">${sessionScope.user_role}</li>
                        <label for="status"><fmt:message key="user.status" bundle="${rb}"/></label>
                        <li class="list-group-item " id="status">${sessionScope.user_status}</li>
                    </ul>
                </div>
            </div>
            <div class="col-md-3">
                <p class="text-danger">${requestScope.Error}</p>
                <div>
                    <c:forEach items="${Request}" var="request">
                        <ul class="list-group">
                            <label for="request_id">id</label>
                            <li class="list-group-item" id="request_id"><c:out value="${request.id}"/></li>
                            <label for="request_recipe"><fmt:message key="request.recipe" bundle="${rb}"/></label>
                            <li class="list-group-item" id="request_recipe"><c:out value="${request.recipeId}"/></li>
                            <label for="request_doctor"><fmt:message key="request.doctor" bundle="${rb}"/></label>
                            <li class="list-group-item" id="request_doctor"><c:out value="${request.doctorId}"/></li>
                        </ul>
                    </c:forEach>
                </div>
                <%--                TIMESLOT--%>
                <div>
                    <c:forEach items="${Window}" var="window">
                        <ul class="list-group">
                            <label for="window_id">id</label>
                            <li class="list-group-item list-group-item-light" id="window_id"><c:out
                                    value="${window.id}"/></li>
                            <label for="window_doctor"><fmt:message key="request.doctor" bundle="${rb}"/></label>
                            <li class="list-group-item" id="window_doctor"><c:out value="${window.doctorId}"/></li>
                            <label for="window_date"><fmt:message key="recipe.date" bundle="${rb}"/></label>
                            <li class="list-group-item" id="window_date"><c:out value="${f:formatLocalDateTime(window.dateTime, 'yyyy-MM-dd HH:mm')}"/></li>
                            <li>...</li>
                        </ul>
                    </c:forEach>
                </div>

                <%--                BANK ACCOUNT--%>
                <div>
                    <c:forEach items="${BankAccount}" var="bank">
                        <ul class="list-group">
                            <label for="bank_id">id</label>
                            <li class="list-group-item list-group-item-light" id="bank_id">
                                <c:out value="${bank.id}"/>
                            </li>
                            <label for="bank_patient"><fmt:message key="recipe.patient" bundle="${rb}"/></label>
                            <li class="list-group-item list-group-item-light" id="bank_patient">
                                <c:out value="${bank.patientId}"/>
                            </li>
                            <label for="bank_iban"><fmt:message key="payment.pay.IBAN" bundle="${rb}"/></label>
                            <li class="list-group-item list-group-item-light" id="bank_iban">
                                <c:out value="${bank.IBAN}"/>
                            </li>
                        </ul>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>

    <div class="tab-pane fade" id="timeslot" role="tabpanel" aria-labelledby="timeslot-tab">
        <p class="text-primary">${param.message}</p>
        <p class="text-danger">${param.error}</p>
        <form action="${pageContext.request.contextPath}/pharmacy" method="POST">
            <input type="hidden" name="command" value="add_window">
            <input type="hidden" name="window_doctor" value="${sessionScope.user_id}">
            <div class="form-group">
                <label for="date"><fmt:message key="payment.date" bundle="${rb}"/></label>
                <input type="datetime-local" name="window_date" class="form-control" id="date"
                       placeholder="<fmt:message key="payment.date.placeholder" bundle="${rb}"/>">
            </div>
            <button type="submit" class="btn btn-success"><fmt:message key="payment.button.submit" bundle="${rb}"/></button>
        </form>
    </div>
    <div class="tab-pane fade" id="bank" role="tabpanel" aria-labelledby="bank-tab">
        <p class="text-primary">${param.message}</p>
        <p class="text-danger">${param.error}</p>
        <form action="${pageContext.request.contextPath}/pharmacy" method="POST">
            <input type="hidden" name="command" value="add_bank_account">
            <input type="hidden" name="bank_account_patient" value="${sessionScope.user_id}">
            <div class="form-group">
                <label for="iban"><fmt:message key="payment.pay.IBAN" bundle="${rb}"/></label>
                <input type="text" name="bank_account_iban" class="form-control" id="iban">
            </div>
            <div class="form-group">
                <label for="card_holder"><fmt:message key="payment.pay.holder" bundle="${rb}"/></label>
                <input type="text" name="bank_account_holder" class="form-control" id="card_holder">
            </div>
            <div class="form-group">
                <label for="expiry_date"><fmt:message key="payment.pay.expiry" bundle="${rb}"/></label>
                <input type="text" name="bank_account_date" class="form-control" id="expiry_date">
            </div>
            <div class="form-group">
                <label for="cvv"><fmt:message key="payment.pay.cvv" bundle="${rb}"/></label>
                <input type="number" name="bank_account_cvv" class="form-control" id="cvv">
            </div>
            <button type="submit" class="btn btn-success"><fmt:message key="payment.button.submit" bundle="${rb}"/></button>
        </form>
    </div>
    <div class="tab-pane fade" id="medicine" role="tabpanel" aria-labelledby="medicine-tab">
        <p class="text-primary">${param.message}</p>
        <p class="text-danger">${param.error}</p>
        <form action="${pageContext.request.contextPath}/pharmacy" method="POST">
            <input type="hidden" name="command" value="add_medicine">
            <div class="form-group">
                <label for="name_medicine"><fmt:message key="medicine.name" bundle="${rb}"/></label>
                <input type="text" name="medicine_name" class="form-control" id="name_medicine"
                       placeholder="<fmt:message key="medicine.name.placeholder" bundle="${rb}"/>">
            </div>
            <div class="form-check form-check-inline">
                <input type="checkbox" name="recipe_requirement" class="form-check-input" id="recipeCheck1">
                <label class="form-check-label" for="recipeCheck1"><fmt:message key="medicine.withRecipe" bundle="${rb}"/></label>
            </div>
            <div class="form-group">
                <label for="info"><fmt:message key="medicine.info" bundle="${rb}"/></label>
                <input type="text" name="medicine_info" class="form-control" id="info"
                       placeholder="<fmt:message key="medicine.info.placeholder" bundle="${rb}"/>">
            </div>
            <div class="form-group">
                <label for="dose"><fmt:message key="medicine.dose" bundle="${rb}"/></label>
                <input type="number" step="0.01" name="medicine_dose" class="form-control" id="dose"
                       placeholder="<fmt:message key="medicine.dose.placeholder" bundle="${rb}"/>">
            </div>
            <div class="form-group">
                <label for="price"><fmt:message key="medicine.price" bundle="${rb}"/></label>
                <input type="number" step="0.01" min="0" name="medicine_price" class="form-control" id="price"
                       placeholder="<fmt:message key="medicine.price.placeholder" bundle="${rb}"/>">
            </div>
            <button type="submit" class="btn btn-success"><fmt:message key="payment.button.submit" bundle="${rb}"/></button>
        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>

</body>
</html>

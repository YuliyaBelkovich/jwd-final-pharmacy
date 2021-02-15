<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 05.01.2021
  Time: 15:53
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


    <title><fmt:message key="search.medicine" bundle="${rb}"/></title>
</head>
<body>
<c:import url="MainHeader.jsp"/>

<%--FIRST COLUMN - SEARCH MEDICINE FORM--%>

<div class="row">
    <div class="col-md-3">
        <form action="${pageContext.request.contextPath}/pharmacy" method="GET">
            <input type="hidden" name="command" value="search_medicine">
            <c:if test="${sessionScope.user_role=='DOCTOR' or sessionScope.user_role=='PHARMACIST' }">
                <div class="form-group">
                    <label for="id">id</label>
                    <input type="number" name="medicine_id" min="1" step="1"  class="form-control" id="id"
                           placeholder="Enter medicine id">
                </div>
            </c:if>
            <div class="form-group">
                <label for="name"><fmt:message key="medicine.name" bundle="${rb}"/></label>
                <input type="text" name="medicine_name" class="form-control" id="name"
                       placeholder="<fmt:message key="medicine.name.placeholder" bundle="${rb}"/>">
            </div>
            <div class="form-check form-check-inline">
                <input type="checkbox" name="recipe_requirement" class="form-check-input" id="recipeCheck1">
                <label class="form-check-label" for="recipeCheck1"><fmt:message key="medicine.withRecipe" bundle="${rb}"/></label>
            </div>
            <div class="form-check form-check-inline">
                <input type="checkbox" name="non_recipe_requirement" class="form-check-input" id="recipeCheck2">
                <label class="form-check-label" for="recipeCheck2"><fmt:message key="medicine.withoutRecipe" bundle="${rb}"/></label>
            </div>
            <div class="form-group">
                <label for="dose"><fmt:message key="medicine.dose" bundle="${rb}"/></label>
                <input type="number" step="0.01" min="0" name="medicine_dose" class="form-control" id="dose"
                       placeholder="<fmt:message key="medicine.dose.placeholder" bundle="${rb}"/>">
            </div>
            <div class="form-group">
                <label for="price"><fmt:message key="medicine.price" bundle="${rb}"/></label>
                <input type="number" step="0.01" min="0" name="medicine_price" class="form-control" id="price"
                       placeholder="<fmt:message key="medicine.price.placeholder" bundle="${rb}"/>">
            </div>
            <p>   </p>
            <button type="submit" class="btn btn-success"><fmt:message key="search.find" bundle="${rb}"/></button>
        </form>
    </div>

    <div class="col-md-9">
        <p class="text-danger">${requestScope.Error}</p>
        <p class="text-info">${requestScope.Message}</p>
        <p class="text-primary">${param.message}</p>
        <p class="text-danger">${param.error}</p>
        <div class="row">
            <c:forEach items="${Medicine}" var="medicine">

                <%--            DISPLAY SEARCH RESULT--%>

                <div class="col-md-7">
                    <div>
                        <ul class="list-group">
                            <c:if test="${sessionScope.user_role=='DOCTOR' or sessionScope.user_role=='PHARMACIST' }">
                                 <small>
                                    <label for="result_id">id</label>
                                    <li class="list-group-item list-group-item-light" id="result_id"><c:out
                                            value="${medicine.id}"/></li>
                                </small>
                            </c:if>
                            <label for="result_name"><fmt:message key="medicine.name" bundle="${rb}"/> </label>
                            <a href="/pharmacy?command=go_to_medicine_page&medicine_id=${medicine.id}">
                                <li class="list-group-item list-group-item-primary" id="result_name"><c:out
                                        value="${medicine.name}"/></li>
                            </a>
                            <label for="result_dose"><fmt:message key="medicine.dose" bundle="${rb}"/></label>
                            <li class="list-group-item" id="result_dose"><c:out value="${medicine.dose}"/></li>
                            <label for="result_recipe"><fmt:message key="medicine.isRecipe" bundle="${rb}"/></label>
                            <li class="list-group-item" id="result_recipe"><c:out
                                    value="${medicine.recipeRequirement}"/></li>
                            <label for="result_info"><fmt:message key="medicine.info" bundle="${rb}"/></label>
                            <li class="list-group-item list-group-item-info" id="result_info"><c:out
                                    value="${medicine.info}"/></li>
                            <label for="result_price"><fmt:message key="medicine.price" bundle="${rb}"/></label>
                            <li class="list-group-item list-group-item-success" id="result_price"><c:out
                                    value="${medicine.price}"/> $
                            </li>
                            <li class="list-group-item">...</li>
                        </ul>
                    </div>

                </div>

                <%--                DISPLAY ACTION BUTTONS--%>

                <div class="col-md-2">
                    <p><c:if
                            test="${sessionScope.user_role == 'PHARMACIST'}">
                        <a href="/pharmacy?command=delete_medicine&medicine_id=${medicine.id}">
                            <button type="button" class="btn btn-danger"><fmt:message key="medicine.button.delete" bundle="${rb}"/></button>
                            </c:if></a></p>
                    <p>
                        <c:if
                                test="${sessionScope.user_role == 'PHARMACIST'}">
                            <button type="button" onclick="openUpdate(${medicine.id})" class="btn btn-warning"><fmt:message key="medicine.button.update" bundle="${rb}"/></button>
                        </c:if></p>
                    <p>
                        <c:if
                                test="${sessionScope.user_role=='PATIENT' or sessionScope.user_role=='GUEST'}">
                            <button type="button" onclick="openBasket(${medicine.id})" class="btn btn-success"><fmt:message key="medicine.basket" bundle="${rb}"/></button>
                            </c:if>
                    <p>
                        <c:if
                                test="${sessionScope.user_role == 'DOCTOR'}">
                                <button type="button" onclick="openAdd(${medicine.id})" class="btn btn-warning"><fmt:message key="medicine.prescribe" bundle="${rb}"/></button>
                        </c:if>
                    </p>

                        <%--                    UPDATE MEDICINE FORM--%>

                </div>
                <div class="col-md-3">
                    <div class="d-none" id="basketForm${medicine.id}">
                        <form action="${pageContext.request.contextPath}/pharmacy" method="POST">
                            <input type="hidden" name="command" value="add_to_basket">
                            <input type="hidden" name="medicine_id" value="${medicine.id}">
                            <div class="form-group">
                                <label for="amount"><fmt:message key="order.amount" bundle="${rb}"/></label>
                                <input type="number" min="1" step="1" class="form-control" name="amount" id="amount">
                            </div>
                            <p>  </p>
                            <button type="submit" class="btn btn-success"><fmt:message key="payment.button.submit" bundle="${rb}"/></button>
                        </form>
                        <small>
                            <button onclick="closeBasket(${medicine.id})" class="btn btn-danger"><fmt:message key="medicine.button.close" bundle="${rb}"/></button>
                        </small>
                    </div>
                    <div class="d-none" id="updateForm${medicine.id}">
                        <form action="${pageContext.request.contextPath}/pharmacy" method="POST">
                            <input type="hidden" name="command" value="update_medicine">
                            <input type="hidden" name="medicine_id" value="${medicine.id}">
                            <div class="form-group">
                                <label for="nameUpdate"><fmt:message key="medicine.name" bundle="${rb}"/></label>
                                <input type="text" name="medicine_name" class="form-control" id="nameUpdate"
                                       placeholder="<fmt:message key="medicine.name.placeholder" bundle="${rb}"/>">
                            </div>
                            <div class="form-check">
                                <input type="checkbox" name="recipe_requirement" class="form-check-input"
                                       id="recipeCheck1Update">
                                <label class="form-check-label" for="recipeCheck1Update"><fmt:message key="medicine.withRecipe" bundle="${rb}"/></label>
                            </div>
                            <div class="form-group">
                                <label for="doseUpdate"><fmt:message key="medicine.dose" bundle="${rb}"/></label>
                                <input type="number" step="0.01" min="0" name="medicine_dose" class="form-control"
                                       id="doseUpdate"
                                       placeholder="<fmt:message key="medicine.dose.placeholder" bundle="${rb}"/>">
                            </div>
                            <div class="form-group">
                                <label for="infoUpdate"><fmt:message key="medicine.info" bundle="${rb}"/></label>
                                <input type="text" name="medicine_info" class="form-control" id="infoUpdate"
                                       placeholder="<fmt:message key="medicine.info.placeholder" bundle="${rb}"/>">
                            </div>
                            <div class="form-group">
                                <label for="priceUpdate"><fmt:message key="medicine.price" bundle="${rb}"/></label>
                                <input type="number" step="0.01" min="0" name="medicine_price" class="form-control"
                                       id="priceUpdate"
                                       placeholder="<fmt:message key="medicine.price.placeholder" bundle="${rb}"/>">
                            </div>
                            <p>  </p>
                            <button type="submit" class="btn btn-success"><fmt:message key="medicine.button.update" bundle="${rb}"/></button>
                        </form>
                        <small>
                            <button onclick="closeUpdate(${medicine.id})" class="btn btn-danger"><fmt:message key="medicine.button.close" bundle="${rb}"/></button>
                        </small>
                    </div>
                    <div class="d-none" id="addForm${medicine.id}">
                        <form action="${pageContext.request.contextPath}/pharmacy" method="POST">
                            <input type="hidden" name="command" value="add_recipe">
                            <input type="hidden" name="recipe_medicine" value="${medicine.id}">
                            <input type="hidden" name="recipe_doctor" value="${sessionScope.user_id}">
                            <input type="hidden" name="recipe_duration" value="30">
                            <div class="form-group">
                                <label for="recipe_patient"><fmt:message key="appointment.patient" bundle="${rb}"/></label>
                                <input type="number" name="recipe_patient" min="1" step="1" class="form-control" id="recipe_patient"
                                       placeholder="<fmt:message key="appointment.patient.placeholder" bundle="${rb}"/>">
                            </div>
                            <div class="form-group">
                                <label for="recipe_dose"><fmt:message key="medicine.dose" bundle="${rb}"/></label>
                                <input type="number" name="recipe_dose" min="0" step="0.01" class="form-control" id="recipe_dose"
                                       placeholder="<fmt:message key="medicine.dose.placeholder" bundle="${rb}"/>">
                            </div>
                            <button type="submit" class="btn btn-success"><fmt:message key="payment.button.submit" bundle="${rb}"/></button>
                        </form>
                        <button onclick="closeAdd(${medicine.id})" class="btn btn-danger"><fmt:message key="medicine.button.close" bundle="${rb}"/></button>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

</div>
<script>
    function openUpdate(id) {
        document.getElementById("updateForm"+id).classList.replace("d-none", "d-block");
    }

    function closeUpdate(id) {
        document.getElementById("updateForm"+id).classList.replace("d-block", "d-none");
    }

    function openAdd(id) {
        document.getElementById("addForm"+id).classList.replace("d-none", "d-block");
    }

    function closeAdd(id) {
        document.getElementById("addForm"+id).classList.replace("d-block", "d-none");
    }
    function openBasket(id) {
        document.getElementById("basketForm"+id).classList.replace("d-none", "d-block");
    }

    function closeBasket(id) {
        document.getElementById("basketForm"+id).classList.replace("d-block", "d-none");
    }
</script>
</body>
</html>

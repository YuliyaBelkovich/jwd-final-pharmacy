<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 16.01.2021
  Time: 15:22
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


    <title><fmt:message key="medicine.header" bundle="${rb}"/> </title>
</head>
<body>
<c:import url="MainHeader.jsp"/>
<div class="col-md-5">
    <div>
        <ul class="list-group">
            <c:if test="${sessionScope.user_role=='DOCTOR' or sessionScope.user_role=='PHARMACIST' }">
                <small>
                    <label for="result_id">id</label>
                    <li class="list-group-item list-group-item-light" id="result_id"><c:out
                            value="${medicine.id}"/></li>
                </small>
            </c:if>
            <label for="result_name"><fmt:message key="medicine.name" bundle="${rb}"/></label>
            <a href="/pharmacy?command=go_to_medicine_page&medicine_id=${medicine.id}">
                <li class="list-group-item list-group-item-primary" id="result_name"><c:out
                        value="${medicine.name}"/></li>
            </a>
            <label for="result_dose"><fmt:message key="medicine.dose" bundle="${rb}"/></label>
            <li class="list-group-item" id="result_dose"><c:out value="${medicine.dose}"/></li>
            <label for="result_recipe"><fmt:message key="medicine.isRecipe" bundle="${rb}"/></label>
            <li class="list-group-item" id="result_recipe"><c:out value="${medicine.recipeRequirement}"/></li>
            <label for="result_info"><fmt:message key="medicine.info" bundle="${rb}"/></label>
            <li class="list-group-item list-group-item-info" id="result_info"><c:out value="${medicine.info}"/></li>
            <label for="result_price"><fmt:message key="medicine.price" bundle="${rb}"/></label>
            <li class="list-group-item list-group-item-success" id="result_price"><c:out value="${medicine.price}"/> $
            </li>
        </ul>
    </div>

</div>

<%--                DISPLAY ACTION BUTTONS--%>
<div class="row">
    <p class="text-primary">${param.message}</p>
    <p class="text-danger">${param.error}</p>
    <div class="col-md-4">
        <p><c:if
                test="${sessionScope.user_role == 'PHARMACIST'}">
            <a href="/pharmacy?command=delete_medicine&medicine_id=${medicine.id}">
                <button type="button" class="btn btn-danger"><fmt:message key="medicine.button.delete" bundle="${rb}"/></button>
                </c:if></a></p>
        <p>
            <c:if
                    test="${sessionScope.user_role == 'PHARMACIST'}">
                <button type="button" onclick="openUpdate()" class="btn btn-warning"><fmt:message key="medicine.button.update" bundle="${rb}"/></button>
            </c:if></p>

        <%--                    UPDATE MEDICINE FORM--%>
        <small>
            <div class="d-none" id="updateForm">
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
                        <label class="form-check-label" for="recipeCheck1Update"><fmt:message key="medicine.recipe" bundle="${rb}"/></label>
                    </div>
                    <div class="form-group">
                        <label for="doseUpdate"><fmt:message key="medicine.dose" bundle="${rb}"/></label>
                        <input type="number" step="0.01" name="medicine_dose" class="form-control" id="doseUpdate"
                               placeholder="<fmt:message key="medicine.dose.placeholder" bundle="${rb}"/>">
                    </div>
                    <div class="form-group">
                        <label for="infoUpdate"><fmt:message key="medicine.info" bundle="${rb}"/></label>
                        <input type="text" name="medicine_info" class="form-control" id="infoUpdate"
                               placeholder="<fmt:message key="medicine.info.placeholder" bundle="${rb}"/>">
                    </div>
                    <div class="form-group">
                        <label for="priceUpdate"><fmt:message key="medicine.price" bundle="${rb}"/></label>
                        <input type="number" step="0.01" name="medicine_price" class="form-control" id="priceUpdate"
                               placeholder="<fmt:message key="medicine.price.placeholder" bundle="${rb}"/>">
                    </div>
                    <button type="submit" class="btn btn-success"><fmt:message key="medicine.button.update" bundle="${rb}"/></button>
                </form>
                <small>
                    <button onclick="closeUpdate()" class="btn btn-danger"><fmt:message key="medicine.button.close" bundle="${rb}"/></button>
                </small>
            </div>
        </small>
    </div>
</div>
<script>
    function openUpdate() {
        document.getElementById("updateForm").classList.replace("d-none","d-block");
    }

    function closeUpdate() {
        document.getElementById("updateForm").classList.replace("d-block","d-none");
    }
</script>
</body>
</html>

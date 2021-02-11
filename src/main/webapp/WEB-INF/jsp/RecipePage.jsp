<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 11.01.2021
  Time: 23:44
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


    <title><fmt:message key="recipe.header" bundle="${rb}"/></title>
</head>
<body>
<c:import url="MainHeader.jsp"/>
<div class="row">
    <div class="col-md-5">
        <div>
            <ul class="list-group">
                <small>
                    <label for="result_id">id</label>
                    <a href="/pharmacy?command=go_to_recipe_page&recipe_id=${recipe.id}">
                        <li class="list-group-item list-group-item-light" id="result_id"><c:out
                                value="${recipe.id}"/></li>
                        </a>
                </small>
                <label for="result_patient"><fmt:message key="recipe.patient" bundle="${rb}"/></label>
                <a href="/pharmacy?command=go_to_patient_page&patient_id=${recipe.patientId}">
                    <li class="list-group-item list-group-item-primary" id="result_patient"><c:out
                            value="${recipe.patientId}"/></li>
                </a>
                <label for="result_medicine"><fmt:message key="recipe.medicine" bundle="${rb}"/></label>
                <a href="/pharmacy?command=go_to_medicine_page&medicine_id=${recipe.medicineId}">
                    <li class="list-group-item list-group-item-primary" id="result_medicine"><c:out
                            value="${recipe.medicineId}"/></li>
                </a>
                <label for="result_doctor"><fmt:message key="recipe.doctor" bundle="${rb}"/></label>
                <a href="/pharmacy?command=go_to_doctor_page&doctor_id=${recipe.doctorId}">
                    <li class="list-group-item" id="result_doctor"><c:out
                            value="${recipe.doctorId}"/></li>
                </a>
                <label for="result_dose"><fmt:message key="recipe.dose" bundle="${rb}"/></label>
                <li class="list-group-item" id="result_dose"><c:out
                        value="${recipe.dose}"/></li>
                <label for="result_date"><fmt:message key="recipe.date" bundle="${rb}"/></label>
                <li class="list-group-item list-group-item-primary" id="result_date"><c:out
                        value="${recipe.date}"/></li>
                <label for="result_duration"><fmt:message key="recipe.duration" bundle="${rb}"/></label>
                <li class="list-group-item" id="result_duration"><c:out value="${recipe.duration}"/></li>
            </ul>
        </div>
    </div>

    <%--                DISPLAY ACTION BUTTONS--%>

    <div class="col-md-4">
        <c:if
                test="${sessionScope.user_role == 'DOCTOR' && sessionScope.user_status=='ACTIVE'}">
            <a href="/pharmacy?command=add_request&request_recipe=${recipe.id}">
                <button type="button" class="btn btn-warning"><fmt:message key="recipe.prolong" bundle="${rb}"/></button>
            </a>
            <p class="text-primary">${param.message}</p>
            <p class="text-danger">${param.error}</p>
        </c:if>
        <div class="d-none" id="updateForm">
            <form action="${pageContext.request.contextPath}/pharmacy" method="POST">
                <input type="hidden" name="command" value="update_recipe">
                <input type="hidden" name="recipe_id" value="${recipe.id}">
                <input type="hidden" name="recipe_patient" value="${recipe.patientId}">
                <input type="hidden" name="recipe_doctor" value="${recipe.doctorId}">
                <input type="hidden" name="recipe_medicine" value="${recipe.medicineId}">
                <input type="hidden" name="recipe_dose" value="${recipe.dose}">
                <div class="form-group">
                    <label for="update_duration"><fmt:message key="recipe.duration" bundle="${rb}"/></label>
                    <input type="number" name="recipe_duration" id="update_duration" class="form-control" placeholder="<fmt:message key="recipe.duration.placeholder" bundle="${rb}"/>">
                </div>
                <button type="submit" class="btn btn-success"><fmt:message key="recipe.button.update" bundle="${rb}"/></button>
            </form>
            <small> <button onclick="closeUpdate()" class="btn btn-danger"><fmt:message key="recipe.button.close" bundle="${rb}"/></button></small>
        </div>
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

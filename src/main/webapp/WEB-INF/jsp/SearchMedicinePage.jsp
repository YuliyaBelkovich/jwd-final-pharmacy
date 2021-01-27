<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 05.01.2021
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">


    <title>MEDICINES</title>
</head>
<body>
<style type="text/css">
    BODY {
        background: white;
    }
    A {
        color: black;
    }
    A:visited {
        color: #565353;
    }
    A:active {
        color: red;
    }
</style>
<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #d45a6c;">
    <div class="container-fluid">
        <a href="/pharmacy?command=go_to_main_page" class="navbar-brand">SACRED HEART PHARMACY</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
                aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="nav navbar-nav">
                <c:if test="${sessionScope.user_role=='GUEST'}">
                    <a class="nav-link" href="/pharmacy?command=go_to_login_page">log in</a>
                </c:if>
                <c:if test="${sessionScope.user_role=='GUEST'}">
                    <a class="nav-link" href="/pharmacy?command=go_to_register_page">register</a>
                </c:if>
                <c:if test="${sessionScope.user_role!='GUEST'}">
                    <a class="nav-link" href="/pharmacy?command=log_out">log out</a>
                </c:if>
                <c:if test="${sessionScope.user_role=='GUEST' or sessionScope.user_role=='PATIENT'}">
                    <a class="nav-link" href="/pharmacy?command=go_to_basket_page">basket</a>
                </c:if>
                <c:if test="${sessionScope.user_role!='GUEST'}">
                    <a class="nav-link" href="/pharmacy?command=go_to_user_main_page">my profile</a>
                </c:if>
                <a class="nav-link" href=""><small>ru</small></a>
                <a class="nav-link" href=""><small>en</small></a>
            </div>
        </div>
    </div>
</nav>

<%--USER NAVIGATION BAR--%>

<nav class="nav"style="background-color: #dec6cb;" >
    <div class="container-fluid">
        <ul class="nav justify-content-center">
            <li class="nav-item">
                <a class="nav-link" href="/pharmacy?command=go_to_search_medicine_page">MEDICINES</a>
            </li>

            <c:if test="${sessionScope.user_role!='GUEST'}">
            <ul class="nav justify-content-center">
                <li class="nav-item">
                    <a class="nav-link"
                       href="/pharmacy?command=go_to_search_doctor_page">DOCTORS</a>
                </li>
                </c:if>
                <c:if test="${sessionScope.user_role=='DOCTOR' or sessionScope.user_role=='PHARMACIST' }">
                    <c:if test="${sessionScope.user_status=='ACTIVE'}">
                        <li class="nav-item">
                            <a class="nav-link"
                               href="/pharmacy?command=go_to_search_patient_page">PATIENTS</a>
                        </li>
                    </c:if>
                </c:if>
                <c:if test="${sessionScope.user_role=='PHARMACIST'}">
                    <li class="nav-item"><a class="nav-link"
                                            href="/pharmacy?command=go_to_search_order_page">ORDERS</a></li>
                </c:if>

                <c:if test="${sessionScope.user_role=='PHARMACIST'  or sessionScope.user_role=='DOCTOR'}">
                    <li class="nav-item"><a class="nav-link"
                                            href="/pharmacy?command=go_to_search_appointment_page">APPOINTMENTS</a></li>
                </c:if>

                <c:if test="${sessionScope.user_role=='PHARMACIST'}">
                    <li class="nav-item"><a class="nav-link"
                                            href="/pharmacy?command=go_to_search_payment_page">PAYMENTS</a></li>
                </c:if>
                <c:if test="${sessionScope.user_role=='DOCTOR' or sessionScope.user_role=='PHARMACIST' }">
                    <c:if test="${sessionScope.user_status=='ACTIVE'}">
                        <li class="nav-item"><a
                                class="nav-link"
                                href="/pharmacy?command=go_to_search_recipe_page">RECIPES</a></li>
                    </c:if>
                </c:if>
            </ul>
    </div>
</nav>

<%--FIRST COLUMN - SEARCH MEDICINE FORM--%>

<div class="row">
    <div class="col-md-3">
        <form action="${pageContext.request.contextPath}/pharmacy" method="GET">
            <input type="hidden" name="command" value="search_medicine">
            <c:if test="${sessionScope.user_role=='DOCTOR' or sessionScope.user_role=='PHARMACIST' }">
                <div class="form-group">
                    <label for="id">ID</label>
                    <input type="number" name="medicine_id" class="form-control" id="id"
                           placeholder="Enter medicine id">
                </div>
            </c:if>
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" name="medicine_name" class="form-control" id="name"
                       placeholder="Enter medicine name">
            </div>
            <div class="form-check form-check-inline">
                <input type="checkbox" name="recipe_requirement" class="form-check-input" id="recipeCheck1">
                <label class="form-check-label" for="recipeCheck1">With recipe</label>
            </div>
            <div class="form-check form-check-inline">
                <input type="checkbox" name="non_recipe_requirement" class="form-check-input" id="recipeCheck2">
                <label class="form-check-label" for="recipeCheck2">Without recipe</label>
            </div>
            <div class="form-group">
                <label for="dose">Dose</label>
                <input type="number" step="0.01" name="medicine_dose" class="form-control" id="dose"
                       placeholder="Enter medicine dose">
            </div>
            <div class="form-group">
                <label for="price">Price</label>
                <input type="number" step="0.01" name="medicine_price" class="form-control" id="price"
                       placeholder="Enter medicine price">
            </div>
            <button type="submit" class="btn btn-success">Find</button>
        </form>
    </div>
    <div class="col-md-9">
        <p class="text-danger">${requestScope.Error}</p>
        <p class="text-info">${requestScope.Message}</p>
        <p class="text-danger">${sessionScope.Error}</p>
        <p class="text-info">${sessionScope.Message}</p>
        <div class="row">
            <c:forEach items="${Medicine}" var="medicine">

                <%--            DISPLAY SEARCH RESULT--%>

                <div class="col-md-4">
                    <div>
                        <ul class="list-group">
                            <c:if test="${sessionScope.user_role=='DOCTOR' or sessionScope.user_role=='PHARMACIST' }">
                                <small>
                                    <label for="result_id">id</label>
                                    <li class="list-group-item list-group-item-light" id="result_id"><c:out
                                            value="${medicine.id}"/></li>
                                </small>
                            </c:if>
                            <label for="result_name">name</label>
                            <a href="/pharmacy?command=go_to_medicine_page&medicine_id=${medicine.id}">
                                <li class="list-group-item list-group-item-primary" id="result_name"><c:out
                                        value="${medicine.name}"/></li>
                            </a>
                            <label for="result_dose">dose</label>
                            <li class="list-group-item" id="result_dose"><c:out value="${medicine.dose}"/></li>
                            <label for="result_recipe">recipe requirement</label>
                            <li class="list-group-item" id="result_recipe"><c:out
                                    value="${medicine.recipeRequirement}"/></li>
                            <label for="result_info">information</label>
                            <li class="list-group-item list-group-item-info" id="result_info"><c:out
                                    value="${medicine.info}"/></li>
                            <label for="result_price">price</label>
                            <li class="list-group-item list-group-item-success" id="result_price"><c:out
                                    value="${medicine.price}"/> $
                            </li>
                        </ul>
                    </div>

                </div>

                <%--                DISPLAY ACTION BUTTONS--%>

                <div class="col-md-2">
                    <p><c:if
                            test="${sessionScope.user_role == 'PHARMACIST'}">
                        <a href="/pharmacy?command=delete_medicine&medicine_id=${medicine.id}">
                            <button type="button" class="btn btn-danger">Delete</button>
                            </c:if></a></p>
                    <p>
                        <c:if
                                test="${sessionScope.user_role == 'PHARMACIST'}">
                            <button type="button" onclick="openUpdate()" class="btn btn-warning">Update</button>
                        </c:if></p>
                    <p>
                        <c:if
                                test="${sessionScope.user_role=='PATIENT' or sessionScope.user_role=='GUEST'}">
                        <a href="/pharmacy?command=add_to_basket&medicine_id=${medicine.id}&amount=30">
                            <button type="button" class="btn btn-success">Add to basket</button>
                            </c:if></a></p>
                    <p>
                        <c:if
                                test="${sessionScope.user_role == 'DOCTOR'}">
                                <button type="button" onclick="openAdd()" class="btn btn-warning">Prescribe</button>
                        </c:if>
                    </p>
                        <%--                PRESCRIBE!!!!!!!!--%>

                        <%--                    UPDATE MEDICINE FORM--%>

                </div>
                <div class="col-md-3">
                    <div class="d-none" id="updateForm">
                        <form action="${pageContext.request.contextPath}/pharmacy" method="POST">
                            <input type="hidden" name="command" value="update_medicine">
                            <input type="hidden" name="medicine_id" value="${medicine.id}">
                            <div class="form-group">
                                <label for="nameUpdate">Name</label>
                                <input type="text" name="medicine_name" class="form-control" id="nameUpdate"
                                       placeholder="Enter medicine name">
                            </div>
                            <div class="form-check">
                                <input type="checkbox" name="recipe_requirement" class="form-check-input"
                                       id="recipeCheck1Update">
                                <label class="form-check-label" for="recipeCheck1Update">With recipe</label>
                            </div>
                            <div class="form-group">
                                <label for="doseUpdate">Dose</label>
                                <input type="number" step="0.01" name="medicine_dose" class="form-control"
                                       id="doseUpdate"
                                       placeholder="Enter medicine dose">
                            </div>
                            <div class="form-group">
                                <label for="infoUpdate">Info</label>
                                <input type="text" name="medicine_info" class="form-control" id="infoUpdate"
                                       placeholder="Enter medicine info">
                            </div>
                            <div class="form-group">
                                <label for="priceUpdate">Price</label>
                                <input type="number" step="0.01" name="medicine_price" class="form-control"
                                       id="priceUpdate"
                                       placeholder="Enter medicine price">
                            </div>
                            <button type="submit" class="btn btn-success">Update</button>
                        </form>
                        <small>
                            <button onclick="closeUpdate()" class="btn btn-danger">Close</button>
                        </small>
                    </div>
                    <div class="d-none" id="addForm">
                        <form action="${pageContext.request.contextPath}/pharmacy" method="POST">
                            <input type="hidden" name="command" value="add_recipe">
                            <input type="hidden" name="recipe_medicine" value="${medicine.id}">
                            <input type="hidden" name="recipe_doctor" value="${sessionScope.user_id}">
                            <input type="hidden" name="recipe_duration" value="30">
                            <div class="form-group">
                                <label for="recipe_patient">patient id</label>
                                <input type="number" name="recipe_patient" class="form-control" id="recipe_patient"
                                       placeholder="Enter patient id">
                            </div>
                            <div class="form-group">
                                <label for="recipe_dose">dose</label>
                                <input type="number" name="recipe_dose" class="form-control" id="recipe_dose"
                                       placeholder="Enter dose">
                            </div>
                            <button type="submit" class="btn btn-success">Submit</button>
                        </form>
                        <button onclick="closeAdd()" class="btn btn-danger">Close</button>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

</div>
<script>
    function openUpdate() {
        document.getElementById("updateForm").classList.replace("d-none", "d-block");
    }

    function closeUpdate() {
        document.getElementById("updateForm").classList.replace("d-block", "d-none");
    }

    function openAdd() {
        document.getElementById("addForm").classList.replace("d-none", "d-block");
    }

    function closeAdd() {
        document.getElementById("addForm").classList.replace("d-block", "d-none");
    }
</script>
</body>
</html>

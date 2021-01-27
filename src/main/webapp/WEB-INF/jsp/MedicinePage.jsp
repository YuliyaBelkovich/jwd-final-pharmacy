<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 16.01.2021
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%--    <!-- Required meta tags -->--%>
    <%--    <meta charset="utf-8">--%>
    <%--    <meta name="viewport" content="width=device-width, initial-scale=1">--%>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--        <!-- Bootstrap CSS -->--%>
    <%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"--%>
    <%--          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">--%>
    <%--    <!-- Latest compiled and minified CSS -->--%>
    <%--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"--%>
    <%--          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">--%>

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">


    <title>Home page</title>
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
            <label for="result_name">name</label>
            <a href="/pharmacy?command=go_to_medicine_page&medicine_id=${medicine.id}">
                <li class="list-group-item list-group-item-primary" id="result_name"><c:out
                        value="${medicine.name}"/></li>
            </a>
            <label for="result_dose">dose</label>
            <li class="list-group-item" id="result_dose"><c:out value="${medicine.dose}"/></li>
            <label for="result_recipe">recipe requirement</label>
            <li class="list-group-item" id="result_recipe"><c:out value="${medicine.recipeRequirement}"/></li>
            <label for="result_info">information</label>
            <li class="list-group-item list-group-item-info" id="result_info"><c:out value="${medicine.info}"/></li>
            <label for="result_price">price</label>
            <li class="list-group-item list-group-item-success" id="result_price"><c:out value="${medicine.price}"/> $
            </li>
        </ul>
    </div>

</div>

<%--                DISPLAY ACTION BUTTONS--%>
<div class="row">
    <div class="col-md-4">
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
        <%--                PRESCRIBE!!!!!!!!--%>

        <%--                    UPDATE MEDICINE FORM--%>
        <small>
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
                        <input type="number" step="0.01" name="medicine_dose" class="form-control" id="doseUpdate"
                               placeholder="Enter medicine dose">
                    </div>
                    <div class="form-group">
                        <label for="infoUpdate">Info</label>
                        <input type="text" name="medicine_info" class="form-control" id="infoUpdate"
                               placeholder="Enter medicine info">
                    </div>
                    <div class="form-group">
                        <label for="priceUpdate">Price</label>
                        <input type="number" step="0.01" name="medicine_price" class="form-control" id="priceUpdate"
                               placeholder="Enter medicine price">
                    </div>
                    <button type="submit" class="btn btn-success">Update</button>
                </form>
                <small>
                    <button onclick="closeUpdate()" class="btn btn-danger">Close</button>
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

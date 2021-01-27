<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 11.01.2021
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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


    <title>DOCTORS</title>
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
<%--FIRST COLUMN - SEARCH DOCTOR FORM--%>

<div class="row">
    <div class="col-md-3">
        <form action="${pageContext.request.contextPath}/pharmacy" method="GET">
            <input type="hidden" name="command" value="search_user">
            <input type="hidden" name="user_role" value="DOCTOR">
            <div class="form-group">
                <label for="id">ID</label>
                <input type="number" name="user_id" class="form-control" id="id"
                       placeholder="Enter doctor's id">
            </div>
            <div class="form-group">
                <label for="email">E-mail</label>
                <input type="text" name="user_email" class="form-control" id="email"
                       placeholder="Enter doctor's email">
            </div>
            <div class="form-group">
                <label for="status_user">Status</label>
                <input type="search" name="user_status" class="form-control" list="status" id="status_user"
                       placeholder="Enter doctor's status">
            </div>
            <datalist id="status">
                <option value="ACTIVE" label="ACTIVE">
                <option value="BANNED" label="BANNED">
                <option value="PENDING" label="PENDING">
            </datalist>
            <button type="submit" class="btn btn-success">Find</button>
        </form>
    </div>
    <div class="col-md-9">
        <p class="text-danger">${requestScope.Error}</p>
        <p class="text-info">${requestScope.Message}</p>
        <p class="text-danger">${sessionScope.Error}</p>
        <p class="text-info">${sessionScope.Message}</p>
        <div class="row">
            <c:forEach items="${Doctor}" var="doctor">

                <%--            DISPLAY SEARCH RESULT--%>

                <div class="col-md-5">
                    <div>
                        <ul class="list-group">
                            <small>
                                <label for="result_id">id</label>
                                <li class="list-group-item list-group-item-light" id="result_id"><c:out
                                        value="${doctor.id}"/></li>
                            </small>
                            <label for="result_name">name</label>
                            <a href="/pharmacy?command=go_to_doctor_page&doctor_id=${doctor.id}"> <li class="list-group-item list-group-item-primary" id="result_name"><c:out value="${doctor.name}"/></li></a>

                            <label for="result_email">e-mail</label>
                            <li class="list-group-item " id="result_email"><c:out value="${doctor.email}"/></li>
                            <label for="result_status">status</label>
                            <li class="list-group-item list-group-item-info" id="result_status"><c:out value="${doctor.status}"/></li>
                        </ul>
                    </div>
                </div>

                <%--                DISPLAY ACTION BUTTONS--%>

                <div class="col-md-4">
<%--                    APPOINTMENTS AND RECIPES!!!!!!!!--%>
                        <c:if
                                test="${sessionScope.user_role == 'PHARMACIST'}">
                            <button type="button" onclick="openUpdate()" class="btn btn-warning">Update</button>
                        </c:if></p>

                        <%--                    UPDATE DOCTOR STATUS FORM--%>
                    <small>
                        <div class="d-none" id="updateStatusForm">
                            <form action="${pageContext.request.contextPath}/pharmacy" method="POST">
                                <input type="hidden" name="command" value="update_user">
                                <input type="hidden" name="user_id" value="${doctor.id}">
                                <input type="hidden" name="user_name" value="${doctor.name}">
                                <input type="hidden" name="user_email" value="${doctor.email}">
                                <input type="hidden" name="user_password" value="${doctor.password}">
                                <input type="hidden" name="user_role" value="DOCTOR">
                                <div class="form-group">
                                    <label for="update_status_user">Status</label>
                                    <input type="search" name="user_status" class="form-control" list="updateStatus" id="update_status_user"
                                           placeholder="Enter doctor's status">
                                </div>
                                <datalist id="updateStatus">
                                    <option value="ACTIVE" label="ACTIVE">
                                    <option value="BANNED" label="BANNED">
                                    <option value="PENDING" label="PENDING">
                                </datalist>
                                <button type="submit" class="btn btn-success">Update</button>
                            </form>
                            <small>
                                <button onclick="closeUpdate()" class="btn btn-danger">Close</button>
                            </small>
                        </div>
                    </small>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<script>
    function openUpdate() {
        document.getElementById("updateStatusForm").classList.replace("d-none","d-block");
    }

    function closeUpdate() {
        document.getElementById("updateStatusForm").classList.replace("d-block","d-none");
    }
</script>
</body>
</html>

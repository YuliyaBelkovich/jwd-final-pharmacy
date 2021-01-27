<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 11.01.2021
  Time: 16:16
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


    <title>APPOINTMENTS</title>
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

<%--RECIPE SEARCH FORM--%>

<div class="row">
    <div class="col-md-3">
<c:if test="${sessionScope.user_role=='DOCTOR' or sessionScope.user_role=='PHARMACIST'}">
        <form action="${pageContext.request.contextPath}/pharmacy" method="GET">
            <input type="hidden" name="command" value="search_appointment">
            <div class="form-group">
                <label for="id">ID</label>
                <input type="number" name="appointment_id" class="form-control" id="id"
                       placeholder="Enter appointment id">
            </div>
            <div class="form-group">
                <label for="id_patient">Patient's id</label>
                <input type="number" name="appointment_patient" class="form-control" id="id_patient"
                       placeholder="Enter patient's id">
            </div>
            <div class="form-group">
                <label for="id_doctor">Doctor's id</label>
                <input type="number" name="appointment_doctor" class="form-control" id="id_doctor"
                       placeholder="Enter doctor's id">
            </div>
            <div class="form-group">
                <label for="date">Date time</label>
                <input type="datetime-local" name="appointment_date" class="form-control" id="date"
                       placeholder="Enter date and time">
            </div>
            <div class="form-group">
                <label for="status">Status</label>
                <input type="search" name="appointment_date" list="statusList" class="form-control" id="status"
                       placeholder="Choose status">
                <datalist id="statusList">
                    <option value="CANCELLED" label="CANCELLED">
                    <option value="PLACED" label="PLACED">
                    <option value="PLANNED" label="PLANNED">
                </datalist>
            </div>
            <button type="submit" class="btn btn-success">Find</button>
        </form>
</c:if>
    </div>
    <div class="col-md-9">
        <p class="text-danger">${requestScope.Error}</p>
        <p class="text-info">${requestScope.Message}</p>
        <p class="text-danger">${sessionScope.Error}</p>
        <p class="text-info">${sessionScope.Message}</p>
        <div class="row">
            <c:forEach items="${Appointment}" var="appointment">

                <%--            DISPLAY SEARCH RESULT--%>

                <div class="col-md-4">
                    <div>
                        <ul class="list-group">
                            <small>
                                <label for="result_id">id</label>
                                <a href="/pharmacy?command=go_to_appointment_page&appointment_id=${appointment.id}">
                                    <li class="list-group-item list-group-item-light" id="result_id"><c:out
                                            value="${appointment.id}"/></li>
                                </a>
                            </small>
                            <label for="result_patient">patient id</label>
                            <a href="/pharmacy?command=go_to_patient_page&patient_id=${appointment.patientId}">
                                <li class="list-group-item list-group-item-primary" id="result_patient"><c:out
                                        value="${appointment.patientId}"/></li>
                            </a>
                            <label for="result_doctor">doctor id</label>
                            <a href="/pharmacy?command=go_to_doctor_page&doctor_id=${appointment.doctorId}">
                                <li class="list-group-item" id="result_doctor"><c:out
                                        value="${appointment.doctorId}"/></li>
                            </a>
                            <label for="result_date">date time</label>
                            <li class="list-group-item list-group-item-primary" id="result_date"><c:out
                                    value="${appointment.dateTime}"/></li>
                            <label for="result_info">date time</label>
                            <li class="list-group-item list-group-item-primary" id="result_info"><c:out
                                    value="${appointment.info}"/></li>
                            <label for="result_status">status</label>
                            <li class="list-group-item list-group-item-primary" id="result_status">
                                <c:out
                                        value="${appointment.appointmentStatus}"/>
                            </li>
                        </ul>
                    </div>
                </div>

                <%--                DISPLAY ACTION BUTTONS--%>

                <div class="col-md-2">
                    <c:if
                            test="${sessionScope.user_role == 'DOCTOR' && sessionScope.user_status=='ACTIVE'}">
                            <button type="button" onclick="openUpdate()" class="btn btn-warning">Edit</button>
                        </a>
                    </c:if>
                </div>
                <div class="col-md-3">
                    <div class="d-none" id="updateForm">
                        <form action="${pageContext.request.contextPath}/pharmacy" method="POST">
                            <input type="hidden" name="appointment_id" value="${appointment.id}">
                            <input type="hidden" name="appointment_doctor" value="${appointment.doctorId}">
                            <input type="hidden" name="appointment_patient" value="${appointment.patientId}">
                            <input type="hidden" name="appointment_date" value="${appointment.dateTime}">
                            <div class="form-group">
                                <label for="update_info">Information</label>
                                <input type="text" name="appointment_info" id="update_info" class="form-control" placeholder="Enter appointment info">
                            </div>
                            <div class="form-group">
                                <label for="update_status">Status</label>
                                <input type="search" name="appointment_date" list="statusList" class="form-control" id="update_status"
                                       placeholder="Choose status">
                            </div>
                            <button type="submit" class="btn btn-success">Update</button>
                        </form>
                        <small> <button onclick="closeUpdate()" class="btn btn-danger">Close</button></small>
                    </div>
                </div>
            </c:forEach>
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

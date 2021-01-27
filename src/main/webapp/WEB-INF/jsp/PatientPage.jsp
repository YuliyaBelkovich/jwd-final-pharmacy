<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 11.01.2021
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <title>Patient</title>
</head>
<body>
<%--TOP NAVIGATION BAR--%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <div class="navbar-header">
            <a href="/pharmacy?command=go_to_main_page" class="navbar-brand">SACRED HEART PHARMACY</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <c:if test="${sessionScope.user_role!='GUEST'}">
                    <li><a href="/pharmacy?command=log_out">log out</a></li>
                </c:if>
                <c:if test="${sessionScope.user_role=='GUEST' or sessionScope.user_role=='PATIENT'}">
                    <li><a href="/pharmacy?command=go_to_basket_page">basket</a></li>
                </c:if>
                <c:if test="${sessionScope.user_role!='GUEST'}">
                    <li><a href="/pharmacy?command=go_to_user_main_page">my profile</a></li>
                </c:if>
                <li><a href=""><small>ru</small></a></li>
                <li><a href=""><small>en</small></a></li>
            </ul>
        </div>
    </div>
</nav>

<%--USER NAVIGATION BAR--%>

<nav class="nav">
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
                <c:if test="${sessionScope.user_role=='DOCTOR' or sessionScope.user_role=='PHARMACIST'}">
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

                <c:if test="${sessionScope.user_role=='PHARMACIST'}">
                    <li class="nav-item"><a class="nav-link"
                                            href="/pharmacy?command=go_to_search_appointment_page">APPOINTMENTS</a></li>
                </c:if>

                <c:if test="${sessionScope.user_role=='PHARMACIST'}">
                    <li class="nav-item"><a class="nav-link"
                                            href="/pharmacy?command=go_to_search_payment_page">PAYMENTS</a></li>
                </c:if>
                <c:if test="${sessionScope.user_role=='DOCTOR' or sessionScope.user_role=='PHARMACIST'}">
                    <c:if test="${sessionScope.user_status=='ACTIVE'}">
                        <li class="nav-item"><a
                                class="nav-link"
                                href="/pharmacy?command=go_to_search_recipe_page">RECIPES</a></li>
                    </c:if>
                </c:if>
            </ul>
    </div>
</nav>

<div class="row">
    <div class="col-md-5">
        <div>
            <ul class="list-group">
                <small>
                    <label for="result_id">id</label>
                    <li class="list-group-item list-group-item-light" id="result_id"><c:out
                            value="${patient.id}"/></li>
                </small>
                <label for="result_name">name</label>
                <a href="/pharmacy?command=go_to_patient_page&patient_id=${patient.id}"> <li class="list-group-item list-group-item-primary" id="result_name"><c:out value="${patient.name}"/></li></a>
                <label for="result_email">e-mail</label>
                <li class="list-group-item" id="result_email"><c:out value="${patient.email}"/></li>
                <label for="result_status">status</label>
                <li class="list-group-item list-group-item-info" id="result_status"><c:out value="${patient.status}"/></li>
            </ul>
        </div>
    </div>

    <%--                DISPLAY ACTION BUTTONS--%>

    <div class="col-md-4">
        <c:if
                test="${sessionScope.user_role == 'PHARMACIST'}">
            <button type="button" onclick="openUpdate()" class="btn btn-warning">Update</button>
        </c:if>
<%--        APPOINTMENTS AND RECIPES!!!!!!!!!!--%>

        <%--                    UPDATE PATIENT STATUS FORM--%>
        <small>
            <div class="d-none" id="updateStatusForm">
                <form action="${pageContext.request.contextPath}/pharmacy" method="POST">
                    <input type="hidden" name="command" value="update_user">
                    <input type="hidden" name="user_id" value="${patient.id}">
                    <input type="hidden" name="user_name" value="${patient.name}">
                    <input type="hidden" name="user_email" value="${patient.email}">
                    <input type="hidden" name="user_password" value="${patient.password}">
                    <input type="hidden" name="user_role" value="PATIENT">
                    <div class="form-group">
                        <label for="update_status_user">Status</label>
                        <input type="search" name="user_status" class="form-control" list="updateStatus" id="update_status_user"
                               placeholder="Enter patient's status">
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

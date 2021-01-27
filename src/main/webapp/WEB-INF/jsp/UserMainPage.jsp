<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 05.01.2021
  Time: 21:32
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
<nav>
    <div class="nav nav-tabs" id="nav-tab" role="tablist">
        <a class="nav-link active" id="profile-tab" role="tab" href="#profile" data-bs-toggle="tab"
           aria-controls="profile" aria-selected="true">
            MY PROFILE
        </a>
        <c:if test="${sessionScope.user_role=='DOCTOR'}">
            <a class="nav-link" id="timeslot-tab" role="tab" href="#timeslot" data-bs-toggle="tab"
               aria-controls="timeslot" aria-selected="false">NEW TIMESLOT</a>
            </li>
        </c:if>
        <c:if test="${sessionScope.user_role=='PHARMACIST'}">
            <a class="nav-link" id="medicine-tab" role="tab" href="#medicine" data-bs-toggle="tab"
               aria-controls="medicine" aria-selected="false">NEW MEDICINE</a>
        </c:if>
        <c:if test="${sessionScope.user_role=='PATIENT'}">
            <a class="nav-link" id="bank-tab" role="tab" href="#bank" data-bs-toggle="tab"
               aria-controls="bank" aria-selected="false">ADD BANK ACCOUNT</a>
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
                               href="/pharmacy?command=search_order&order_patient=${sessionScope.user_id}">MY ORDERS</a>
                    </c:if>
                    <c:if test="${sessionScope.user_role=='PATIENT'}">
                            <a class="nav-link"
                               href="/pharmacy?command=search_appointment&appointment_patient=${sessionScope.user_id}">MY
                                APPOINTMENTS</a>
                    </c:if>
                    <c:if test="${sessionScope.user_role=='DOCTOR'}">

                            <a class="nav-link"
                               href="/pharmacy?command=search_appointment&appointment_doctor=${sessionScope.user_id}">MY
                                APPOINTMENTS</a>

                    </c:if>
                    <c:if test="${sessionScope.user_role=='PATIENT'}">

                            <a class="nav-link"
                               href="/pharmacy?command=search_recipe&recipe_patient=${sessionScope.user_id}">MY
                                RECIPES</a>

                    </c:if>
                    <c:if test="${sessionScope.user_role=='DOCTOR'}">

                            <a class="nav-link"
                               href="/pharmacy?command=search_recipe&recipe_doctor=${sessionScope.user_id}">GIVEN
                                RECIPES</a>

                    </c:if>
                    <c:if test="${sessionScope.user_role=='DOCTOR'}">

                            <a class="nav-link"
                               href="/pharmacy?command=search_window&window_doctor=${sessionScope.user_id}">TIMESLOTS</a>

                    </c:if>
                    <c:if test="${sessionScope.user_role=='PATIENT'}">

                            <a class="nav-link"
                               href="/pharmacy?command=search_request&request_patient=${sessionScope.user_id}">RECIPE
                                PROLONGATION REQUESTS</a>

                    </c:if>
                    <c:if test="${sessionScope.user_role=='DOCTOR'}">
                            <a class="nav-link"
                               href="/pharmacy?command=search_request&request_doctor=${sessionScope.user_id}">RECIPE
                                PROLONGATION REQUESTS</a>
                    </c:if>
                    <c:if test="${sessionScope.user_role=='PATIENT'}">
                        <a class="nav-link"
                           href="/pharmacy?command=search_request&request_patient=${sessionScope.user_id}">MY BANK ACCOUNTS</a>
                    </c:if>
                </nav>
            </div>
            <div class="col-md-4">
                <%--        USER INFO--%>
                <div>
                    <ul class="list-group">
                        <label for="id">ID</label>
                        <li class="list-group-item  list-group-item-light" id="id">${sessionScope.user_id}</li>

                        <label for="name">Name</label>
                        <li class="list-group-item " id="name">${sessionScope.user_name}</li>

                        <label for="email">E-mail</label>
                        <li class="list-group-item " id="email">${sessionScope.user_email}</li>
                        <label for="role">Role</label>
                        <li class="list-group-item " id="role">${sessionScope.user_role}</li>
                        <label for="status">Status</label>
                        <li class="list-group-item " id="status">${sessionScope.user_status}</li>
                    </ul>

                </div>
            </div>
            <div class="col-md-3">
<%--                ORDER--%>
                <div>
                    <c:forEach items="${Orders}" var="order">
                    <ul class="list-group">
                        <label for="order_id">id</label>
                        <li class="list-group-item list-group-item-light" id="order_id"><c:out value="${order.id}"/> </li>
                        <label for="order_price">price</label>
                        <li class="list-group-item" id="order_price"><c:out value="${order.price}"/> </li>
                        <c:forEach items="${order.orderedMedicines}" var="orderMed">
                            <li class="list-group-item"><c:out value="${orderMed.name}, ${orderMed.dose}"/></li>
                        </c:forEach>
                    </ul>
                    </c:forEach>
                </div>
<%--                APPOINTMENT--%>
                <div>
                <c:forEach items="${Appointment}" var="appointment">
                <ul class="list-group">
                    <label for="appointment_id">id</label>
                    <li class="list-group-item list-group-item-light" id="appointment_id"><c:out value="${appointment.id}"/> </li>
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
                </c:forEach>
                </div>
<%--                RECIPE--%>
                <div>
                    <c:forEach items="${Recipe}" var="recipe">
                        <ul class="list-group">
                            <label for="recipe_id">id</label>
                            <a href="/pharmacy?command=go_to_recipe_page&recipe_id=${recipe.id}">
                                <li class="list-group-item list-group-item-light" id="recipe_id"><c:out
                                        value="${recipe.id}"/></li>
                            </a>
                            <label for="result_patient_recipe">patient id</label>
                            <a href="/pharmacy?command=go_to_patient_page&patient_id=${recipe.patientId}">
                                <li class="list-group-item list-group-item-primary" id="result_patient_recipe"><c:out
                                        value="${recipe.patientId}"/></li>
                            </a>
                            <label for="result_medicine">medicine id</label>
                            <a href="/pharmacy?command=go_to_medicine_page&medicine_id=${recipe.medicineId}">
                                <li class="list-group-item list-group-item-primary" id="result_medicine"><c:out
                                        value="${recipe.medicineId}"/></li>
                            </a>
                            <label for="result_doctor_recipe">doctor id</label>
                            <a href="/pharmacy?command=go_to_doctor_page&doctor_id=${recipe.doctorId}">
                                <li class="list-group-item" id="result_doctor_recipe"><c:out
                                        value="${recipe.doctorId}"/></li>
                            </a>
                            <label for="result_dose">dose</label>
                            <li class="list-group-item" id="result_dose"><c:out
                                    value="${recipe.dose}"/></li>
                            <label for="result_date_recipe">date</label>
                            <li class="list-group-item list-group-item-primary" id="result_date_recipe"><c:out
                                    value="${recipe.date}"/></li>
                            <label for="result_duration">duration</label>
                            <li class="list-group-item" id="result_duration"><c:out value="${recipe.duration}"/></li>
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
                            <label for="window_doctor">doctor id</label>
                            <li class="list-group-item" id="window_doctor"><c:out value="${window.doctorId}"/></li>
                           <label for="window_date">date time</label>
                            <li class="list-group-item" id="window_date"><c:out value="${window.dateTime}"/> </li>
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
                            <label for="bank_patient">patient id</label>
                            <li class="list-group-item list-group-item-light" id="bank_patient">
                                <c:out value="${bank.patientId}"/>
                            </li>
                            <label for="bank_iban">IBAN</label>
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
        <p class="text-primary">${sessionScope.Message}</p>
        <p class="text-danger">${sessionScope.Error}</p>
        <form action="${pageContext.request.contextPath}/pharmacy" method="POST">
            <input type="hidden" name="command" value="add_window">
            <input type="hidden" name="window_doctor" value="${sessionScope.user_id}">
            <div class="form-group">
                <label for="date">Date time</label>
                <input type="datetime-local" name="window_date" class="form-control" id="date"
                       placeholder="Enter date and time">
            </div>
            <button type="submit" class="btn btn-success">Submit</button>
        </form>
    </div>
    <div class="tab-pane fade" id="bank" role="tabpanel" aria-labelledby="bank-tab">
        <p class="text-primary">${sessionScope.Message}</p>
        <p class="text-danger">${sessionScope.Error}</p>
        <form action="${pageContext.request.contextPath}/pharmacy" method="POST">
            <input type="hidden" name="command" value="add_bank_account">
            <input type="hidden" name="bank_account_patient" value="${sessionScope.user_id}">
            <div class="form-group">
                <label for="iban">IBAN</label>
                <input type="text" name="bank_account_iban" class="form-control" id="iban">
            </div>
            <div class="form-group">
                <label for="card_holder">Card holder</label>
                <input type="text" name="bank_account_holder" class="form-control" id="card_holder">
            </div>
            <div class="form-group">
                <label for="expiry_date">Expiry date</label>
                <input type="text" name="bank_account_date" class="form-control" id="expiry_date">
            </div>
            <div class="form-group">
                <label for="cvv">CVV</label>
                <input type="number" name="bank_account_cvv" class="form-control" id="cvv">
            </div>
            <button type="submit" class="btn btn-success">Submit</button>
        </form>
    </div>
    <div class="tab-pane fade" id="medicine" role="tabpanel" aria-labelledby="medicine-tab">
        <p class="text-primary">${sessionScope.Message}</p>
        <p class="text-danger">${sessionScope.Error}</p>
        <form action="${pageContext.request.contextPath}/pharmacy" method="POST">
            <input type="hidden" name="command" value="add_medicine">
            <div class="form-group">
                <label for="name_medicine">Name</label>
                <input type="text" name="medicine_name" class="form-control" id="name_medicine"
                       placeholder="Enter medicine name">
            </div>
            <div class="form-check form-check-inline">
                <input type="checkbox" name="recipe_requirement" class="form-check-input" id="recipeCheck1">
                <label class="form-check-label" for="recipeCheck1">With recipe</label>
            </div>
            <div class="form-group">
                <label for="info">Information</label>
                <input type="text" name="medicine_info" class="form-control" id="info"
                       placeholder="Enter medicine info">
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
            <button type="submit" class="btn btn-success">Submit</button>
        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>

</body>
</html>

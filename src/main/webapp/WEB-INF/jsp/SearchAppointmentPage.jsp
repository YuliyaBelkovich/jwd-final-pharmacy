<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 11.01.2021
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
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


    <title><fmt:message key="search.appointment" bundle="${rb}"/> </title>
</head>
<body>
<c:import url="MainHeader.jsp"/>
<%--RECIPE SEARCH FORM--%>

<div class="row">
    <div class="col-md-3">
<c:if test="${sessionScope.user_role=='DOCTOR' or sessionScope.user_role=='PHARMACIST'}">
        <form action="${pageContext.request.contextPath}/pharmacy" method="GET">
            <input type="hidden" name="command" value="search_appointment">
            <div class="form-group">
                <label for="id">id</label>
                <input type="number" name="appointment_id" min="1" step="1" class="form-control" id="id"
                       placeholder="<fmt:message key="appointment.id.placeholder" bundle="${rb}"/>">
            </div>
            <div class="form-group">
                <label for="id_patient"><fmt:message key="appointment.patient" bundle="${rb}"/></label>
                <input type="number" name="appointment_patient" min="1" step="1" class="form-control" id="id_patient"
                       placeholder="<fmt:message key="appointment.patient.placeholder" bundle="${rb}"/>">
            </div>
            <div class="form-group">
                <label for="id_doctor"><fmt:message key="appointment.doctor" bundle="${rb}"/></label>
                <input type="number" name="appointment_doctor" min="1" step="1" class="form-control" id="id_doctor"
                       placeholder="<fmt:message key="appointment.doctor.placeholder" bundle="${rb}"/>">
            </div>
            <div class="form-group">
                <label for="date"><fmt:message key="appointment.date" bundle="${rb}"/></label>
                <input type="datetime-local" name="appointment_date" class="form-control" id="date"
                       placeholder="<fmt:message key="appointment.date.placeholder" bundle="${rb}"/>">
            </div>
            <div class="form-group">
                <label for="status"><fmt:message key="appointment.status" bundle="${rb}"/></label>
                <input type="search" name="appointment_date" list="statusList" class="form-control" id="status"
                       placeholder="<fmt:message key="appointment.status.placeholder" bundle="${rb}"/>">
                <datalist id="statusList">
                    <option value="CANCELLED" label="CANCELLED">
                    <option value="PLACED" label="PLACED">
                    <option value="PLANNED" label="PLANNED">
                </datalist>
            </div>
            <button type="submit" class="btn btn-success"><fmt:message key="search.find" bundle="${rb}"/></button>
        </form>
</c:if>
    </div>
    <div class="col-md-9">
        <p class="text-danger">${requestScope.Error}</p>
        <p class="text-info">${requestScope.Message}</p>
        <p class="text-primary">${param.message}</p>
        <p class="text-danger">${param.error}</p>
        <div class="row">
            <c:forEach items="${Appointment}" var="appointment">

                <%--            DISPLAY SEARCH RESULT--%>

                <div class="col-md-4">
                    <div>
                        <ul class="list-group">
                            <small>
                                <label for="result_id">id</label>
                                    <li class="list-group-item list-group-item-light" id="result_id"><c:out
                                            value="${appointment.id}"/></li>
                            </small>
                            <label for="result_patient"><fmt:message key="appointment.patient" bundle="${rb}"/></label>
                            <a href="/pharmacy?command=go_to_patient_page&patient_id=${appointment.patientId}">
                                <li class="list-group-item list-group-item-primary" id="result_patient"><c:out
                                        value="${appointment.patientId}"/></li>
                            </a>
                            <label for="result_doctor"><fmt:message key="appointment.doctor" bundle="${rb}"/></label>
                            <a href="/pharmacy?command=go_to_doctor_page&doctor_id=${appointment.doctorId}">
                                <li class="list-group-item" id="result_doctor"><c:out
                                        value="${appointment.doctorId}"/></li>
                            </a>
                            <label for="result_date"><fmt:message key="appointment.date" bundle="${rb}"/></label>
                            <li class="list-group-item list-group-item-primary" id="result_date"><c:out
                                    value="${f:formatLocalDateTime(appointment.dateTime, 'yyyy-MM-dd HH:mm')}"/></li>
                            <label for="result_info"><fmt:message key="appointment.info" bundle="${rb}"/></label>
                            <li class="list-group-item list-group-item-success" id="result_info"><c:out
                                    value="${appointment.info}"/></li>
                            <label for="result_status"><fmt:message key="appointment.status" bundle="${rb}"/></label>
                            <li class="list-group-item list-group-item-primary" id="result_status">
                                <c:out
                                        value="${appointment.appointmentStatus}"/>
                            </li>
                            <li class="list-group-item">...</li>
                        </ul>
                    </div>
                </div>

                <%--                DISPLAY ACTION BUTTONS--%>

                <div class="col-md-2">
                    <c:if
                            test="${sessionScope.user_role == 'DOCTOR' && sessionScope.user_status=='ACTIVE' && appointment.doctorId == sessionScope.user_id}">
                            <button type="button" onclick="openUpdate(${appointment.id})" class="btn btn-warning"><fmt:message key="appointment.button.edit" bundle="${rb}"/></button>
                        </a>
                    </c:if>
                </div>
                <div class="col-md-3">
                    <div class="d-none" id="updateForm${appointment.id}">
                        <form action="${pageContext.request.contextPath}/pharmacy" method="POST">
                            <input type="hidden" name="command" value="update_appointment">
                            <input type="hidden" name="appointment_id" value="${appointment.id}">
                            <input type="hidden" name="appointment_doctor" value="${appointment.doctorId}">
                            <input type="hidden" name="appointment_patient" value="${appointment.patientId}">
                            <input type="hidden" name="appointment_date" value="${appointment.dateTime}">
                            <div class="form-group">
                                <label for="update_info"><fmt:message key="appointment.info" bundle="${rb}"/></label>
                                <input type="text" name="appointment_info" id="update_info" class="form-control" placeholder="<fmt:message key="appointment.info.placeholder" bundle="${rb}"/>">
                            </div>
                            <div class="form-group">
                                <label for="update_status"><fmt:message key="appointment.status" bundle="${rb}"/></label>
                                <input type="search" name="appointment_status" list="statusList" class="form-control" id="update_status"
                                       placeholder="<fmt:message key="appointment.status.placeholder" bundle="${rb}"/>">
                            </div>
                            <p>   </p>
                            <button type="submit" class="btn btn-success"><fmt:message key="appointment.button.update" bundle="${rb}"/></button>
                        </form>
                        <small> <button onclick="closeUpdate(${appointment.id})" class="btn btn-danger"><fmt:message key="appointment.button.close" bundle="${rb}"/></button></small>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<script>
    function openUpdate(id) {
        document.getElementById("updateForm"+id).classList.replace("d-none","d-block");
    }

    function closeUpdate(id) {
        document.getElementById("updateForm"+id).classList.replace("d-block","d-none");
    }
</script>
</body>
</html>

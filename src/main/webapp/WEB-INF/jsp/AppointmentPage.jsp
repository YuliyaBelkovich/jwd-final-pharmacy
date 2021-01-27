<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 11.01.2021
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<tr>
    <td><c:out value="${appointment.id}"/></td>
    <td><a href="/pharmacy?command=search_user&user_id=${appointment.patientId}&user_role=PATIENT"><c:out
            value="${appointment.patientId}"/></a></td>
    <td><a href="/pharmacy?command=search_user&user_id=${appointment.doctorId}&user_role=DOCTOR"><c:out
            value="${appointment.doctorId}"/></a></td>
    <td><c:out value="${appointment.dateTime}"/></td>
    <td><c:out value="${appointment.info}"/></td>
    <td><c:out value="${appointment.appointmentStatus}"/></td>
    <td>
        <a href="/pharmacy?command=show_hidden_scope&url=/pharmacy?command=go_to_appointment_page&appointment_id=${appointment.id}"><c:if
                test="${sessionScope.user_role == 'DOCTOR'}">Edit appointment</c:if></a></td>
</tr>
${sessionScope.Message}
${sessionScope.Error}
<c:if test="${sessionScope.showHiddenScope==true}">
    <form action="${pageContext.request.contextPath}/pharmacy" method="POST">
        <input type="hidden" name="command" value="update_appointment">
        <input type="hidden" name="appointment_id" value="${appointment.id}">
        <input type="hidden" name="appointment_patient" value="${appointment.patientId}">
        <input type="hidden" name="appointment_doctor" value="${appointment.doctorId}">
        <input type="hidden" name="appointment_date" value="${appointment.dateTime}">
        Info: <input type="text" name="appointment_info">
        <br/>
        Status <input type="search" name="appointment_status" list="status" placeholder="appointment status">
        <datalist id="status">
            <option value="CANCELLED" label="CANCELLED">
            <option value="PLACED" label="PLACED">
            <option value="PLANNED" label="PLANNED">
        </datalist>
        <input type="submit" value="Submit"/>
    </form>
</c:if>
</body>
</html>

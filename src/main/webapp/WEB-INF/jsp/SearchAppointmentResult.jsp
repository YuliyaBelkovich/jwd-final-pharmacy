<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 11.01.2021
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <c:forEach items="${Appointment}" var="appointment">
        <tr>
            <td><c:out value="${appointment.id}"/></td>
            <td><a href="/pharmacy?command=search_user&user_id=${appointment.patientId}&user_role=PATIENT"><c:out
                    value="${appointment.patientId}"/></a></td>
            <td><a href="/pharmacy?command=search_user&user_id=${appointment.doctorId}&user_role=DOCTOR"><c:out
                    value="${appointment.doctorId}"/></a></td>
            <td><c:out value="${appointment.dateTime}"/></td>
            <td><c:out value="${appointment.info}"/></td>
            <td><c:out value="${appointment.appointmentStatus}"/></td>
            <td><a href="/pharmacy?command=go_to_appointment_page&appointment_id=${appointment.id}">See</a>
        </tr>
    </c:forEach>
</table>
</body>
</html>

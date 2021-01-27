<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 11.01.2021
  Time: 17:37
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
            <td><c:out value="${window.id}"/></td>
            <td><a href="/pharmacy?command=go_to_doctor_page&doctor_id=${window.doctorId}"><c:out value="${window.doctorId}"/></a></td>
            <td><c:out value="${window.dateTime}"/></td>
            <td><c:out value="${window.status}"/></td>
            <a href="/pharmacy?command=show_hidden_scope&url=/pharmacy?command=go_to_appointment_window_page&window_id=${window.id}"><c:if
                test="${sessionScope.user_role == 'DOCTOR'}">Change status</c:if></a></td>
        </tr>
${sessionScope.Message}
${sessionScope.Error}
<c:if test="${sessionScope.showHiddenScope==true}">
        <form action="${pageContext.request.contextPath}/pharmacy" method="POST">
            <input type="hidden" name="command" value="update_window">
            <input type="hidden" name="window_id" value="${window.id}">
            <input type="hidden" name="window_doctor" value="${window.doctorId}">
            <input type="hidden" name="window_date" value="${window.dateTime}">
            Status <input type="search" name="window_status" list="status" placeholder="status">
            <datalist id="status">
                <option value="BUSY" label="BUSY">
                <option value="FREE" label="FREE">
            </datalist>
            <input type="submit" value="Submit"/>
        </form>
</c:if>
</body>
</html>

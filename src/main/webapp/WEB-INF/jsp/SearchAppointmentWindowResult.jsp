<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 11.01.2021
  Time: 17:33
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
    <c:forEach items="${Window}" var="window">
        <tr>
            <td><c:out value="${window.id}"/></td>
            <td><c:out value="${window.doctorId}"/></td>
            <td><c:out value="${window.dateTime}"/></td>
            <td><c:out value="${window.status}"/></td>
            <td><a href="/pharmacy?command=go_to_appointment_window_page&window_id=${window.id}">See</a>
        </tr>
    </c:forEach>
</table>
</body>
</html>

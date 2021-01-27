<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 11.01.2021
  Time: 23:46
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
    <c:forEach items="${Payment}" var="payment">
        <tr>
            <td><c:out value="${payment.id}"/></td>
            <td><c:out value="${payment.sum}"/></td>
            <td><c:out value="${payment.IBAN}"/></td>
            <td><c:out value="${recipe.dateTime}"/></td>
            <td><a href="/pharmacy?command=go_to_payment_page&payment_id=${payment.id}">See</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

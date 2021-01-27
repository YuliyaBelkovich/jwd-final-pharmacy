<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 11.01.2021
  Time: 19:31
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
    <c:forEach items="${bankAccount}" var="bankAccount">
        <tr>
            <td><c:out value="${bankAccount.id}"/></td>
            <td><c:out value="${bankAccount.patientId}"/></td>
            <td><c:out value="${bankAccount.IBAN}"/></td>
            <td><c:out value="${bankAccount.cardHolder}"/></td>
            <td><a href="/pharmacy?command=go_to_bank_account_page&bank_account_id=${bankAccount.id}">See</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

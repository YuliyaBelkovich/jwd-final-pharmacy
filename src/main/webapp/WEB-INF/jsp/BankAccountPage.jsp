<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 11.01.2021
  Time: 19:34
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
        <td><c:out value="${bankAccount.id}"/></td>
        <td><a href="/pharmacy?command=search_user&user_id=${bankAccount.patientId}&user_role=PATIENT"><c:out value="${bankAccount.patientId}"/></a></td>
        <td><c:out value="${bankAccount.IBAN}"/></td>
        <td><c:out value="${bankAccount.cardHolder}"/></td>
    </tr>
</body>
</html>

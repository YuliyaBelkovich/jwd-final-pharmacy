<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 11.01.2021
  Time: 23:47
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
        <td><c:out value="${request.id}"/></td>
        <td><a href="/pharmacy?command=go_to_recipe_page&recipe_id=${request.recipeId}"><c:out value="${request.recipeId}"/></a></td>
        <td><a href="/pharmacy?command=go_to_doctor_page&doctor_id=${request.doctorId}"><c:out value="${request.doctorId}"/></a></td>
        <td><c:out value="${request.status}"/></td>
        <a href="/pharmacy?command=show_hidden_scope&url=/pharmacy?command=go_to_request_page&request_id=${request.id}">
            <c:if test="${sessionScope.user_role == 'DOCTOR'}">Confirm request</c:if></a></td>
    </tr>
${sessionScope.Message}
${sessionScope.Error}
<c:if test="${sessionScope.showHiddenScope==true}">
    <form action="${pageContext.request.contextPath}/pharmacy" method="POST">
        <input type="hidden" name="command" value="update_request">
        <input type="hidden" name="request_id" value="${request.id}">
        <input type="hidden" name="request_recipe" value="${request.recipeId}">
        Status:<input type="search" name="request_status" list="status">
        <datalist id="status">
            <option value="PENDING" label="PENDING">
            <option value="REJECTED" label="REJECTED">
            <option value="CONFIRMED" label="CONFIRMED">
        </datalist>
        <input type="submit" value="Submit"/>
    </form>
</c:if>
</body>
</html>

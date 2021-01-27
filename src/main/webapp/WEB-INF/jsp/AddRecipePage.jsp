<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 12.01.2021
  Time: 23:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${sssionScope.Error}
${sessionScope.Message}
<form action="${pageContext.request.contextPath}/pharmacy" method="POST">
    <input type="hidden" name="command" value="add_recipe">
    Patient id: <input type="number" name="recipe_patient">
    <br/>
    Doctor id:<input type="number" name="recipe_doctor">
    <br/>
    Medicine id:<input type="number" name="recipe_medicine">
    <br/>
    Dose:<input type="number" step="0.01" name="recipe_doctor">
    <br/>
    Duration:<input type="number" name="recipe_duration">
    <input type="submit" value="Submit"/>
</form>
</body>
</html>

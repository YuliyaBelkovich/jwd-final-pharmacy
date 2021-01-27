<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 13.01.2021
  Time: 0:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${sessionScope.Error}
${sessionScope.Message}
<form action="${pageContext.request.contextPath}/pharmacy" method="POST">
    <input type="hidden" name="command" value="add_request">
    Recipe id: <input type="number" name="request_recipe">
    <input type="submit" value="Submit"/>
</form>
</body>
</html>

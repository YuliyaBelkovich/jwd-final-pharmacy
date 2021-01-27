<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 11.01.2021
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${requestScope.Error}
${requestScope.Message}
<form action="${pageContext.request.contextPath}/pharmacy" method="GET">
<input type="hidden" name="command" value="search_window">
ID: <input type="number" name="window_id">
<br/>
Doctor id:<input type="number" name="window_doctor">
<br/>
Date and time <input type="datetime-local" name="window_date">
<br/>
Status <input type="search" name="window_status" list="status" placeholder="status">
<datalist id="status">
    <option value="BUSY" label="BUSY">
    <option value="FREE" label="FREE">
</datalist>
<input type="submit" value="Submit"/>
</form>
</body>
</html>

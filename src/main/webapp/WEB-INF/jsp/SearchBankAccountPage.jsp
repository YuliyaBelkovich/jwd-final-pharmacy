<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 11.01.2021
  Time: 19:18
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
    <input type="hidden" name="command" value="search_bank_account">
    ID: <input type="number" name="bank_account_id">
    <br/>
    Patient id:<input type="number" name="bank_account_patient">
    <br/>
    IBAN <input type="text" name="bank_account_iban">
    <br/>
    Card holder <input type="text" name="bank_account_holder">
    <input type="submit" value="Submit"/>
</form>
</body>
</html>

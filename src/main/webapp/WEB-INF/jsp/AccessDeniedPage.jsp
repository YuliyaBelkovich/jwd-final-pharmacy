<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 14.01.2021
  Time: 0:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="${sessionScope.rb}" var="rb"/>
<html>
  <head>
    <title><fmt:message key="error.access.header" bundle="${rb}"/> </title>
  </head>
  <body>

  <c:import url="MainHeader.jsp"/>
  <h1><fmt:message key="error.access.message" bundle="${rb}"/></h1>
  </body>
</html>

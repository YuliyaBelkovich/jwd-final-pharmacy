<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 04.01.2021
  Time: 23:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="${sessionScope.rb}" var="rb"/>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">


    <title><fmt:message key="registration.header" bundle="${rb}"/></title>
</head>
<body>
<c:import url="MainHeader.jsp"/>
<div class="p-3 mb-2 bg-light text-black" class="needs-validation" novalidate>
    <form action="${pageContext.request.contextPath}/pharmacy" method="POST">
        <input type="hidden" name="command" value="register">
        <div class="form-group">
            <label for="exampleInputEmail1"><fmt:message key="registration.email" bundle="${rb}"/></label>
            <input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="<fmt:message key="registration.email.placeholder" bundle="${rb}"/>" required>
            <small id="emailHelp" class="form-text text-muted"><fmt:message key="login.email.info" bundle="${rb}"/></small>
        </div>
        <div class="form-check-inline">
            <input class="form-check-input" type="radio" name="user_role" id="exampleRadios1" value="DOCTOR" checked required>
            <label class="form-check-label" for="exampleRadios1">
                <fmt:message key="registration.doctor" bundle="${rb}"/>
            </label>
        </div>
        <div class="form-check-inline">
            <input class="form-check-input" type="radio" name="user_role" id="exampleRadios2" value="PATIENT" required>
            <label class="form-check-label" for="exampleRadios2">
                <fmt:message key="registration.patient" bundle="${rb}"/>
            </label>
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1"><fmt:message key="login.password" bundle="${rb}"/></label>
            <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="<fmt:message key="login.password" bundle="${rb}"/>" required>
        </div>
        <div class="form-group">
            <label for="exampleInputPassword2"><fmt:message key="registration.password.repeat" bundle="${rb}"/></label>
            <input type="password" name="confirmed_password" class="form-control" id="exampleInputPassword2" placeholder="<fmt:message key="registration.password.repeat" bundle="${rb}"/>" required>
        </div>
        <div class="form-group">
            <label for="userName"><fmt:message key="registration.name" bundle="${rb}"/></label>
            <input type="text" name="user_name" class="form-control" id="userName" placeholder="<fmt:message key="registration.name" bundle="${rb}"/>" required>
        </div>
        <p class="text-danger">${sessionScope.Error}</p>
        <button type="submit" class="btn btn-primary"><fmt:message key="registration.register" bundle="${rb}"/></button>
    </form>
    <script>
        (function () {
            'use strict'

            var forms = document.querySelectorAll('.needs-validation')

            // Loop over them and prevent submission
            Array.prototype.slice.call(forms)
                .forEach(function (form) {
                    form.addEventListener('submit', function (event) {
                        if (!form.checkValidity()) {
                            event.preventDefault()
                            event.stopPropagation()
                        }

                        form.classList.add('was-validated')
                    }, false)
                })
        })()
    </script>
</div>
</body>
</html>

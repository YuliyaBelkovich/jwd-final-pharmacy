<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 04.01.2021
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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


    <title><fmt:message key="login.header" bundle="${rb}"/> </title>
</head>
<body>
<c:import url="MainHeader.jsp"/>
<p class="text-info">${param.message}</p>
<div class="p-3 mb-2 bg-light text-black">
    <form action="${pageContext.request.contextPath}/pharmacy" method="POST" class="needs-validation" novalidate>
        <input type="hidden" name="command" value="log_in">
        <div class="form-group">
            <label for="exampleInputEmail1" class="form-label"><fmt:message key="login.email" bundle="${rb}"/> </label>
            <input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
                   placeholder="<fmt:message key="login.email.placeholder" bundle="${rb}"/> " required>
            <small id="emailHelp" class="form-text text-muted"><fmt:message key="login.email.info" bundle="${rb}"/> </small>
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1" class="form-label"><fmt:message key="login.password" bundle="${rb}"/> </label>
            <input type="password" name="password" class="form-control" id="exampleInputPassword1"
                   placeholder="<fmt:message key="login.password" bundle="${rb}"/> " required>
        </div>
        <p class="text-danger">${param.error}</p>

        <button type="submit" class="btn btn-info"><fmt:message key="login.header" bundle="${rb}"/> </button>
    </form>
    <script>
        // Example starter JavaScript for disabling form submissions if there are invalid fields
        (function () {
            'use strict'

            // Fetch all the forms we want to apply custom Bootstrap validation styles to
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

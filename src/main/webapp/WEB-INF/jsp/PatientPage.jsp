<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 30.01.2021
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="${sessionScope.rb}" var="rb"/>
<html>
<head>
    <title><fmt:message key="user.patient.header" bundle="${rb}"/></title>
</head>
<body>
<c:import url="MainHeader.jsp"/>
<div class="row">
    <div class="col-md-4">
        <div>
            <ul class="list-group">
                <small>
                    <label for="result_id">id</label>
                    <li class="list-group-item list-group-item-light" id="result_id"><c:out
                            value="${patient.id}"/></li>
                </small>
                <label for="result_name"><fmt:message key="user.name" bundle="${rb}"/></label>
                    <li class="list-group-item list-group-item-primary" id="result_name"><c:out
                            value="${patient.name}"/></li>
                <label for="result_email"><fmt:message key="user.email" bundle="${rb}"/></label>
                <li class="list-group-item " id="result_email"><c:out value="${patient.email}"/></li>
                <label for="result_status"><fmt:message key="user.status" bundle="${rb}"/></label>
                <li class="list-group-item list-group-item-info" id="result_status"><c:out
                        value="${patient.status}"/></li>
            </ul>
        </div>
    </div>

    <%--                DISPLAY ACTION BUTTONS--%>

    <div class="col-md-2">
        <p class="text-primary">${param.message}</p>
        <p class="text-danger">${param.error}</p>
        <c:if
                test="${sessionScope.user_role == 'PHARMACIST'}">
            <button type="button" onclick="openUpdate()" class="btn btn-warning"><fmt:message key="user.button.update" bundle="${rb}"/></button>
        </c:if>
    </div>

    <%--                    UPDATE DOCTOR STATUS FORM--%>
    <div class="col-md-3">
        <small>
            <div class="d-none" id="updateStatusForm">
                <form action="${pageContext.request.contextPath}/pharmacy" method="POST" class="needs_validation" novalidate>
                    <input type="hidden" name="command" value="update_user" required>
                    <input type="hidden" name="user_id" value="${patient.id}" required>
                    <input type="hidden" name="user_name" value="${patient.name}" required>
                    <input type="hidden" name="user_email" value="${patient.email}" required>
                    <input type="hidden" name="user_role" value="PATIENT" required>
                    <div class="form-group">
                        <label for="update_status_user"><fmt:message key="user.status" bundle="${rb}"/></label>
                        <input type="search" name="user_status" class="form-control" list="updateStatus"
                               id="update_status_user"
                               placeholder="<fmt:message key="user.status.placeholder" bundle="${rb}"/>" required>
                    </div>
                    <datalist id="updateStatus">
                        <option value="ACTIVE" label="ACTIVE">
                        <option value="BANNED" label="BANNED">
                        <option value="PENDING" label="PENDING">
                    </datalist>
                    <button type="submit" class="btn btn-success"><fmt:message key="user.button.update" bundle="${rb}"/></button>
                </form>
                <small>
                    <button onclick="closeUpdate()" class="btn btn-danger"><fmt:message key="user.button.close" bundle="${rb}"/></button>
                </small>
            </div>
        </small>
    </div>
</div>
<script>
    function openUpdate() {
        document.getElementById("updateStatusForm").classList.replace("d-none", "d-block");
    }

    function closeUpdate() {
        document.getElementById("updateStatusForm").classList.replace("d-block", "d-none");
    }
</script>
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
</body>
</html>


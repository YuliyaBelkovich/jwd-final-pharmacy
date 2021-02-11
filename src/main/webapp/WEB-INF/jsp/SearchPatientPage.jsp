<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 11.01.2021
  Time: 12:00
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


    <title><fmt:message key="search.patient" bundle="${rb}"/> </title>
</head>
<body>
<c:import url="MainHeader.jsp"/>
<%--FIRST COLUMN - SEARCH PATIENT FORM--%>

<div class="row">
    <div class="col-md-3">
        <form action="${pageContext.request.contextPath}/pharmacy" method="GET">
            <input type="hidden" name="command" value="search_user">
            <input type="hidden" name="user_role" value="PATIENT">
            <div class="form-group">
                <label for="id">id</label>
                <input type="number" name="user_id" min="0" step="1" class="form-control" id="id"
                       placeholder="<fmt:message key="user.id.placeholder" bundle="${rb}"/>">
            </div>
            <div class="form-group">
                <label for="email"><fmt:message key="user.email" bundle="${rb}"/></label>
                <input type="text" name="user_email" class="form-control" id="email"
                       placeholder="<fmt:message key="user.email.placeholder" bundle="${rb}"/>">
            </div>
            <div class="form-group">
                <label for="status_user"><fmt:message key="user.status" bundle="${rb}"/></label>
                <input type="search" name="user_status" class="form-control" list="status" id="status_user"
                       placeholder="<fmt:message key="user.status.placeholder" bundle="${rb}"/>">
            </div>
            <datalist id="status">
                <option value="ACTIVE" label="ACTIVE">
                <option value="BANNED" label="BANNED">
                <option value="PENDING" label="PENDING">
            </datalist>
            <button type="submit" class="btn btn-success"><fmt:message key="search.find" bundle="${rb}"/></button>
        </form>
    </div>
    <div class="col-md-9">
        <p class="text-danger">${requestScope.Error}</p>
        <p class="text-info">${requestScope.Message}</p>
        <p class="text-primary">${param.message}</p>
        <p class="text-danger">${param.error}</p>
        <div class="row">
            <c:forEach items="${Patient}" var="patient">

                <%--            DISPLAY SEARCH RESULT--%>

                <div class="col-md-4">
                    <div>
                        <ul class="list-group">
                            <small>
                                <label for="result_id">id</label>
                                <li class="list-group-item list-group-item-light" id="result_id"><c:out
                                        value="${patient.id}"/></li>
                            </small>
                            <label for="result_name"><fmt:message key="user.name" bundle="${rb}"/></label>
                            <a href="/pharmacy?command=go_to_patient_page&patient_id=${patient.id}">
                                <li class="list-group-item list-group-item-primary" id="result_name"><c:out
                                        value="${patient.name}"/></li>
                            </a>
                            <label for="result_email"><fmt:message key="user.email" bundle="${rb}"/></label>
                            <li class="list-group-item" id="result_email"><c:out value="${patient.email}"/></li>
                            <label for="result_status"><fmt:message key="user.status" bundle="${rb}"/></label>
                            <li class="list-group-item list-group-item-info" id="result_status"><c:out
                                    value="${patient.status}"/></li>
                            <li class="list-group-item">...</li>
                        </ul>
                    </div>
                </div>

                <%--                DISPLAY ACTION BUTTONS--%>

                <div class="col-md-2">
                    <c:if
                            test="${sessionScope.user_role == 'PHARMACIST'}">
                        <button type="button" onclick="openUpdate(${patient.id})" class="btn btn-warning"><fmt:message key="user.button.update" bundle="${rb}"/></button>
                    </c:if>
                </div>
                <%--                    UPDATE PATIENT STATUS FORM--%>
                <div class="col-md-3">
                    <small>
                        <div class="d-none" id="updateStatusForm${patient.id}">
                            <form action="${pageContext.request.contextPath}/pharmacy" method="POST">
                                <input type="hidden" name="command" value="update_user">
                                <input type="hidden" name="user_id" value="${patient.id}">
                                <input type="hidden" name="user_name" value="${patient.name}">
                                <input type="hidden" name="user_email" value="${patient.email}">
                                <input type="hidden" name="user_password" value="${patient.password}">
                                <input type="hidden" name="user_role" value="PATIENT">
                                <div class="form-group">
                                    <label for="update_status_user"><fmt:message key="user.status" bundle="${rb}"/></label>
                                    <input type="search" name="user_status" class="form-control" list="updateStatus"
                                           id="update_status_user"
                                           placeholder="<fmt:message key="user.status.placeholder" bundle="${rb}"/>">
                                </div>
                                <datalist id="updateStatus">
                                    <option value="ACTIVE" label="ACTIVE">
                                    <option value="BANNED" label="BANNED">
                                    <option value="PENDING" label="PENDING">
                                </datalist>
                                <p>  </p>
                                <button type="submit" class="btn btn-success"><fmt:message key="user.button.update" bundle="${rb}"/></button>
                            </form>
                            <small>
                                <button onclick="closeUpdate(${patient.id})" class="btn btn-danger"><fmt:message key="user.button.close" bundle="${rb}"/></button>
                            </small>
                        </div>
                    </small>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<script>
    function openUpdate(id) {
        document.getElementById("updateStatusForm"+id).classList.replace("d-none", "d-block");
    }

    function closeUpdate(id) {
        document.getElementById("updateStatusForm"+id).classList.replace("d-block", "d-none");
    }
</script>
</body>
</html>

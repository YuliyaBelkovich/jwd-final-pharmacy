<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 21.01.2021
  Time: 15:11
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


    <title><fmt:message key="payment.header" bundle="${rb}"/></title>
</head>
<body>
<c:import url="MainHeader.jsp"/>

<div class="row">
    <div class="col-md-4">
        <c:if test="${sessionScope.user_role=='PATIENT'}">
            <div>
                <a href="/pharmacy?command=search_bank_account&bank_account_patient=${sessionScope.user_id}">
                    <button type="button" class="btn btn-info"><fmt:message key="payment.button.chooseBank" bundle="${rb}"/></button>
                </a>

            </div>
        </c:if>
        <div>
            <c:forEach items="${requestScope.BankAccount}" var="bankAccount">
                <div>
                    <p class="text-primary"><c:out value="${bankAccount.IBAN}"/>
                        <a href="/pharmacy?command=add_payment&payment_sum=${sessionScope.order.price}&payment_iban=${bankAccount.IBAN}">
                            <button type="button" class="btn btn-warning"><fmt:message key="payment.button.submit" bundle="${rb}"/></button>
                        </a>
                    </p>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="col-md-4">
        <p class="text-danger">${requestScope.Error}</p>
        <p class="text-info">${requestScope.Message}</p>
        <p class="text-danger">${param.error}</p>
        <p class="text-info">${param.message}</p>
        <h2><p class="text-primary"><fmt:message key="payment.total" bundle="${rb}"/> ${sessionScope.order.price} $</p></h2>
        <form action="${pageContext.request.contextPath}/pharmacy" method="POST">
            <input type="hidden" name="command" value="add_payment">
            <input type="hidden" name="payment_sum" value="${sessionScope.order.price}">
            <div class="form-group">
                <label for="iban"><fmt:message key="payment.pay.IBAN" bundle="${rb}"/></label>
                <input type="text" name="payment_iban" class="form-control" id="iban">
            </div>
            <div class="form-group">
                <label for="card_holder"><fmt:message key="payment.pay.holder" bundle="${rb}"/></label>
                <input type="text" name="payment_card_holder" pattern="^((?:[A-Za-z]+ ?){1,3})$" class="form-control" id="card_holder">
            </div>
            <div class="form-group">
                <label for="expiry"><fmt:message key="payment.pay.expiry" bundle="${rb}"/></label>
                <input type="text" name="payment_expiry" pattern="(?:0[1-9]|1[0-2])/[0-9]{2}" class="form-control" id="expiry">
            </div>
            <div class="form-group">
                <label for="cvv"><fmt:message key="payment.pay.cvv" bundle="${rb}"/></label>
                <input type="text" name="payment_cvv" pattern="\d{3}" class="form-control" id="cvv">
            </div>
            <button type="submit" class="btn btn-success"><fmt:message key="payment.button.submit" bundle="${rb}"/></button>
        </form>
    </div>
</div>
</body>
</html>

<%@ page import="by.kolbun.andersen.blogic.entity.AccountStatus" %>
<%@ page import="by.kolbun.andersen.blogic.entity.TranshStatus" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Account Info</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/acc_info.css"/>
</head>
<body>
<c:set var="ACTIVE" value="<%=AccountStatus.ACTIVE%>"/>
<c:set var="BLOCKED" value="<%=AccountStatus.BLOCKED%>"/>
<c:set var="SUCCESS" value="<%=TranshStatus.SUCCESS%>"/>
<c:set var="FAIL" value="<%=TranshStatus.FAIL%>"/>
<c:set var="DENIED" value="<%=TranshStatus.DENIED_NOTENAUGHMONEY%>"/>
<div class="outer">
    <table class="table_blur">
        <tr
                <c:if test="${account.status == BLOCKED}">class="blocked"</c:if> >
            <th>Account ID:</th>
            <th>${account.id}</th>
        </tr>
        <tr>
            <td colspan="2">${account.user.firstName} ${account.user.lastName}</td>
        </tr>
        <tr>
            <td colspan="2"><fmt:formatNumber value="${a.money}" type="currency" currencySymbol="$"/></td>
        </tr>
        <tr>
            <td colspan="2">Transactions:</td>
        </tr>
    </table>
    <br/>
    <table class="table_blur">
        <c:forEach var="t" items="${transhes}">
            <tr <c:if test="${t.status == DENIED}">class="blocked"</c:if>>
                <td>${t.id}</td>
                <td>${t.sender.id}</td>
                <td>${t.receiver.id}</td>
                <td>${t.amount}</td>
                <td>${t.status}</td>
                <td><fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${t.date}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

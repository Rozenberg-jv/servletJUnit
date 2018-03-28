<%@ page import="by.kolbun.andersen.blogic.entity.AccountStatus" %>
<%@ page import="by.kolbun.andersen.blogic.entity.TranshStatus" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f" %>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Accounts</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/accounts.css"/>
    <%--<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js" type="text/javascript"></script>--%>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js" type="text/javascript"></script>
    <%--<script src="${pageContext.request.contextPath}/js/blocked.js" type="text/javascript"></script>--%>
    <script src="${pageContext.request.contextPath}/js/hide.js" type="text/javascript"></script>
</head>
<body>
<c:set var="DENIED" value="<%=TranshStatus.DENIED_NOTENAUGHMONEY%>"/>
<div class="outer">
    <div class="inner">
        <table>
            <thead>
            <tr>
                <th>User Id</th>
                <th>Name of User</th>
                <th>Status</th>
                <th>Money</th>
                <th width="80px">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:set var="ACTIVE" value="<%=AccountStatus.ACTIVE%>"/>
            <c:set var="BLOCKED" value="<%=AccountStatus.BLOCKED%>"/>

            <c:forEach items="${accounts}" var="a">
                <%--<tr
                        <c:if test="${a.status == BLOCKED}">id="blocked"</c:if> >--%>

                <tr <c:if test="${a.status == BLOCKED}" var="block">class="blocked"</c:if>>
                    <td>${a.id}</td>
                    <td><p>${a.user.firstName} ${a.user.lastName}</p></td>
                    <td><a href="${pageContext.request.contextPath}/account?act=block&id=${a.id}"
                           class="button">SWITCH</a>
                    </td>
                    <td><fmt:formatNumber value="${a.money}" type="currency" currencySymbol="$"/></td>
                    <td><a href="${pageContext.request.contextPath}/account/single?id=${a.id}" class="button">Account
                        info</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="${pageContext.request.contextPath}/account/new" class="button">Add new account</a>
    </div>
    <div>
        <form action="${pageContext.request.contextPath}/account" method="post">
            <table>
                <tr>
                    <td>Sender ID</td>
                    <td>Receiver ID</td>
                    <td>Amount to send</td>
                </tr>
                <tr>
                    <td><input type="text" name="sender" value="" title="~from~"/></td>
                    <td><input type="text" name="receiver" value="" title="~to~"/></td>
                    <%--<td><input name="money" value="" pattern="^[0-9]+$" title="Initial money amount"/></td>--%>
                    <td><input type="number" min="0" name="money" title="~amount~"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td align="center" class="button">
                        <input id="submit" type="submit" value="Do transaction"/>
                    </td>
                    <td></td>
                </tr>
            </table>
        </form>
    </div>
    <%-- --%>

    <%----%>
    <div class="inner">
        <button id="hide" class="button">Transactions</button>
        <div class="hiddenlist">
            <table class="table_blur">
                <tr>
                    <th>id</th>
                    <th>from</th>
                    <th>to</th>
                    <th>amount</th>
                    <th>status</th>
                    <th>date</th>
                </tr>
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
    </div>
</div>
</body>
</html>
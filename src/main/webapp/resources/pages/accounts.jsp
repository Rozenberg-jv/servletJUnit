<%@ page import="by.kolbun.andersen.blogic.entity.AccountStatus" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Accounts</title>
    <link rel="stylesheet" type="text/css" href="resources/static/css/accounts.css"/>
</head>
<body>
<div class="outer">
    <div class="inner">
        <table class="otable">
            <thead>
            <tr>
                <th>User Id</th>
                <th>Name of User</th>
                <th>Status</th>
                <th>Money</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:set var="ACTIVE" value="<%=AccountStatus.ACTIVE%>"/>
            <c:set var="BLOCKED" value="<%=AccountStatus.BLOCKED%>"/>

            <c:forEach items="${accounts}" var="a">
                <tr
                        <c:if test="${a.status == BLOCKED}">id="blocked"</c:if> >
                    <td>${a.id}</td>
                    <td>${a.user.firstName} ${a.user.lastName}</td>
                    <td><a href="${pageContext.request.contextPath}/a?act=block&id=${a.id}" class="button">SWITCH</a>
                    </td>

                    <td><fmt:formatNumber value="${a.money}" type="currency" currencySymbol="$"/></td>
                        <%--<td>
                            <c:set value="${f:substring(a.money,f:length(a.money)-2,2)}" var="cents"/>
                            <c:set value="${f:substring(a.money, 0, f:length(a.money))}" var="dolrs"/>
                                ${dolrs+"."+cents}
                        </td>--%>

                    <td><a href="${pageContext.request.contextPath}/a/s?id=${a.id}" class="button">Account info</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <%--<br/>
        <p>
            <c:if test="${empty message}">
        <p id="message">${message}</p>
        </c:if>
        </p>
        <br/>--%>
        <a href="${pageContext.request.contextPath}/a/new" class="button">Add new account</a>
    </div>
    <%----%>
    <div class="inner">
        <form action="${pageContext.request.contextPath}/a" method="post">
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
                    <td colspan="3" align="center" class="button">
                        <input id="submit" type="submit" value="Do transaction"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
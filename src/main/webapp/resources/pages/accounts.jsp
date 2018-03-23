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
<table>
    <thead>
    <tr>
        <th width="10em">User Id</th>
        <th width="140em">Name of User</th>
        <th width="80px">Status</th>
        <th width="60em">Money</th>
        <th colspan=2>Action</th>
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
            <td><a href="${pageContext.request.contextPath}/a?act=block&id=${a.id}" class="button">SWITCH</a></td>

            <td><fmt:formatNumber value="${a.money}" type="currency" currencySymbol="$"/></td>
                <%--<td>
                    <c:set value="${f:substring(a.money,f:length(a.money)-2,2)}" var="cents"/>
                    <c:set value="${f:substring(a.money, 0, f:length(a.money))}" var="dolrs"/>
                        ${dolrs+"."+cents}
                </td>--%>

            <td><a href="${pageContext.request.contextPath}/a/s?id=${a.id}" class="button">Account info</a></td>
            <td></td>
                <%--<td><a href="AccountController?action=edit&userId=<c:out value="${a.userid}"/>">Info</a></td>--%>
                <%--<td><a href="AccountController?action=delete&userId=<c:out value="${a.userid}"/>">Block/Unblock</a></td>--%>
                <%--<td><a href="AccountController?action=edit&userId=<c:out value="${a.userid}"/>">Update</a></td>--%>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br/>
<c:if test="${empty message}">
    <p id="message">${message}</p>
</c:if>
<br/>
<a href="${pageContext.request.contextPath}/a/new" class="button">Add new account</a>
<div>?act=add&f=&l=&m=</div>
<div>?act=del&id=</div>
<div>?act=get&id=</div>
<div>?act=all</div>
<div>?act=transh&sen=&rec=&m=</div>
<div>?act=fill</div>
<div>?act=block&id=</div>
<%--<p><a href="UserController?action=insert">Add User</a></p>--%>
</body>
</html>
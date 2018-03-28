<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New Account</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/accounts.css"/>
</head>
<body>
<form action="${pageContext.request.contextPath}/account/new" method="post">
    <div class="outer">
        <div>
            <table>
                <tr>
                    <td>First Name</td>
                    <td>Last Name</td>
                    <td>Initial Money</td>
                </tr>
                <tr>
                    <td><input type="text" name="fname" value="" title="User first name"/></td>
                    <td><input type="text" name="lname" value="" title="User last name"/></td>
                    <%--<td><input name="money" value="" pattern="^[0-9]+$" title="Initial money amount"/></td>--%>
                    <td><input type="number" min="0" name="money" title="Initial money amount"/></td>
                </tr>
            </table>
        </div>
        <div>
            <input id="submit" type="submit" value="Add"/>
        </div>
    </div>
</form>
</body>
</html>

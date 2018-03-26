<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>User Update</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/accounts.css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div class="outer">
    <form action="${pageContext.request.contextPath}/a/u?id=${id}" method="post">
        <div>
            <table>
                <tr>
                    <td>First Name</td>
                    <td>Last Name</td>
                </tr>
                <tr>
                    <td><input type="text" name="fname" value="" title="User first name"/></td>
                    <td><input type="text" name="lname" value="" title="User last name"/></td>
                </tr>
                <input type="hidden" value="${id}"/>
            </table>
        </div>
        <div>
            <input id="submit" type="submit" value="Update"/>
        </div>
    </form>
</div>
</body>
</html>

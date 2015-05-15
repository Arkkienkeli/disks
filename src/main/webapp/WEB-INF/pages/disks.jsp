<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
</head>
<body>

<c:url var="logoutUrl" value="/logout"/>
<form class="form-inline" action="${logoutUrl}" method="post">
    <input type="submit" value="Log out" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>


<div align="center">
    <table border="1 px">
        <c:forEach var="disk" items="${disks}" varStatus="status">
            <tr>
                <c:set var="diskFormId" value="disk${status.index}"/>
                <td>${disk.title}</td>
                <td>
                    <form id="${diskFormId}" action="disks/take" method="POST">
                        <input id="id" name="id" type="hidden" value="${disk.id}"/>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button type="submit">Взять</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>


</body>
</html>
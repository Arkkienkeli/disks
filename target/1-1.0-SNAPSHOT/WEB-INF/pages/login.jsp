
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
  Created by IntelliJ IDEA.
  User: Arkkienkeli
  Date: 06.02.2015
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<body>

<c:if test="${not empty param.error}">
  <font color="red"> <spring:message code="label.loginerror" />
    : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} </font>
</c:if>
<form method="POST" action="<c:url value="/j_spring_security_check" />">
<table>
  <tr>
    <td align="right"><spring:message code="label.login" /></td>
    <td><input type="text" name="j_username" /></td>
  </tr>
  <tr>
    <td align="right"><spring:message code="label.password" /></td>
    <td><input type="password" name="j_password" /></td>
  </tr>
  <tr>
    <td align="right"><spring:message code="label.remember" /></td>
    <td><input type="checkbox" name="_spring_security_remember_me" /></td>
  </tr>
  <tr>
    <td colspan="2" align="right"><input type="submit" value="Login" />
      <input type="reset" value="Reset" /></td>
  </tr>
</table>
</form>
</body>
</body>
</html>

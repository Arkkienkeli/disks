<%--
  Created by IntelliJ IDEA.
  User: Arkkienkeli
  Date: 13.02.2015
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title></title>
</head>

<body>
<c:url var="logoutUrl" value="/logout"/>
<form class="form-inline" action="${logoutUrl}" method="post">
  <input type="submit" value="Log out" />
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
<p>disks that I took</p>
<div align="center">
  <table border="1 px">
    <c:forEach var="td" items="${tds}" varStatus="status">
      <tr>
        <c:set var="diskFormId" value="disk${status.index}"/>
        <td>${status.index}</td>
        <td>${td.disk.title}</td>
        <td>
          <form id="${diskFormId}" action="mytd/return" method="POST">
            <input id="id" name="id" type="hidden" value="${td.disk.id}"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit">Отдать</button>
          </form>
        </td>
      </tr>
    </c:forEach>
  </table>
</div>
</body>
</html>

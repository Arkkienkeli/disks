<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Home</title>
</head>
<body>
<p>disks that were taken from me</p>
<div align="center">
  <table border="1 px">
    <c:forEach var="td" items="${tds}" varStatus="status">
      <tr>
        <td>${td.user.username}</td>
        <td>${td.disk.title}</td>
      </tr>
    </c:forEach>
  </table>
</div>




</body>
</html>
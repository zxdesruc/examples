<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<spring:message code="select.option.submit" var="submitValue"/>
<html>
<head>
    <title><spring:message code="user.create.title"/></title>
</head>
<body>
<b><spring:message code="user.create.headline"/></b>
<form method="post" action="/createUser">
    <br>
    <label><spring:message code="user.create.name"/></label>
    <input type="text" name="name"/>
    <br>
    <label><spring:message code="user.create.age"/></label>
    <input type="text" name="age"/>
    <input type="submit" name=${submitValue}>
</form>
</body>
</html>

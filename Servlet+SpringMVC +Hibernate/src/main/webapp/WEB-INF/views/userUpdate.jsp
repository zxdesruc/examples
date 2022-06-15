<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<spring:message code="select.option.submit" var="submitValue"/>
<html>
<head>
    <title><spring:message code="user.update.title"/></title>
</head>
<body>
<b><spring:message code="user.update.headline"/></b>
<form method="post" action="/userUpdate">
    <spring:message code="user.update.headline"/>
    <br>
    <label><spring:message code="user.update.userId"/></label>
    <input name="id" type="text">
    <br>
    <label><spring:message code="user.update.newName"/></label>
    <input name="name" type="text">
    <br>
    <label><spring:message code="user.update.newAge"/></label>
    <input name="age" type="text">
    <input type="submit" name=${submitValue}>
</form>
</body>
</html>

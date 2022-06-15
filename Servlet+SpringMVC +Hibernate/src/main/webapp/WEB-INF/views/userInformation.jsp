<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<spring:message code="select.option.submit" var="submitValue"/>
<html>
<head>
    <title><spring:message code="user.info.title"/></title>
</head>
<body>
<h1><spring:message code="user.info.headline"/></h1>
<form action="/getUser">
    <br/><spring:message code="user.info.inputId"/>
    <input name="userId" type="text">
    <input type="submit" name=${submitValue}>;
</form>
</body>
</html>

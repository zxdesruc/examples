<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Home Page</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
    <style>
        table,th,td
        {
            border:1px solid black;
        }

    </style>
</head>
<body text="red">
<h3>c:When example:</h3>                            <IMG src="img/1621241379_13.jpg" align="right"width="357"
                                                          height="335">
<c:set var = "salary" scope = "session" value = "${2000*2}"/>
<p>Your salary is : <c:out value = "${salary}"/></p>
<c:choose>

<c:when test = "${salary <= 500}">
    Salary is very low to survive.
</c:when>

<c:when test = "${salary > 1000}">
    Salary is very good.
</c:when>
</c:choose>
<h3>c:Remove</h3>
<c:set var = "salary" scope = "session" value = "${2000*2}"/>
<p>Before Remove Value: <c:out value = "${salary}"/></p>
<c:remove var = "salary"/>
<p>After Remove Value: <c:out value = "${salary}"/></p>
<br><br>
<h3>SQL weather example:</h3>
<sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
                   url = "jdbc:mysql://localhost/sys"
                   user = "root"  password = "root"/>

<sql:query dataSource = "${snapshot}" var = "result">
    SELECT * from weather;
</sql:query>

<table border = "1" width = "100%">
    <tr>
        <th>Id</th>
        <th>Weather type</th>
        <th>Temperature</th>
    </tr>

    <c:forEach var = "row" items = "${result.rows}">
        <tr>
            <td><c:out value = "${row.idweather}"/></td>
            <td><c:out value = "${row.type}"/></td>
            <td><c:out value = "${row.temperature}"/></td>
        </tr>
    </c:forEach>
</table>
<h4>FN examples:</h4>
<c:set var = "string" value = "This is TEST string"/>
String:   <c:out value="${string}"></c:out>
<h3>fn:startsWith example</h3>
<c:if test = "${fn:startsWith(string, 'This')}">
    <p>String starts with This</p>
</c:if>

<br />
<c:if test = "${fn:startsWith(string, 'Test')}">
    <p>String starts with Test</p>
</c:if>
<h3>fn:length example</h3>
<p>Length of String (1) : ${fn:length(string)}</p>
</body>
</html>
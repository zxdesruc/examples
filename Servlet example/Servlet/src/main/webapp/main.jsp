<%@ page language="java"
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
    <meta http-equiv="Content-Type"
          content="text/html; charset=UTF-8">
    <title>   Result   </title>
</head>

<body>
<body bgcolor ="#fffafa" text="#ff1493">
<h2><b>The result is:</b>
    <%=session.getAttribute("res")%> </center><br>
    <br>
    <br>
<i><center>Have a nice day! :)</center></i></h2>
<form>
    <center><input type="button" value="what's there?" onclick="location.href='buter.jsp'" ></center>


</body>

</html>
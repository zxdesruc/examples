<%--
<%@ page language="java"
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
--%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Page</title>
</head>

<body>
<form action="LoginServlet">
 <body bgcolor ="#fffafa" text="#ff1493">
 <h1>
 <center><i>Hello!</i> <br></center>

     </h1>
   <h2>Please select operation
        <select name="operation">
           <option value="1">Average month temperature</option>
           <option value="2">Number of days higher than average</option>
           <option value="3">Number of days lower than average</option>
           <option value="4">3 hottest days</option>
           <option value="5">Show all temperatures</option>
       </select>
<br>

       <input type="submit" value="submit"></h2>
 <img src="sub.jpg" width="500x500" align="top" border="2" hspace="50%" vspace=0%"/>


</form>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: EFDMS
  Date: 2/1/2023
  Time: 4:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>

<jsp:useBean id="form4" class="com.example.demo3.StudentsBean" scope="session"/>
<jsp:setProperty name="form4" property="*"/>

<p> User Name:
  <jsp:getProperty name = "form4" property = "userName"/>
</p>

<p> Password:
  <jsp:getProperty name = "form4" property = "password"/>
</p>

<%--<p>Student Age:--%>
<%--  <jsp:getProperty name = "form4" property = "age"/>--%>
<%--</p>--%>

</body>
</html>
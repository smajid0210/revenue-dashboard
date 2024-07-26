<%--
  Created by IntelliJ IDEA.
  User: Sedna
  Date: 2/2/2023
  Time: 9:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="registrationBean.registerDao"%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--<jsp:useBean id="registerForm" class="registrationBean.registerBean" scope="session"/>--%>
<%--<jsp:setProperty name="registerForm" property="*"/>--%>
<jsp:useBean id="registerForm" class="registrationBean.registerBean" scope="session"/>
<jsp:setProperty name="registerForm" property="*"/>

<%
    boolean status=registerDao.saveDataRegister(registerForm);
    if(status){
        out.println("You are registered successfully");
        session.setAttribute("session","TRUE");
        %>
<jsp:include page="index.jsp"></jsp:include>
<%
    }
    else {
        out.print("Duplicate Userid");

%>
<jsp:include page="registration.jsp"></jsp:include>
<%
    }
%>
%>

<%--<p> Name:--%>
<%--    <jsp:getProperty name = "registerForm" property = "firstName"/> &nbsp--%>

<%--    <jsp:getProperty name = "registerForm" property = "middleName"/>  &nbsp--%>

<%--    <jsp:getProperty name = "registerForm" property = "lastName"/>--%>
<%--</p>--%>

<%--<p> Mobile:--%>
<%--    <jsp:getProperty name = "registerForm" property = "mobile"/>--%>
<%--</p>--%>

<%--<p> Nid:--%>
<%--    <jsp:getProperty name = "registerForm" property = "nid"/>--%>
<%--</p>--%>

<%--<p> Wing:--%>
<%--    <jsp:getProperty name = "registerForm" property = "wing"/>--%>
<%--</p>--%>

<%--<p> Position:--%>
<%--    <jsp:getProperty name = "registerForm" property = "position"/>--%>
<%--</p>--%>

<%--<p> User Name:--%>
<%--    <jsp:getProperty name = "registerForm" property = "userName"/>--%>
<%--</p>--%>

<%--<p> Password:--%>
<%--    <jsp:getProperty name = "registerForm" property = "password"/>--%>
<%--</p>--%>

</body>
</html>

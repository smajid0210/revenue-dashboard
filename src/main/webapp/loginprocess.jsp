
<%@page import="login.bean.LoginDao"%>
<jsp:useBean id="loginobj" class="login.bean.LoginBean"/>
<jsp:setProperty property="*" name="loginobj"/>

<jsp:useBean id="roleTypeobj" class="login.bean.roleBean"/>

<%
//boolean status=LoginDao.validate(loginobj);
String status=LoginDao.validate(loginobj);
if(status!=null){
//out.println("You r successfully logged in");
session.setAttribute("session","TRUE");
}
else
{
//out.print("Sorry, Wrong userid or password");
%>
<%--<script>alert("Sorry, Wrong userid or password..............");--%>
<%--</script>--%>
<jsp:include page="index.jsp"></jsp:include>

<%
}
%>

<%
    String r1= "i";
    String r2= "c";
    String r3= "t";
    String r4= "v";
    String r5= "b";
    String r6= "ch";
//    out.println("revdashboard"+status);
if(r1.equals(status) || r5.equals(status) || r6.equals(status)){
//    out.println("revdashboard");
    %>

<jsp:include page="revdashboard.jsp"></jsp:include>
<%
} else if (r2.equals(status)) {
//    out.println("dashBoardCustom");
%>
<jsp:include page="dashBoardCustom.jsp"></jsp:include>
<%
} else if (r3.equals(status)) {
//    out.println("dashBoardTax");
%>
<jsp:include page="dashBoardTax.jsp"></jsp:include>
<%
    }else if (r4.equals(status)) {
//        out.println("dashBoardVat");
%>
<jsp:include page="dashBoardVat.jsp"></jsp:include>
<%
    }
%>


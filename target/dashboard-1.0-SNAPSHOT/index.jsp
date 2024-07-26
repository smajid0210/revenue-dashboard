<%@ page import = "java.io.*,java.util.*" %>
<%@ page import="api.eTINCountApi"%>
<%@ page import="login.bean.messageAlert"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link href="css/main.css" rel="stylesheet" crossorigin="anonymous">
    <title>National Board of Revenue</title>
</head>
<body>
<%--<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #D4E6F1 ;">--%>
<%--    <div class="container-fluid">--%>
<%--        <img src="img/logo.png" alt="nbr logo" class="logo">--%>
<%--        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">--%>
<%--            <span class="navbar-toggler-icon"></span>--%>
<%--        </button>--%>
<%--        <h2 style="margin-left: 12%;">--%>
<%--            Welcome to Revenue Dashboard--%>
<%--        </h2>--%>
<%--        <div class="collapse navbar-collapse text-right flex-grow-1" id="navbarText">--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</nav>--%>
<%--<a href="registration.jsp">registration</a>|--%>
<%--<a href="login.jsp">login</a>|--%>
<%--<a href="logout.jsp">logout</a>|--%>
<%--<a href="revdashboard.jsp">Dashboard</a>--%>


<%--<%@ include file="index.jsp" %>--%>
<a href="userform.jsp">registration</a>|
<a href="login.jsp">login</a>|
<a href="logout.jsp">logout</a>|
<a href="revdashboard.jsp">Dashboard</a>|||

<a href="dashBoardVat.jsp">VAT Dashboard</a>|
<a href="dashBoardTax.jsp"> Tax Dashboard</a>|
<a href="dashBoardCustom.jsp">Customs Dashboard</a>

<%
    String profile_msg=(String)request.getAttribute("profile_msg");
    if(profile_msg!=null){
        out.print(profile_msg);
    }
    String login_msg=(String)request.getAttribute("login_msg");
    if(login_msg!=null){
        out.print(login_msg);
    }
%>

<section class="vh-280" style="background-color: #393f81;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col col-xl-10">
                <div class="card" style="border-radius: 1rem;">
                    <div class="row g-0">
                        <div class="col-md-6 col-lg-5 d-none d-md-block">
                            <img src="img/NBR-building.jpg"
                                 alt="login form" class="img-fluid" style="border-radius: 1rem 0 0 1rem;" />
                        </div>
                        <div class="col-md-6 col-lg-7 d-flex align-items-center">
                            <div class="card-body p-4 p-lg-5 text-black">

                                <form id="loginobj" name ="loginobj" action="loginprocess.jsp" method="post">

                                    <div class="d-flex align-items-center mb-3 pb-1">
                                        <i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
                                        <span class="h1 fw-bold mb-0">Logo</span>
                                    </div>

                                    <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Sign into your account</h5>

                                    <div class="form-outline mb-4">
                                        <p class="text-danger">
                                        <% if(messageAlert.getLoginMsgID()>0){
                                            out.print(messageAlert.getLoginErrorMsg());
                                        messageAlert.setLoginMsgID(0);} %>
                                        </p>
                                        <input type="text" name="userName" id="userName" class="form-control form-control-lg" required>
                                        <label class="form-label" for="userName">User ID</label>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <input type="password" name="password" id="password" class="form-control form-control-lg" required>
                                        <label class="form-label" for="password">Password</label>
                                    </div>

                                    <div class="pt-1 mb-4">
                                        <button class="btn btn-dark btn-lg btn-block" type="submit">Login</button>
<%--                                        <input type="submit" value="login"/>--%>
                                    </div>

                                    <a class="small text-muted" href="forgotPass.jsp">Forgot password?</a>
                                    <p class="mb-5 pb-lg-2" style="color: #393f81;">Don't have an account? <a href="registration.jsp"
                                                                                                              style="color: #393f81;">Register here</a></p>
                                    <%--                  <a href="#!" class="small text-muted">Terms of use.</a>--%>
                                    <%--                  <a href="#!" class="small text-muted">Privacy policy</a>--%>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>

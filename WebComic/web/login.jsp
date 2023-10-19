<%-- 
    Document   : login
    Created on : Jun 19, 2023, 10:01:05 PM
    Author     : HP
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<<!DOCTYPE html>
<html lang="zxx">

<head>
   
    <style>
        .remeber_me{
         
            color: white;
           
        }
    </style>
</head>

<body>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

    <!-- Header Section Begin -->
  <%@include file="header.jsp"  %>
    <!-- Header End -->

   

    <!-- Login Section Begin -->
    <section class="login spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div class="login__form">
                        <h3>Login</h3>
                         <h5 style="color:red">${requestScope.err}</h5>
                         <c:set var="cookie" value="${pageContext.request.cookies}"/>
                        <form action="login" method="post">
                            <div class="input__item">
                                <input type="text" placeholder="User name" name="user" 
                                       value="${cookie.cuser.value}">
                                <span class="icon_profile"></span>
                            </div>
                            <div class="input__item">
                                <input type="password" placeholder="Password" name="pass" 
                                       value="${cookie.cpass.value}">
                                <span class="icon_lock"></span>
                            </div>
                            <div class="remeber_me">
                                 <input  type="checkbox" ${cookie.crem!=null?'checked':''} name="rem" value="ON"/> Remember me
                            </div>
                            <button type="submit" class="site-btn">Login Now</button>
                            
                           
                        </form>                       
                       
                    </div>
                       
                </div>
                <div class="col-lg-6">
                    <div class="login__register">
                        <h3>Dont’t Have An Account?</h3>
                        <a href="signup" class="primary-btn">Register Now</a>
                    </div>
                </div>
            </div>
           
        </div>
    </section>
    <!-- Login Section End -->

    <!-- Footer Section Begin -->
    <footer class="footer">
          <%@include file="footer.jsp"  %>
      </footer>
      <!-- Footer Section End -->

      

</body>

</html>
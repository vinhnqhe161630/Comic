<!DOCTYPE html>
<html lang="zxx">

<head>
   
</head>

<body>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

    <!-- Header Section Begin -->
    <%@include file="header.jsp"  %>
    <!-- Header End -->

   

    <!-- Signup Section Begin -->
    <section class="signup spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div class="login__form">
                        <h3>Change password</h3>
                        <form action="change" method="post">

                            <div class="input__item">
                                <input type="text" placeholder="Your Name" name="user" value="${sessionScope.account.user}">
                                <span class="icon_profile"></span>
                            </div>
                            <div class="input__item">
                                <input type="password" placeholder="Current password" name="cupass">
                                <span class="icon_lock"></span>
                            </div>
                            <div class="input__item">
                                <input type="password" placeholder="New password" name="newpass">
                                <span class="icon_lock"></span>
                            </div>
                             <div class="input__item">
                                <input type="password" placeholder="Confirm Password" name="copass">
                                <span class="icon_lock"></span>
                            </div>
                            <h5 style="color: red">${mess}</h5>
                            <button type="submit" class="site-btn">Change</button>
                        </form>
                        
                    </div>
                    
                </div>
                  <div class="col-lg-6">
                    <div class="login__register">
                        
                        <h3>Already have an account?</h3></br>
                        <a href="login" class="primary-btn">Log In!</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Signup Section End -->

    <!-- Footer Section Begin -->
   <%@include file="footer.jsp"  %>
      <!-- Footer Section End -->

   

   

</body>

</html>
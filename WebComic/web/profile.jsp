<%-- 
    Document   : profile
    Created on : Jul 3, 2023, 8:33:55 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@300;400;500;600;700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Mulish:wght@300;400;500;600;700;800;900&display=swap"
              rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="css/plyr.css" type="text/css">
        <link rel="stylesheet" href="css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="css/style.css" type="text/css">

        <style>
            .change{
                align-items: center;
                background: red;
                color: white;
                padding-left: 20px;
            }
        </style>
    </head>
    <body>
         <!-- Header Section Begin -->
  <%@include file="header.jsp"  %>
    <!-- Header End -->
        <div class="container rounded bg-white mt-5 mb-5">
            <div class="row">
                <div class="col-md-3 border-right">
                    <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" width="150px" src="img/avarta.jpg"><span class="font-weight-bold">${sessionScope.account.fname} ${sessionScope.account.lname}</span>

                        <a href="change"> <span class="border px-3 p-1 add-experience change">Change password</span></a></div><br>
                        
                </div>


                <div class="col-md-5 border-right">
                    <form action="profile" method="post">
                        <div class="p-3 py-5">
                            <div class="d-flex justify-content-between align-items-center mb-3">
                                <h4 class="text-right">Profile Settings</h4>
                            </div>
                            <div class="row mt-2">
                                <div class="col-md-12">
                                    <label class="labels">Name</label>
                                    <input type="text" class="form-control" placeholder="first name" name="fname" value="${sessionScope.account.fname}">
                                </div>
                                <div class="col-md-12">
                                    <label class="labels">Surname</label>
                                    <input type="text" class="form-control" name="lname" placeholder="surname" value="${sessionScope.account.lname}">
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-md-12">
                                    <label class="labels">Email:</label>
                                    <input type="text" class="form-control" placeholder="Email" value="${sessionScope.account.email}" readonly>
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-md-6">
                                    <label class="labels">Gender</label><br>
                                    <input type="radio" name="gender" value="1" ${sessionScope.account.gender == 1 ? "checked" : ""}> Female
                                    <input type="radio" name="gender" value="2" ${sessionScope.account.gender == 2 ? "checked" : ""}> Male
                                    
                                </div>
                            </div>
                            <div class="mt-5 text-center">
                                <input class="btn btn-primary profile-button" type="submit" value="Save profile">
                            </div>
                        </div>
                    </form>

                </div>

                <div class="col-md-4">
                    <div class="p-3 py-5">

                        <a href="logout"><span class="border px-3 p-1 add-experience change">&nbsp;Logout</span></a></div>


                </div>
            </div>
        </div>
    </div>
</div>
</div>
  <!-- Footer Section Begin -->
        <%@include file="footer.jsp" %>
        <!-- Footer Section End -->


</body>
</html>

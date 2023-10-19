<%-- 
    Document   : header
    Created on : Jun 19, 2023, 10:17:41 PM
    Author     : HP
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta name="description" content="Anime Template">
        <meta name="keywords" content="Anime, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Anime | Template</title>

<!--         Google Font 
        <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@300;400;500;600;700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Mulish:wght@300;400;500;600;700;800;900&display=swap"
              rel="stylesheet">-->

        <!-- Css Styles -->
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="css/plyr.css" type="text/css">
        <link rel="stylesheet" href="css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="css/style.css" type="text/css">
    </head>
    <body>
        <header class="header">
            <div class="container">
                <div class="row">
                    <div class="col-lg-2">
                        <div class="header__logo">
                            <a href="./home">
                                <img src="img/logo.png" alt="">
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-8">
                        <div class="header__nav">
                            <nav class="header__menu mobile-menu">
                                <ul>


                                    <li class="active"><a href="./home">Homepage</a></li>
                                  
                                    <li><a href="./home">Categories <span class="arrow_carrot-down"></span></a>
                                        <ul class="dropdown" >
                                            <c:forEach items="${requestScope.ca}" var="ca">
                                                <c:set var="id" value="${ca.ca_id}"/>
                                                <li><a href="category?id=${id}">${ca.ca_name}</a></li>

                                            </c:forEach>
                                        </ul>
                                    </li>
                                   
                                  <c:if test="${sessionScope.account.role==0 || sessionScope.account.role==2}">
                                    <li><a href="./admin">Admin</a>
                                        
                                    </li>   
                                       
                                    </c:if>
                                    <li><a href="./history">History</a></li>
                                    <li><a href="./follow">Following</a></li>
                                    <li><a href="https://www.facebook.com/profile.php?id=100094677240044">Fanpage</a></li>



                                </ul>
                            </nav>
                        </div>
                    </div>
                    <div class="col-lg-2">
                        <div class="header__right">
                            <a href="#" class="search-switch"><span class="icon_search"></span></a>
                                <c:choose>
                                    <c:when test="${sessionScope.account==null}">
                                    <a href="./login"><span class="icon_profile"></span></a>
                                    </c:when>
                                    <c:otherwise>
                                    <a href="./profile"><span class="icon_profile"></span></a>
                                    </c:otherwise>

                            </c:choose>



                            </span>
                        </div>
                    </div>
                </div>
                <div id="mobile-menu-wrap"></div>
            </div>
        </header>
    </body>
</html>

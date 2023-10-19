<!DOCTYPE html>
<html lang="zxx">
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <head>
        <style>
            .co h5{
                display: inline;
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
        <%@include file="header.jsp" %>
        <!-- Header End -->

        <!-- Breadcrumb Begin -->
        <div class="breadcrumb-option">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="breadcrumb__links">
                            <a href="./home"><i class="fa fa-home"></i> Home</a>
                          
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Breadcrumb End -->

        <!-- Product Section Begin -->
        <section class="product-page spad">
            <div class="container">



                <div class="row">
                    <div class="col-lg-8">
                        <div class="product__page__content">
                            <div class="product__page__title">
                                <div class="row">
                                    <div class="col-lg-8 col-md-8 col-sm-6">
                                        <div class="section-title">

                                            <h4>Story Following</h4>

                                        </div>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-6">

                                    </div>
                                </div>
                            </div>
                            <div class="row">


                                <c:forEach items="${list}" var="s" >

                                    <a href="story?id=${s.s_id}">
                                       
                                        <div class="col-lg-4 col-md-6 col-sm-6">
                                             <a href="follow?id=${s.s_id}" > <span class="remove-history" title="Un-Follow" data-id="14354" 
                                                              >
                                                <i class="fa fa-times-circle-o" aria-hidden="true"></i></span> </a>
                                            <div class="product__item">
                                                <div class="product__item__pic set-bg" data-setbg="${s.image}">
                                                  

                                                    <div class="view"><i class="fa fa-eye"></i> ${s.view}</div>
                                                </div>
                                                <div class="product__item__text">

                                                    <h5><a href="story?id=${s.s_id}">${s.s_name}</a></h5>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </c:forEach>



                            </div>
                        </div>

                    </div>
                    <%@include file="topview_comment.jsp" %>
                </div>
                <div class="product__pagination">
                    <c:forEach begin="1" end="${numberPage}" var="i">
                        <c:choose>
                            <c:when test="${requestScope.country==null}">
                                <a href="trending?page=${i}" class="current-page">${i}</a>
                            </c:when>
                            <c:otherwise>
                                <a href="trending?country=${country}&page=${i}" class="current-page">${i}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>



                    <a href="#"><i class="fa fa-angle-double-right"></i></a>
                </div>
            </div>
        </section>
        <!-- Product Section End -->

        <!-- Footer Section Begin -->
        <%@include file="footer.jsp" %>
        <!-- Footer Section End -->




    </body>

</html>
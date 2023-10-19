<!DOCTYPE html>
<html lang="zxx">
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
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

        <!-- Hero Section Begin -->
        <section class="hero">
            <div class="container">
                <div class="hero__slider owl-carousel">
                    
                  
                        
                  
                    <div class="hero__items set-bg" data-setbg="img/hero/hero-1.jpg">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="hero__text">
                                    <div class="label">Adventure</div>
                                    <h2>Fate / Stay Night: Unlimited Blade Works</h2>
                                    <p>After 30 days of travel across the world...</p>
                                    <a href="story?id=46"><span>Read Now</span> <i class="fa fa-angle-right"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                   
                </div>
            </div>
        </section>
        <!-- Hero Section End -->

        <!-- Product Section Begin -->
        <section class="product spad">
            <div class="container">

                <div class="row">
                    <div class="col-lg-8">
                        <div class="trending__product">
                            <div class="row">
                                <div class="col-lg-8 col-md-8 col-sm-8">
                                    <div class="section-title">
                                        <h4>Trending Now</h4>
                                    </div>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4">
                                    <div class="btn__all">
                                        <a href="trending" class="primary-btn">View All <span class="arrow_right"></span></a>
                                    </div>
                                </div>
                            </div>

                            <div class="row">  
                                <c:forEach items="${st}" var="s" begin="0" end="5">
                                    <a href="story?id=${s.key.s_id}">
                                        <div class="col-lg-4 col-md-6 col-sm-6">
                                            <div class="product__item">
                                                <div class="product__item__pic set-bg" data-setbg="${s.key.image}">
                                                    <div class="ep">Chapter ${s.value.get(s.getValue().size()-1).getCh_number()}</div>
                                                    <div class="view"><i class="fa fa-eye"></i> ${s.key.view}</div>
                                                </div>
                                                <div class="product__item__text">
                                                    <h5><a class="namestory" href="story?id=${s.key.s_id}">${s.key.s_name}</a></h5>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </c:forEach>

                            </div>
                        </div>
                        <div class="popular__product">
                            <div class="row">
                                <div class="col-lg-8 col-md-8 col-sm-8">
                                    <div class="section-title">
                                        <h4>Popular</h4>
                                    </div>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4">
                                    
                                </div>
                            </div>
                            <div class="row">



                                 <c:forEach items="${popular}" var="s" begin="0" end="5">
                                    <a href="story?id=${s.key.s_id}">
                                        <div class="col-lg-4 col-md-6 col-sm-6">
                                            <div class="product__item">
                                                <div class="product__item__pic set-bg" data-setbg="${s.key.image}">
                                                    <div class="ep">Chapter ${s.value.get(s.getValue().size()-1).getCh_number()}</div>
                                                    <div class="view"><i class="fa fa-eye"></i> ${s.key.view}</div>
                                                </div>
                                                <div class="product__item__text">
                                                    <h5><a class="namestory" href="story?id=${s.key.s_id}">${s.key.s_name}</a></h5>
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
            </div>
        </section>
        <!-- Product Section End -->

        <!-- Footer Section Begin -->
        <%@include file="footer.jsp"  %>
        <!-- Footer Section End -->




    </body>

</html>
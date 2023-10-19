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
                            <c:choose>
                                <c:when test="${requestScope.caid!=null}">


                                    <span>Categories >${requestScope.ca.get(caid-1).ca_name}</span>
                                </c:when>

                            </c:choose>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Breadcrumb End -->

        <!-- Product Section Begin -->
        <section class="product-page spad">
            <div class="container">
                <c:choose>
                    <c:when test="${requestScope.caid!=null}">
                        <div class="anime__details__episodes co">

                            <h5>Country </h5>

                            <a href="category?page=1&id=${caid}&country=1">Việt Nam</a>
                            <a href="category?page=1&id=${caid}&country=2">Hàn Quốc</a>
                            <a href="category?page=1&id=${caid}&country=3">Trung Quốc</a>
                            <a href="category?page=1&id=${caid}&country=4">Nhật Bản</a>

                        </div>
                    </c:when>

                </c:choose>
                <div class="row">
                    <div class="col-lg-8">
                        <div class="product__page__content">
                            <div class="product__page__title">
                                <div class="row">
                                    <div class="col-lg-8 col-md-8 col-sm-6">
                                        <div class="section-title">
                                            <c:choose>
                                                <c:when test="${requestScope.caid!=null}">
                                                    <h4>${requestScope.ca.get(caid-1).ca_name}</h4>
                                                </c:when>
                                                <c:otherwise>
                                                    <h4>Search Results</h4>
                                                </c:otherwise>

                                            </c:choose>
                                        </div>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-6">

                                    </div>
                                </div>
                            </div>
                            <div class="row">

                                <c:forEach items="${stories}" var="s">
                                    
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
                <div class="product__pagination">
                    <c:forEach begin="1" end="${numberPage}" var="i">
                        <c:choose>
                            <c:when test="${requestScope.country==null}">
                                <a href="category?page=${i}&id=${caid}" class="current-page">${i}</a>
                            </c:when>
                            <c:otherwise>
                                <a href="category?page=${i}&id=${caid}&country=${country}" class="current-page">${i}</a>
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

        <script>
            // Get the URL parameters to determine the current page
            const urlParams = new URLSearchParams(window.location.search);
            const currentPage = parseInt(urlParams.get('page')) || 1;

            // Remove 'current-page' class from all page links
            const pageLinks = document.querySelectorAll('a[id^="page"]');
            pageLinks.forEach(link => link.classList.remove('current-page'));

            // Add 'current-page' class to the active page link
            const activePageLink = document.getElementById(`page${currentPage}`);
            if (activePageLink) {
                activePageLink.classList.add('current-page');
            }
        </script>




    </body>

</html>
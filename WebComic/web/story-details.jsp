<%-- 
    Document   : story-details
    Created on : Jun 21, 2023, 7:42:06 PM
    Author     : HP
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

        <!-- Anime Section Begin -->
        <section class="anime-details spad">
            <div class="container">
                <div class="anime__details__content">

                    <div class="row">

                        <div class="col-lg-3">
                            <div class="anime__details__pic set-bg" data-setbg="${story.image}">
                                <div class="comment"><i class="fa fa-comments"></i> 11</div>
                                <div class="view"><i class="fa fa-eye"></i> ${story.view}</div>
                            </div>
                        </div>
                        <div class="col-lg-9">
                            <div class="anime__details__text">
                                <div class="anime__details__title">
                                    <h3>${story.s_name}</h3>

                                </div>

                                <p></p>
                                <div class="anime__details__widget">
                                    <div class="row">

                                        <div class="col-lg-6 col-md-6">
                                            <ul>
                                                <li><span>Author:</span> ${story.a.a_name}</li>
                                                <c:if test="${story.country == 1}">  <li><span>Country:</span> Việt Nam</li></c:if>
                                                   <c:if test="${story.country == 2}">  <li><span>Country:</span> Hàn Quốc</li></c:if>
                                                      <c:if test="${story.country == 3}">  <li><span>Country:</span> Trung Quốc</li></c:if>
                                                         <c:if test="${story.country == 4}">  <li><span>Country:</span>Nhật Bản</li></c:if>
                                               
                                                <li><span>Date aired:</span> ${story.create_at.substring(0,10)}</li>
                                                <li><span>Status:</span> 
                                                    <c:choose>
                                                        <c:when test="${story.status==1}">
                                                            Đang cập nhập
                                                        </c:when>
                                                        <c:otherwise>
                                                            Hoàn thành
                                                        </c:otherwise>

                                                    </c:choose>
                                                </li>
                                                <li><span>Genre:</span> 
                                                    <c:forEach items="${requestScope.stca}" var="ca"> ${ca.ca_name} </c:forEach>
                                                    </li>
                                                </ul>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="anime__details__btn">
                                    <c:choose>
                                        <c:when test="${isStoryincookie==false}">
                                            <a href="story?id=${story.s_id}&fo=1" class="follow-btn"><i class="fa fa-heart-o"></i> Follow</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="story?id=${story.s_id}&fo=0" class="follow-btn"><i class="fa fa-heart-o"></i>Un-Follow</a>
                                        </c:otherwise>

                                    </c:choose>



                                    <a href="chapter?sid=${story.s_id}&chapid=${chap.get(0).ch_id}" class="watch-btn"><span>Read Now</span> <i
                                            class="fa fa-angle-right"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

                <div class="anime__details__episodes">
                    <div class="section-title">
                        <h5>List Chapters</h5>
                    </div>
                    <c:forEach items="${requestScope.chap}" var="ch">
                        <a href="chapter?sid=${story.s_id}&chapid=${ch.ch_id}">${ch.ch_number}</a>
                    </c:forEach>




                </div>
                <!--  Comment                    -->
                <div class="row">
                    
                    <div class="col-lg-8 col-md-8">
                          <div class="anime__details__form">
                            <div class="section-title">
                                <h5>Your Comment</h5>
                            </div>
                         
                            <c:choose>
                                <c:when test="${sessionScope.account!=null}">
                                    <form action="comment?sid=${story.s_id}" method="post" >
                                        <textarea name="comment" placeholder="Your Comment"></textarea>
                                        <button type="submit"><i class="fa fa-location-arrow"></i> Review</button>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <form action="comment?sid=${story.s_id}" method="post" onsubmit="return showAlertAndPreventSubmit(event)">
                                        <textarea name="comment" placeholder="Your Comment"></textarea>
                                        <button type="submit"><i class="fa fa-location-arrow"></i> Review</button>
                                    </form>
                                </c:otherwise>

                            </c:choose>
                              <br><br>

                        </div>
                        <div class="anime__details__review">
                            <div class="section-title">
                                <h5>Comments</h5>
                            </div>

                            <c:forEach items="${cmt}" var="cmt">


                                <div class="anime__review__item">
                                    <div class="anime__review__item__pic">
                                        <img src="img/anime/review-6.jpg" alt="">
                                    </div>
                                    <div class="anime__review__item__text">
                                        <c:choose>
                                        <c:when test="${cmt.key.a.fname==null}">
                                          <h6>${cmt.key.a.user} - <span>${cmt.value}</span></h6>
                                        </c:when>
                                        <c:otherwise>
                                            <h6>${cmt.key.a.fname}${cmt.key.a.lname} - <span>${cmt.value}</span></h6>
                                        </c:otherwise>

                                    </c:choose>
                                        
                                        <p>${cmt.key.content}</p>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                      
                    </div>


                    <div class="col-lg-4 col-md-4">
                        <div class="anime__details__sidebar">
                            <div class="section-title">
                                <h5>you might like...</h5>
                            </div>
                            <c:forEach items="${popular}" var="d" begin="0" end="3">
                            <div class="product__sidebar__comment__item set-bg ">
                                <div class="product__sidebar__comment__item__pic">
                                    <img src="${d.image}" alt="" style="width: 95px; height: 123px;">
                                </div>
                                <div class="product__sidebar__comment__item__text">

                                    <h5><a href="story?id=${d.s_id}">${d.s_name}</a></h5>
                                    <span><i class="fa fa-eye"></i> ${d.view} Viewes</span>
                                </div>
                            </div>
                        </c:forEach>
                            
                        </div>
                    </div>
                </div>




            </div>
        </section>
        <!-- Anime Section End -->




        <script>
            function showAlertAndPreventSubmit(event) {
                event.preventDefault(); // Prevents the form from being submitted
                alert("Please log in before submitting the form.");
                return false; // Ensures the form is not submitted
            }
        </script>








        <!-- Footer Section Begin -->
        <%@include file="footer.jsp"  %>
        <!-- Footer Section End -->




    </body>

</html>

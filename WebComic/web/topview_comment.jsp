<%-- 
    Document   : topview_comment
    Created on : Jun 27, 2023, 9:03:02 PM
    Author     : HP
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
/*            .editiamge{
            width: 50%;
                height: 50%
            }*/
        </style>
    </head>
    <body>



        <div class="col-lg-4 col-md-6 col-sm-8">
            <div class="product__sidebar">
                <div class="product__sidebar__view">
                    <div class="section-title">
                        <h5>Top Views</h5>
                    </div>
                    <ul class="filter__controls">
                     
                     
                    </ul>
                    <div class="filter__gallery">


                        <!-- week -->
                        <c:forEach items="${topweek}" var="d" >
                            <div class="product__sidebar__comment__item set-bg mix week ">
                                <div class="product__sidebar__comment__item__pic">
                                    <img class="editiamge" src="${d.image}" alt="" style="width: 95px; height: 123px; ">
                                </div>
                                <div class="product__sidebar__comment__item__text">

                                    <h5><a href="story?id=${d.s_id}">${d.s_name}</a></h5>
                                    <span><i class="fa fa-eye"></i> ${d.view} Viewes</span>
                                </div>
                            </div>
                        </c:forEach>

                        <!-- week -->

                      
                    </div>
                </div>
                <div class="product__sidebar__comment">
                    <div class="section-title">
                        <h5>New Comment</h5>
                    </div>
                    
                     <c:forEach items="${newcmt}" var="d">
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
        <script>
    document.getElementById('weekFilter').addEventListener('click', function() {
        // Add your code here to be executed when the "Week" filter is clicked
        // For example, you can show/hide elements or perform any other actions
        
        // Example: Alert a message
        alert('Week filter clicked!');
    });
</script>
    </body>
</html>

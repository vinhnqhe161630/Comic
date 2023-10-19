<!DOCTYPE html>
<html lang="zxx">
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <head>
        <style>
          
            .a{
                padding: 100px;
                margin: 50px;
                align-items: center;
            }
            .abc{
                padding: 100px;
                margin: 50px;
                align-items: center;
            }
             #addAccModal {
                position: fixed;
                background-color: white;
                width: 50%;
                height: 80%;
                margin-left: auto;
                margin-right: auto;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                border: 3px solid #ccc;
            }
           
        </style>
        <style>
            .chapter-list {
                display: flex;
                justify-content: center;
                align-items: center;
                list-style: none;
                padding: 0;
            }

            .chapter-list li {
                flex: 1;
                text-align: center;
            }

            .chapter-list li:not(:last-child) {
                border-right: 1px solid black;
            }

            .chapter-list a {
                text-decoration: none;
                display: block;
                padding: 10px 20px;

                color: white;
            }

            .chapter-list a:hover {
                background-color: #f5f5f5;
            }
            .report{
            width: 100%;    
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
        <div class="a">
            <h3 style="color: white">${s.s_name}</h3>
            <h3 style="color: white">Chap ${ch.ch_number}</h3>
            <div>
                <img class="abc" src="https://i.pinimg.com/474x/7e/9e/40/7e9e409a40e0797a3531265203a84a60.jpg" alt="alt"/>
            </div>

        </div>
        <div class="row" class="ac">
            <div class="col-sm-4 ">
              
            </div>
            <div>
                <ul class="chapter-list">

                    <li class="abc">
                        <select onchange="location = this.value;">
                            <c:forEach items="${listch}" var="chap">
                                <option value="chapter?sid=${s.s_id}&chapid=${chap.ch_id}"
                                        ${ch.ch_id == chap.ch_id ? 'selected disabled hidden' : ''}>
                                    Chap ${chap.ch_number}
                                </option>
                            </c:forEach>
                        </select>


                        <!-- Thêm các option cho các chap khác -->
                        <br><br><br>
                        <a href="#" class="btn btn-success" id="addAccountBtn"><i class="material-icons">&#xE147;</i> <span>Report error</span></a>
                    </li>

                </ul>
            </div>
            <div class="col-sm-4 "></div>
        </div>

            <div id="addAccModal" style="display: none;">
                <form action="test?sid=${s.s_id}&chid=${ch.ch_id}" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Chapter error report</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="section-title">
                            <!-- Add account form fields here -->
                            <!-- For example: -->
                           
                                
                               <textarea  class="report" name="report" placeholder="Your report"></textarea>
                          
                            
                          
                            
                         
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-info" value="Save">
                        </div>
                    </form>
                </div>  
            
            
            
        <!--  Comment                    -->
        <div class="row">
            <div class="col-lg-8 col-md-8">
                <div class="anime__details__review">
                    <div class="section-title">
                        <h5>Reviews</h5>
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
                <div class="anime__details__form">
                    <div class="section-title">
                        <h5>Your Comment</h5>
                    </div>

                    <c:choose>
                        <c:when test="${sessionScope.account!=null}">
                            <form action="comment?sid=${s.s_id}" method="post" >
                                <textarea name="comment" placeholder="Your Comment"></textarea>
                                <button type="submit"><i class="fa fa-location-arrow"></i> Review</button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form action="comment?sid=${s.s_id}" method="post" onsubmit="return showAlertAndPreventSubmit(event)">
                                <textarea name="comment" placeholder="Your Comment"></textarea>
                                <button type="submit"><i class="fa fa-location-arrow"></i> Review</button>
                            </form>
                        </c:otherwise>

                    </c:choose>

                </div>
            </div>

            <!-- Footer Section Begin -->
            <%@include file="footer.jsp" %>
            <!-- Footer Section End -->



 <script>
                    var addAccountBtn = document.getElementById('addAccountBtn');
                    var addAccModal = document.getElementById('addAccModal');

                    addAccountBtn.addEventListener('click', function (event) {
                        event.preventDefault(); // Prevent default link behavior

                        // Show the Add New Account modal
                        addAccModal.style.display = 'block';
                    });
                    
                </script>
              <script>
    var cancelButton = document.querySelector('#addAccModal input[type="button"]');
    var addAccModal = document.getElementById('addAccModal');

    cancelButton.addEventListener('click', function (event) {
        event.preventDefault(); // Ngăn chặn hành vi mặc định của nút

        // Ẩn form
        addAccModal.style.display = 'none';
    });
</script>
  <script>
            function showAlertAndPreventSubmit(event) {
                event.preventDefault(); // Prevents the form from being submitted
                alert("Please log in before submitting the form.");
                return false; // Ensures the form is not submitted
            }
        </script>
    </body>

</html>
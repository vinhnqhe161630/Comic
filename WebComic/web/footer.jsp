<%-- 
    Document   : footer
    Created on : Jun 19, 2023, 10:05:16 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
    </head>
    <body>
      <footer class="footer">
    <div class="page-up">
        <a href="#" id="scrollToTopButton"><span class="arrow_carrot-up"></span></a>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div class="footer__logo">
                    <a href="./home"><img src="img/logo.png" alt=""></a>
                </div>
                <p style="color: white">Email : truyenhay@gmail.com</p>
            </div>
            <div class="col-lg-6">
                <div class="footer__nav">
                    <ul>
                     <li class="active"><a href="./home">Homepage</a></li>                       
                     <li><a href="history">History</a></li>
                     <li><a href="follow">Following</a></li>
                      <li><a href="https://www.facebook.com/truyenqqq">Fanpage</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-3">
                <p>Mọi thông tin và hình ảnh trên website đều được sưu tầm trên Internet. 
                    Chúng tôi không sở hữu hay chịu trách nhiệm bất kỳ thông tin nào trên web này. 
                    Nếu làm ảnh hưởng đến cá nhân hay tổ chức nào,
                    khi được yêu cầu, chúng tôi sẽ xem xét và gỡ bỏ ngay lập tức.</p>

              </div>
          </div>
      </div>

          <!-- Footer Section End -->

  <!-- Search model Begin -->
  <div class="search-model">
    <div class="h-100 d-flex align-items-center justify-content-center">
        <div class="search-close-switch"><i class="icon_close"></i></div>
        <form class="search-model-form" action="search" method="post" >
            <input type="text" id="search-input" placeholder="Search here....." name="storyname">
            
        </form>

    </div>
</div>


  </footer>
<!-- Search model end -->

<!-- Js Plugins -->
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/player.js"></script>
<script src="js/jquery.nice-select.min.js"></script>
<script src="js/mixitup.min.js"></script>
<script src="js/jquery.slicknav.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/main.js"></script>

    </body>
</html>

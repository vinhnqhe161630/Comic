<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Admin</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Favicon -->
        <link href="chartt/img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap" rel="stylesheet">

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="chartt/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="chartt/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

        <!-- Customized Bootstrap Stylesheet -->
        <link href="chartt/css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="chartt/css/style.css" rel="stylesheet">

        <style>
            .canle{
                padding-left: 100px;
                padding-right: 100px
            }
            .imga img{
                width: 73px;
                height: 104px;
            }

            th:nth-child(2) {
                width: 250px;
            }



        </style>
        <script type="text/javascript">
            function doDelete(id) {
                if (confirm("Are you want to delete ?")) {
                    window.location = "deleteSt?id=" + id;
                }
            }
        </script>
    </head>

    <body>
        <!-- Header Section Begin -->
        <%@include file="headerAd.jsp"  %>
        <!-- Header End -->
        <div class="container-fluid position-relative bg-white d-flex p-0">



            <!-- Sidebar Start -->

            <!-- Sidebar End -->


            <!-- Content Start -->
            <div class="row canle">
                <!-- Navbar Start -->

                <!-- Navbar End -->


                <!-- Sale & Revenue Start -->
                <div class="container-fluid pt-4 px-4">
                    <div class="row g-4">
                        <div class="col-sm-6 col-xl-3">
                            <div class="bg-light rounded d-flex align-items-center justify-content-between p-4">
                                <i class="fas fa-book fa-3x text-primary"></i>
                                <div class="ms-3">
                                    <p class="mb-2">Total Story</p>
                                    <h6 class="mb-0">${totalStory}</h6>
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-6 col-xl-3">
                            <div class="bg-light rounded d-flex align-items-center justify-content-between p-4">
                                <i class="fas fa-eye fa-3x text-primary"></i>
                                <div class="ms-3">
                                    <p class="mb-2">Total View</p>
                                    <h6 class="mb-0">${totalViews}</h6>
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-6 col-xl-3">
                            <div class="bg-light rounded d-flex align-items-center justify-content-between p-4">
                                <i class="fas fa-language fa-3x text-primary"></i>
                                <div class="ms-3">
                                    <p class="mb-2">Translator</p>
                                    <h6 class="mb-0">${totalTrans}</h6>
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-6 col-xl-3">
                            <div class="bg-light rounded d-flex align-items-center justify-content-between p-4">
                                <i class="fas fa-user fa-3x text-primary"></i>
                                <div class="ms-3">
                                    <p class="mb-2">Total User</p>
                                    <h6 class="mb-0">${totalUser}</h6>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Sale & Revenue End -->


                <!-- Sales Chart Start -->
                <div class="container-fluid pt-4 px-4">
                    <div class="row g-4">
                        <div class="col-sm-12 col-xl-6">
                            <div class="bg-light text-center rounded p-4">
                                <div class="d-flex align-items-center justify-content-between mb-4">
                                    <h6 class="mb-0">The number of stories of the translation team</h6>

                                </div>
                                <canvas id="bar-chart"></canvas>

                            </div>
                        </div>
                        <div class="col-sm-12 col-xl-6">
                            <div class="bg-light text-center rounded p-4">
                                <div class="d-flex align-items-center justify-content-between mb-4">
                                    <h6 class="mb-0">Views</h6>
                                    
                                </div>
                                <canvas id="line-chart"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Sales Chart End -->


                <!-- Recent Sales Start -->
                <div class="container-fluid pt-4 px-4">
                    <div class="bg-light text-center rounded p-4">
                        <div class="d-flex align-items-center justify-content-between mb-4">
                            <h6 class="mb-0">Newly updated story</h6>
                            <a href="./manageSto">Show All</a>
                        </div>
                        <div class="table-responsive">
                            <table class="table text-start align-middle table-bordered table-hover mb-0">
                                <thead>
                                    <tr class="text-dark">

                                        <th scope="col">Image</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Author</th>
                                        <th scope="col">Update at</th>
                                        <th scope="col">Country</th>
                                        <th scope="col">Total Chapters</th>
                                        <th scope="col">Action</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${st}" var="s" begin="0" end="4">


                                        <tr>

                                            <td class="imga"><img src="${s.key.image}" alt="alt"/></td>
                                            <td>${s.key.s_name}</td>
                                            <td>${s.key.a.a_name}</td>
                                            <td>${s.value.upchat_at.substring(0,10)}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${s.key.country == 1}">
                                                        Việt Nam
                                                    </c:when>
                                                    <c:when test="${s.key.country == 2}">
                                                        Hàn Quốc
                                                    </c:when>
                                                    <c:when test="${s.key.country == 3}">
                                                        Trung Quốc
                                                    </c:when>
                                                    <c:when test="${s.key.country == 4}">
                                                        Nhật Bản
                                                    </c:when>
                                                    <c:otherwise>
                                                        ${s.key.country}
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>${s.value.ch_number}</td> 
                                           <td>
    <span style="font-size: smaller; display: block;">
        <a href="updateSt?id=${s.key.s_id}" class="edit" data-toggle="modal">Edit</a>
    </span><br>
    <span style="font-size: smaller; display: block;">
        <a href="#" onclick="doDelete('${s.key.s_id}')" class="delete" data-toggle="modal" style="color: red">Delete</a>
    </span>
</td>



                                        </tr>
                                    </c:forEach>



                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- Recent Sales End -->


                <!-- Widgets Start -->

                <div class="container-fluid pt-4 px-4">
                    <div class="row g-4">

                        <!--                        Top view-->

                        <div class="col-sm-12 col-md-6 col-xl-4">
                            <div class="h-100 bg-light rounded p-4">
                                <div class="d-flex align-items-center justify-content-between mb-2">
                                    <h6 class="mb-0">Top view</h6>

                                </div>


                                <c:forEach items="${topview}" var="tv">
                                    <div class="d-flex align-items-center pt-3">
                                        <img class="rounded-circle flex-shrink-0" src="${tv.image}" alt="" style="width: 40px; height: 40px;">
                                        <div class="w-100 ms-3">
                                            <div class="d-flex w-100 justify-content-between">
                                                <small class="mb-0 ">${tv.s_name}</small>
                                                <small><i class="fas fa-eye"></i> ${tv.view}</small>
                                            </div>

                                        </div>
                                    </div>
                                </c:forEach>



                            </div>
                        </div>


                        <!--                         Top user-->


                       <div class="col-sm-12 col-md-6 col-xl-4">
                            <div class="h-100 bg-light rounded p-4">
                                <div class="d-flex align-items-center justify-content-between mb-2">
                                    <h6 class="mb-0">Top User</h6>

                                </div>


                                <c:forEach items="${topuser}" var="s">
                                    <div class="d-flex align-items-center pt-3">
                                        <img class="rounded-circle flex-shrink-0" src="img/avarta.jpg" alt="" style="width: 40px; height: 40px;">
                                        <div class="w-100 ms-3">
                                            <div class="d-flex w-100 justify-content-between">
                                                  <h6 class="mb-0 "><strong>${s.key.user}</strong></h6>
                                               <small class="mb-0">💬 ${s.getValue()}</small>

                                              
                                            </div>

                                        </div>
                                    </div>
                                </c:forEach>



                            </div>
                        </div>

                        <!--                        Comment-->
                        <div class="col-sm-12 col-md-6 col-xl-4">
                            <div class="h-100 bg-light rounded p-4">
                                <div class="d-flex align-items-center justify-content-between mb-2">
                                    <h6 class="mb-0">New Comments</h6>

                                </div>


                                <c:forEach items="${cmt}" var="entry">
                                    <div class="d-flex align-items-center pt-3">
                                        <img class="rounded-circle flex-shrink-0" src="img/avarta.jpg" alt="" style="width: 40px; height: 40px;">
                                        <div class="w-100 ms-3">
                                            <div class="d-flex w-100 justify-content-between">
                                                <h6 class="mb-0 "><strong>${entry.key.a.user}</strong></h6>
                                                <small>${entry.value}</small>
                                            </div>
                                            <span>${entry.key.content}</span>
                                        </div>
                                    </div>
                                </c:forEach>



                            </div>
                        </div>
                        <!--                        Comment-->



                    </div>
                </div>
                <!-- Widgets End -->


                <!-- Footer Start -->

                <!-- Footer End -->
            </div>
            <!-- Content End -->


            <!-- Back to Top -->
            <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
        </div>

        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="chartt/lib/chart/chart.min.js"></script>
        <script src="chartt/lib/easing/easing.min.js"></script>
        <script src="chartt/lib/waypoints/waypoints.min.js"></script>
        <script src="chartt/lib/owlcarousel/owl.carousel.min.js"></script>
        <script src="chartt/lib/tempusdominus/js/moment.min.js"></script>
        <script src="chartt/lib/tempusdominus/js/moment-timezone.min.js"></script>
        <script src="chartt/lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

        <!-- Template Javascript -->
        <script src="chartt/js/main.js"></script>

        <script>
                                                        var chartData = [];
                                                        var chartData2 = [];
            <c:forEach items="${chartTrans}" var="entry">
                                                        var key = "${entry.key}"; // Get the key from LinkedHashMap entry
                                                        var value = "${entry.value}";
                                                        chartData.push(key);
                                                        chartData2.push(value);

            </c:forEach>

                                                        var ctx = document.getElementById("bar-chart").getContext("2d");
                                                        var myChart = new Chart(ctx, {
                                                            type: "bar",
                                                            data: {
                                                                labels: chartData, // Use the chartData array as labels
                                                                datasets: [{
                                                                        backgroundColor: [
                                                                            "rgba(0, 156, 255, .7)",
                                                                            "rgba(0, 156, 255, .6)",
                                                                            "rgba(0, 156, 255, .5)",
                                                                            "rgba(0, 156, 255, .4)",
                                                                            "rgba(0, 156, 255, .3)"
                                                                        ],
                                                                        data: chartData2 // Replace with your desired data
                                                                    }]
                                                            },
                                                            options: {
                                                                responsive: true
                                                            }
                                                        });
        </script>


        <!--    -->
        <script>
            var chartData3 = [];
            var chartData4 = [];
            <c:forEach items="${listsv}" var="sv">
            var stime = "${sv.view_date.substring(0,10)}"; // Get the key from LinkedHashMap entry
            var views = "${sv.view_count}";
            chartData3.push(stime);
            chartData4.push(views);

            </c:forEach>
            var ctx3 = $("#line-chart").get(0).getContext("2d");
            var myChart3 = new Chart(ctx3, {
                type: "line",
                data: {
                    labels: chartData3,
                    datasets: [{
                            label: "Views",
                            fill: false,
                            backgroundColor: "rgba(0, 156, 255, .3)",
                            data: chartData4
                        }]
                },
                options: {
                    responsive: true
                }
            });
        </script>
    </body>

</html>

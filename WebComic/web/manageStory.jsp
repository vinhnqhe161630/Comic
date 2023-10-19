<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
          <title>Admin</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bootstrap CRUD Data Table for Database with Modal Form</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <style>
            table{
                font-size: 25px
            }

            tr,th,td{
                font-size: 15px;
                text-align: center;
                padding: 8px;
                padding-top: 10px;

            }
            th:nth-child(2) {
                width: 100px;
            }

            th:nth-child(3) {
                width: 200px;
            }



        </style>
        <script type="text/javascript">
            function doDelete(id) {
                if (confirm("Are you want to delete ?")) {
                    window.location = "deleteSt?id=" + id;
                }
            }
        </script>

    <body>
        <!-- Header Section Begin -->
        <%@include file="headerAd.jsp"  %>
        <!-- Header End -->
        <div class="container rounded bg-white">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Manage <b>Story</b></h2>
                            <br>   <br>
                            <form action="manageSto" >
                                <h6>Search <input type="text" name="name" placeholder="Enter story name" value="${searchname}">  </h6></br>

                                <p> Category <select name="category">
                                        <option value="0">All</option>
                                        <c:forEach items="${ca}" var="ca">
                                            <option value="${ca.ca_id}" <c:if test="${caid == ca.ca_id}">selected</c:if>>${ca.ca_name}</option>

                                        </c:forEach>


                                    </select> &nbsp &nbsp 
                                    Country <select name="country">
                                        <option value="0" >All</option>
                                        <option value="1" <c:if test="${country == '1'}">selected</c:if>>Việt Nam</option>
                                        <option value="2" <c:if test="${country == '2'}">selected</c:if>>Hàn Quốc</option>
                                        <option value="3" <c:if test="${country == '3'}">selected</c:if>>Trung Quốc</option>
                                        <option value="4" <c:if test="${country == '4'}">selected</c:if>>Nhật Bản</option>

                                        </select></p> </br>

                                    <input type="submit" value="Search">
                                </form>
                            </div>
                            <div class="col-sm-6 text-right">

                             <a href="addSt" class="btn btn-success" ><i class="material-icons">&#xE147;</i> <span>Add New Story</span></a>
                             <a href="#" class="btn btn-danger" data-toggle="modal" onclick="sendData()"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a>

       
                            
                            </div>

                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                               <th>
    <span class="custom-checkbox">
        <input type="checkbox" id="selectAll" onclick="selectAllCheckboxes()">
        <label for="selectAll"></label>
    </span>
</th>
                                <th>Image</th>
                                <th>Name</th>
                                <th>Author</th>    
                                <th>View</th>
                                <th>Update at</th>
                                <th>Country</th>
                                <th>Total Chapters</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:set var="count" value="1" />
                        <c:forEach items="${st}" var="s">


                            <tr>
                                <td>
                                    <span class="custom-checkbox">
                                        <input type="checkbox" id="checkbox${count}" name="options[]" value="${s.key.s_id}">
                                        <label for="checkbox${count}"></label>
                                    </span>
                                </td>
                                <td><img src="${s.key.image}" alt=""/></td>
                                <td>${s.key.s_name}</td>
                                <td>${s.key.a.a_name}</td>
                                <td>${s.key.view}</td>
                                <td>${s.value.get(s.value.size()-1).upchat_at.substring(0,10)}</td>
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
                                <td>${s.value.get(s.value.size()-1).ch_number}</td>

                                <td>
                                    <a href="updateSt?id=${s.key.s_id}" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                    <a href="#"  onclick="doDelete('${s.key.s_id}')" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete" >&#xE872;</i></a>
                                </td>
                            </tr>
                        </c:forEach>






                    </tbody>
                </table>

                <div class="clearfix">

                    <ul class="pagination">


                        <c:forEach begin="1" end="${numberPage}" var="i">
                            <c:choose>
                                <c:when test="${searchname == null && country == null}">
                                    <li class="page-item">
                                        <a href="manageSto?page=${i}" class="current-page page-link">${i}</a>
                                    </li>
                                </c:when>
                                <c:when test="${searchname == null && country != null}">
                                    <li class="page-item">
                                        <a href="manageSto?page=${i}&amp;category=${caid}&amp;country=${country}" class="current-page page-link">${i}</a>
                                    </li>
                                </c:when>
                                <c:when test="${searchname != null}">
                                    <li class="page-item">
                                        <a href="manageSto?page=${i}&amp;category=${caid}&amp;country=${country}&amp;name=${searchname}" class="current-page page-link">${i}</a>
                                    </li>
                                </c:when>
                            </c:choose>
                        </c:forEach>


                    </ul>
                </div>



                <br>
                <br>
                <br>
                <br>
                <br>

                <script>
                    // Get the pagination container element
                    var paginationContainer = document.querySelector('.pagination');

                    // Get all page links inside the container
                    var pageLinks = paginationContainer.getElementsByClassName('page-link');

                    // Loop through the links and add the active class to the current/clicked link
                    for (var i = 0; i < pageLinks.length; i++) {
                        pageLinks[i].addEventListener('click', function () {
                            // Remove active class from the current active link
                            var currentActive = paginationContainer.querySelector('.current-page');
                            currentActive.classList.remove('active');

                            // Add active class to the clicked link
                            this.classList.add('active');
                        });
                    }
                </script>
           <script type="text/javascript">
    function sendData() {
        var checkboxes = document.querySelectorAll('input[name="options[]"]:checked');
        var values = Array.from(checkboxes).map(function(checkbox) {
            return checkbox.value;
        });

        if (values.length > 0) {
            var confirmDelete = confirm("Are you sure you want to delete ?");
            if (confirmDelete) {
                var form = document.createElement('form');
                form.method = 'POST';
                form.action = 'deleteSt';

                values.forEach(function(value) {
                    var input = document.createElement('input');
                    input.type = 'hidden';
                    input.name = 'selectedItems';
                    input.value = value;
                    form.appendChild(input);
                });

                document.body.appendChild(form);
                form.submit();
            }
        } else {
            alert('Please select at least one item.');
        }
    }
</script>


<script type="text/javascript">
    function selectAllCheckboxes() {
        var selectAllCheckbox = document.getElementById('selectAll');
        var checkboxes = document.querySelectorAll('input[name="options[]"]');

        for (var i = 0; i < checkboxes.length; i++) {
            checkboxes[i].checked = selectAllCheckbox.checked;
        }
    }
</script>




                </body>
                </html>

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

            }
            #formContainer {
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background-color: #fff;
                padding: 20px;
                border: 1px solid #ccc;
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


    <body>
        <!-- Header Section Begin -->
        <%@include file="headerAd.jsp"  %>
        <!-- Header End -->
        <div class="container rounded bg-white">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Manage <b>Account</b></h2>
                            <br>   <br>
                            <form action="manageAcc" method="post">
                                <h6>Search <input type="text" name="user" placeholder="Enter user name" value="${user}">  </h6></br>

                                <p> Role <select name="role">
      <option value="3" <c:if test="${role == '3'}">selected</c:if>>All</option>
    <option value="1" <c:if test="${role == '1'}">selected</c:if>>Reader</option>
    <option value="2" <c:if test="${role == '2'}">selected</c:if>>Translator</option>
    <option value="0" <c:if test="${role == '0'}">selected</c:if>>Admin</option>
                                    </select> &nbsp &nbsp 
                                    Status <select name="status">
                                        <option value="3"  <c:if test="${status == '3'}">selected</c:if>>All</option>
                                        <option value="1"  <c:if test="${status == '1'}">selected</c:if>>Active</option>
                                        <option value="0"  <c:if test="${status == '0'}">selected</c:if>>Blocked</option>

                                    </select></p> </br>
                                <input type="submit" value="Search">
                            </form>
                        </div>
                                        <div class="col-sm-6 text-right">
                            <a href="#" class="btn btn-success" id="addAccountBtn"><i class="material-icons">&#xE147;</i> <span>Add New Account</span></a>
                        </div>

                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Username</th>
                            <th>Password</th>    
                            <th>Name</th>
                            <th>Gender</th>
                            <th>Status</th>
                            <th>Role</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="count" value="1" />
                        <c:forEach items="${acc}" var="ac">

                            <tr>
                                <td>${ac.ac_id}</td>
                                <td>${ac.user}</td>
                                <td>${ac.password}</td>
                                <td>${ac.fname} ${ac.lname}</td>


                                <c:if test="${ac.gender==0}">
                                    <td> <img src="img/male-icon.png" alt="male" /></td>
                                    </c:if>
                                    <c:if test="${ac.gender==1}">
                                    <td>  <img src="img/female-icon.png" alt="female" /></td>
                                    </c:if>

                                <c:if test="${ac.gender!=1 && ac.gender!=0}">
                                    <td> </td>
                                </c:if>
                                <c:if test="${ac.status==1}">
                                    <td style="color: green">Active</td>
                                </c:if>
                                <c:if test="${ac.status==0}">
                                    <td style="color: red">Blocked</td>
                                </c:if>

                                <c:if test="${ac.role==0}">
                                    <td>Admin</td>
                                </c:if>
                                <c:if test="${ac.role==1}">
                                    <td>Reader</td>
                                </c:if>
                                <c:if test="${ac.role==2}">
                                    <td>Translator</td>
                                </c:if>

                                <td>

                                    <a href="#" class="edit" id="showForm${count}" data-password="${ac.password}" data-password2="${ac.ac_id}" data-role="${ac.role}" data-status="${ac.status}" >
                                        <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i>
                                    </a>



                                </td>
                            </tr>
                        </c:forEach>





                    </tbody>
                </table>
                        
                     
                        
                <div id="addAccModal" style="display: none;">
                    <form action="add" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Add New Account</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">
                            <!-- Add account form fields here -->
                            <!-- For example: -->
                            <div class="form-group">
                                <h5>Username</h5>
                                <input type="text" class="form-control" name="username" required>
                            </div>
                            <div class="form-group">
                                <h5>Password</h5>
                                <input type="password" class="form-control" name="password" required>
                            </div>
                            
                            <div class="form-group inline-fields">
                                <div class="col-sm-6"> <h5>Firstname</h5>
                                <input type="text" class="form-control" name="fname"></div>
                                 <div class="col-sm-6"> <h5>Lastname</h5>
                                <input type="text" class="form-control" name="lname"></div>
                               
                                
                            </div>
                            
                            <div class="form-group">
                                <h5>Gender</h5>
                                <input type="radio"  value="0" name="gender" required><label> Female </label>
                                <input type="radio"  value="1" name="gender"><label> Male </label>
                               
                            </div>
                           <div class="form-group">
                                <h5>Email</h5>
                                <input type="text" class="form-control" name="email" required>
                            </div>
                             <div class="form-group">
                                <h5>Role</h5>
                                <input type="radio"  value="0" name="role" required><label> Admin </label>
                                <input type="radio"  value="1" name="role"><label> Reader </label>
                                <input type="radio"  value="2" name="role"> <label>Translator </label> </br>
                            </div>
                            <!-- Add other form fields as needed -->
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-info" value="Save">
                        </div>
                    </form>
                </div>


                <div id="formContainer" style="display: none;">
                    <form action="editAcc" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Edit Account</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">	


                            <div class="form-group">
                                <h5>ID</h5>
                                <input type="text" class="form-control" name="pass2"></br>
                            </div>
                            <div class="form-group">
                                <h5>Password</h5>
                                <input type="text" class="form-control" name="pass"></br>
                            </div>
                            <div class="form-group">
                                <h5>Role</h5>
                                <input type="radio"  value="0" name="role"><label> Admin </label>
                                <input type="radio"  value="1" name="role"><label> Reader </label>
                                <input type="radio"  value="2" name="role"> <label>Translator </label> </br>
                            </div>
                            <div class="form-group">
                                <h5>Status</h5>
                                <input type="radio" value="1" name="status"> <label>Active</label>
                                <input type="radio"  value="0" name="status"> <label>Blocked </label>
                            </div>					
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-info" value="Save">
                        </div>
                    </form>
                </div>

                <br>
                <br>
                <br>
                <br>
                <br>

                <script>
                    var links = document.getElementsByClassName('edit');
                    var formContainer = document.getElementById('formContainer');

// Lặp qua từng link và thêm sự kiện click
                    for (var i = 0; i < links.length; i++) {
                        links[i].addEventListener('click', function (event) {
                            event.preventDefault(); // Ngăn chặn hành vi mặc định của link

                            // Hiển thị form
                            formContainer.style.display = 'block';
                        });
                        var links = document.getElementsByClassName('edit');
                        var formContainer = document.getElementById('formContainer');
                        var password2Input = document.querySelector('#formContainer input[name="pass2"]');
                        var passwordInput = document.querySelector('#formContainer input[name="pass"]');
                        var statusInputs = document.querySelectorAll('#formContainer input[name="status"]');
                        var roleInputs = document.querySelectorAll('#formContainer input[name="role"]');


// Lặp qua từng link và thêm sự kiện click
                        for (var i = 0; i < links.length; i++) {
                            links[i].addEventListener('click', function (event) {
                                event.preventDefault(); // Ngăn chặn hành vi mặc định của link

                                // Lấy mật khẩu từ thuộc tính data-password của link
                                var password2 = this.getAttribute('data-password2');
                                var password = this.getAttribute('data-password');
                                var status = this.getAttribute('data-status');
                                var role = this.getAttribute('data-role');

                                // Hiển thị form
                                formContainer.style.display = 'block';

                                // Đặt giá trị mật khẩu cho input
                                password2Input.value = password2;
                                passwordInput.value = password;

                                for (var j = 0; j < statusInputs.length; j++) {
                                    if (statusInputs[j].value === status) {
                                        statusInputs[j].checked = true;
                                        break;
                                    }
                                }

                                for (var k = 0; k < roleInputs.length; k++) {
                                    if (roleInputs[k].value === role) {
                                        roleInputs[k].checked = true;
                                        break;
                                    }
                                }
                            });
                        }


                    }
                    var cancelButton = document.querySelector('#formContainer input[type="button"]');
                    cancelButton.addEventListener('click', function (event) {
                        event.preventDefault(); // Ngăn chặn hành vi mặc định của nút

                        // Ẩn form
                        formContainer.style.display = 'none';
                    });
                </script>
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

               


                </body>
                </html>

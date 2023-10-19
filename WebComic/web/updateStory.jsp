<%-- 
    Document   : profile
    Created on : Jul 3, 2023, 8:33:55 AM
    Author     : HP
--%>
<%@ page import="model.Category" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>@
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
  <title>Admin</title>
        <style>
           
            .change{
                align-items: center;
                background: gray;
                color: white;
                padding-left: 20px;
            }
        </style>
    </head>
    <body>
        <!-- Header Section Begin -->
        <%@include file="headerAd.jsp"  %>
        <!-- Header End -->
        <div class="container rounded bg-white mt-5 mb-5">
            <div class="row">
                <div class="col-md-3 border-right">
                    <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class=" mt-5" width="150px" src="${s.image}"><span class="font-weight-bold">${sessionScope.account.fname} ${sessionScope.account.lname}</span>
                        <br>
                        
                </div>
                </div>


                <div class="col-md-5 border-right">
                    <form action="updateSt?id=${s.s_id}" method="post">
                        <div class="p-3 py-5">
                            <div class="d-flex justify-content-between align-items-center mb-3">
                                <h4 class="text-right">Story ID : ${s.s_id}</h4>
                            </div>
                            <div class="row mt-2">
                                <div class="col-md-12">
                                    <label class="labels">Story Name</label>
                                    <input type="text" class="form-control" placeholder="first name" name="sname" value="${s.s_name}" required>
                                </div>
                                <div class="col-md-12">
                                    <label class="labels">Author</label>
                                    <input type="text" class="form-control" name="lname" placeholder="aname" value="${s.a.a_name}" readonly>
                                </div>
                            </div>

                            <div class="row mt-3">
                                <div class="col-md-8">

                                    <label class="labels">Country</label><br>
                                    <input type="radio" name="country" value="1" ${s.country == 1 ? "checked" : ""} required> Việt Nam &nbsp;&nbsp; 
                                    <input type="radio" name="country" value="2" ${s.country == 2 ? "checked" : ""}> Hàn Quốc </br> 
                                    <input type="radio" name="country" value="3" ${s.country == 3 ? "checked" : ""}> Trung Quốc &nbsp;&nbsp; 
                                    <input type="radio" name="country" value="3" ${s.country == 3 ? "checked" : ""}> Nhật Bản
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-md-11">
                                    <label class="labels">Category</label><br>

                                    <c:forEach items="${requestScope.ca}" var="ca">
                                        <c:set var="checked" value="false" />
                                        <c:forEach items="${requestScope.cast}" var="cast">
                                            <c:if test="${ca.ca_id == cast.ca_id}">
                                                <c:set var="checked" value="true" />
                                            </c:if>
                                        </c:forEach>
                                        <input type="checkbox" name="category" value="${ca.ca_id}" <c:if test="${checked}">checked</c:if>> ${ca.ca_name} &nbsp;&nbsp;
                                    </c:forEach>




                                </div>
                            </div>

                            <div class="mt-5 text-center">
                                <input class="btn btn-primary profile-button" type="submit" value="Save profile">
                            </div>
                        </div>
                    </form>

                </div>

                <div class="col-md-4">
                    <div class="p-3 py-5">
                        <div id="formContainer">
                            <form action="addChap?id=${s.s_id}" method="POST">
                                <label for="stringInput">ADD new chapter</label>
                                <input type="text" name="chNumber">
                                <input type="submit" value="Submit">
                            </form>
                        </div>
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <h4 class="text-right">Chapter </h4>
                            <a href="#" class="btn btn-danger" data-toggle="modal" onclick="sendData()"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a>
                        </div>
                        <table class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th></th>
                                    <th> <span class="custom-checkbox">
                                            <input type="checkbox" id="selectAll" onclick="selectAllCheckboxes()">
                                            <label for="selectAll"></label>
                                        </span></th>

                                </tr>
                            </thead>
                            <tbody>

                                <c:set var="count" value="1" />
                                <c:forEach items="${requestScope.ch}" var="ch">
                                    <tr>
                                        <td> Chapter: ${ch.ch_number} </td>
                                        <td>
                                            <span class="custom-checkbox">
                                                <input type="checkbox"  name="options[]" value="${ch.ch_id}">
                                                <label for="checkbox${count}"></label>
                                        </td>

                                        </span>
                                    </tr>
                                </c:forEach> 




                            </tbody>



                        </table>

                    


                        



                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Footer Section Begin -->
<%@include file="footer.jsp" %>
<!-- Footer Section End -->

<script type="text/javascript">
    function doDelete(id) {
        if (confirm("Are you want to delete ?")) {
            window.location = "deleteSt?id=" + id;
        }
    }
</script>


<script type="text/javascript">
  function sendData() {
  var checkboxes = document.querySelectorAll('input[name="options[]"]:checked');
  var values = Array.from(checkboxes).map(function (checkbox) {
    return checkbox.value;
  });

  if (values.length > 0) {
    var confirmDelete = confirm("Are you sure you want to delete?");
    if (confirmDelete) {
      var form = document.createElement('form');
      form.method = 'POST';
      form.action = 'deleteCh';

      values.forEach(function (value) {
        var input = document.createElement('input');
        input.type = 'hidden';
        input.name = 'selectedItems';
        input.value = value;
        form.appendChild(input);
      });

      // Add ID input field
      var idInput = document.createElement('input');
      idInput.type = 'hidden';
      idInput.name = 'id';
      idInput.value = '${s.s_id}';
      form.appendChild(idInput);

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
<!--Add Chap-->

</body>
</html>

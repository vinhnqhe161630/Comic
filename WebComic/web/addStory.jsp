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
                  

                </div>


                <div class="col-md-5 border-right">
                    <form action="addSt" method="post">
                        <div class="p-3 py-5">
                            <div class="d-flex justify-content-between align-items-center mb-3">
                               
                            </div>
                            <div class="row mt-2">
                                <div class="col-md-12">
                                    <label class="labels">Story Name</label>
                                    <input type="text" class="form-control" placeholder="Enter Story name" name="sname" required>
                                </div>
                                <div class="col-md-12">
                                    <label class="labels">Image</label>
                                    <input type="text" class="form-control" placeholder="Enter Image  " name="img" required>
                                </div>
                                <div class="col-md-12">
                                    <label class="labels">Author</label>
                                    <input type="text" class="form-control" name="aname" placeholder="Enter name author" >
                                </div>
                            </div>

                            <div class="row mt-3">
                                <div class="col-md-8">

                                    <label class="labels">Country</label><br>
                                    <input type="radio" name="country" value="1" required> Việt Nam &nbsp;&nbsp; 
                                    <input type="radio" name="country" value="2" > Hàn Quốc </br> 
                                    <input type="radio" name="country" value="3" > Trung Quốc &nbsp;&nbsp; 
                                    <input type="radio" name="country" value="3" > Nhật Bản
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-md-11">
                                    <label class="labels">Category</label><br>

                                    <c:forEach items="${requestScope.ca}" var="ca">
                                        
                                        <input type="checkbox" name="category" value="${ca.ca_id}" > ${ca.ca_name} &nbsp;&nbsp;
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

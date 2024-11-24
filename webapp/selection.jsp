<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Course List</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="css/style.css">

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" integrity="sha512-5A8nwdMOWrSz20fDsjczgUidUBR8liPYU+WymTZP1lmY9G6Oc7HlZv156XqnsgNUzTyMefFTcsFH/tnJE/+xBg==" crossorigin="anonymous" referrerpolicy="no-referrer" />


</head>
<body>
<% 
    // Retrieve the student ID from the request attributes
    Integer student = (Integer) request.getAttribute("student");
    // Define the form action URL
    String url = "enrollServlet";
%>
	<nav class="navbar navbar-expand-lg navbar-light bg-dark ">
  <div class="container-fluid ">
   
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0 d-flex justify-content-between w-100">
       
        <li class="nav-item">
       <form action="enrollServlet" method="GET">
    <input type="hidden" name="student" value="<%= student %>">
    <button class="btn btn-dark fs-5" type="submit" >Go to Enrolled Courses</button>
</form>
       
        </li>
         <a class="nav-link active btn btn-dark fs-5" aria-current="page" href="http://localhost:8080/CourseManagement/studentLogin.jsp" style="color: white; font-size: 17px;">LOGOUT</a>
      
      </ul>
     
    </div>
  </div>
</nav>

<%-- 
<% int student = (int) request.getAttribute("student");
	String url = "enrollServlet?studid="+ student;
	out.println(student);
%> --%>



<h4>Courses List</h4>
<div style="margin: 0 auto; width: 40rem" >
<form method="post" action=<%= url %>>
     <input type="hidden" name="studid" value="<%= student %>">
<label >Enter course Id you want to Enroll:</label>
<input type="number" name="courseId" required>

  <input type="submit" value="Enroll" name="">
<div class="text-danger text-center"><%= request.getAttribute("idError") != null ? request.getAttribute("idError") : "" %></div>
</form>

</div>
<div class="container">
<table class="table">
  
  <div class="red"></div>
  <tbody>
<%@ page import="java.util.List" %>
<%@ page import="courseManagementDTO.courseDTO" %>
<%
    Object obj = request.getAttribute("courses");
    if (obj instanceof List<?>) {
        List<?> tempList = (List<?>) obj;
        if (!tempList.isEmpty() && tempList.get(0) instanceof courseDTO) {
            List<courseDTO> courses = (List<courseDTO>) tempList;
%>
            <table class="table">
               <thead style="color: white;">
				    <tr bgcolor="#120671">
				      <th scope="col">Sr No |</th>
				      <th scope="col">Name|</th>
				      <th scope="col">Category |</th>
				      <th scope="col">Duration |</th>
				      <th scope="col">Fee |</th>
				      <th scope="col">Faculty |</th>
				      <th scope="col">IT |</th>
				      <th scope="col">Available</th>
				      
				    </tr>
				  </thead>
                <%
                    for (courseDTO course : courses) {
                %>
	                <tr>
	                    <td scope="col"><%= course.getCourseId() %></td>
	                    <td scope="col"><%= course.getCourseName() %></td>
	                    <td scope="col"><%= course.getCategory() %> </td>
	                    <td scope="col"><%= course.getDuration() > 12 ? (course.getDuration() / 12) + " years": course.getDuration() + " months" %> </td>
	                    <td scope="col"><%= course.getFees() %></td>
	                    <td scope="col"><%= course.getFaculty() %></td>
	                    <td scope="col"><%= course.getITCategory() %></td>
	                    <td scope="col"><%= course.getPref() %></td>
	                        <td scope="col">
				          </td>
	                </tr>
                <%
                    }
                %>
            </table>
<%
        } else {
            out.println("The list is empty or contains non-Course elements.");
        }
    } else {
        out.println("The attribute 'courses' is not a List.");
    }
%>
	
  </tbody>
</table>

</div>



</body>
</html>

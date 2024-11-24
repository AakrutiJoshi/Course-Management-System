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
	<nav class="navbar navbar-expand-lg navbar-light bg-dark">
  <div class="container-fluid">
   
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
       
        <li class="nav-item">
          <a class="nav-link active btn btn-dark fs-5" aria-current="page" href="http://localhost:8080/CourseManagement/add.jsp#" style="color: white; font-size: 17px;">Add Course</a>
        </li>
 <a class="nav-link active btn btn-dark fs-5" aria-current="page" href="http://localhost:8080/CourseManagement/login.jsp" style="color: white; font-size: 17px;">LOGOUT</a>
      <form action="studentManagement" method="get">
<!--         <input type="hidden" name="action" value="fulldetails"> -->
    <button class="btn btn-dark fs-5" type="submit" >Enroll Student</button>
      </ul>
     
    </div>
  </div>
</nav>




<h4>Course Management</h4>
<div class="container">
<table class="table">
  
  <div class="red"></div>
  <div class="text-danger"><%= request.getAttribute("deleteError") != null ? request.getAttribute("deleteError") : "" %></div>
  <tbody>
<%@ page import="java.util.List" %>
<%@ page import="courseManagementDTO.courseDTO" %>
<%@ page import="courseManagementDAO.courseDAO" %>
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
				      <th scope="col">Available |</th>
				      <th scope="col">Student counts |</th>
				      <th scope="col">Action</th>
				      
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
	                    <%
    courseDAO dao = new courseDAO();
    int studentCount = dao.getCount(course.getCourseId());
%>
	                    <td scope="col"><%= studentCount %></td>
	                        <td scope="col">
				        <a href="?action=delete&courseid=<%= course.getCourseId() %>"><i class="fa fa-trash" aria-hidden="true"></i></a>
				         <a href="?action=edit&courseid=<%= course.getCourseId() %>"><i class="fa fa-edit" aria-hidden="true"></i></a>
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
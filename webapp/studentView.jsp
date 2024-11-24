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
        			            <a class="nav-link active btn btn-dark fs-5" aria-current="page" href="http://localhost:8080/CourseManagement/courseServlet" style="color: white; font-size: 17px;">View Courses</a>
        			<li class="nav-item">
          				<a class="nav-link active btn btn-dark fs-5" aria-current="page" href="http://localhost:8080/CourseManagement/login.jsp" style="color: white; font-size: 17px;">LOGOUT</a>
        			</li>
      			</ul>
    		</div>
  		</div>
	</nav>

	<h4>Student Data</h4>
	<div class="container">
		<div class="text-danger">
			<%= request.getAttribute("deleteError") != null ? request.getAttribute("deleteError") : "" %>
		</div>

		<table class="table">
			<%@ page import="java.util.List" %>
			<%@ page import="studentDAO.studentDAO" %>
			<%@ page import="studentDTO.studentDTO" %>
			<%
				List<studentDTO> students = (List<studentDTO>) request.getAttribute("students");
			%>
			<thead style="color: white;">
				<tr bgcolor="#120671">
					<th scope="col">Student Id</th>
					<th scope="col">Student Name</th>
					<th scope="col">Date of Birth</th>
					<th scope="col">Email</th>
					<!-- <th scope="col">Action</th> -->
				</tr>
			</thead>
			<tbody>
				<%
					if (students != null) {
						for (studentDTO student : students) {
				%>
				<tr>
					<td scope="col"><%= student.getStudenID() %></td>
					<td scope="col"><%= student.getStudentName() %></td>
					<td scope="col"><%= student.getDob() %></td>
					<td scope="col"><%= student.getEmail() %></td>
					<td scope="col">
					<%-- 	<a href="?action=delete&studentId=<%= student.getStudenID() %>"><i class="fa fa-trash" aria-hidden="true"></i></a>
						<a href="?action=edit&studentId=<%= student.getStudenID() %>"><i class="fa fa-edit" aria-hidden="true"></i></a>
				 --%>	</td>
				</tr>
				<%
						}
					}
				%>
				
				
			</tbody>
		</table>
	<div style="margin: 0 auto; width: 40rem" >
<form method="post" action="studentManagement">
<label >Enter sudent Id :</label>
     <input type="number" name="studid" required>
<label >Enter course Id:</label>
<input type="number" name="courseId" required>
  <input type="submit" value="Enroll" name="">
  <div class="text-danger text-center"><%= request.getAttribute("idError") != null ? request.getAttribute("idError") : "" %></div>
	</div>
</body>
</html>

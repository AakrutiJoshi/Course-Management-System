<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Edit Course</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="css/style.css">

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

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
                <li class="nav-item">
            <a class="nav-link active btn btn-dark fs-5" aria-current="page" href="http://localhost:8080/CourseManagement/courseServlet" style="color: white; font-size: 17px;">View Courses</a>
        </li>
 <a class="nav-link active btn btn-dark fs-5" aria-current="page" href="http://localhost:8080/CourseManagement/login.jsp" style="color: white; font-size: 17px;">LOGOUT</a>

      </ul>
     
    </div>
  </div>
</nav>



<h4>Course Management</h4>
<%@ page import="java.util.List" %>
<%@ page import="courseManagementDTO.courseDTO" %>
<%@ page import="MyLogger.loggerClass" %>
<form method="post" action="courseServlet">

<%
    // Get the course attribute and cast it to the correct type
    courseDTO course = (courseDTO) request.getAttribute("courses");
%>
  
<input type="hidden" name="courseid" value="<%= course.getCourseId() %>">
<div class="container forn-dlt">
<div class="row g-3 align-items-center">
  <div class="col-auto col-lg-4">
    <label for="inputPassword6" class="col-form-label">Course ID :</label>
  </div>
  <div class="col-auto col-lg-8">
    <input type="text" id="inputPassord6" name="coursename" value="<%= course.getCourseId() %>" class="form-control" aria-describedby="passwordHelpInline" disabled> 
  </div>

  <div class="col-auto col-lg-4">
    <label for="inputPassword6" class="col-form-label">Course Name :</label>
  </div>
  <div class="col-auto col-lg-8">
    <input type="text" id="inputPassord6" name="coursename" value="<%= course.getCourseName() %>" class="form-control" aria-describedby="passwordHelpInline">
  <div class="text-danger"><%= request.getAttribute("nameError") != null ? request.getAttribute("nameError") : "" %></div>
  </div>
</div>

<div class="row g-3 align-items-center">
  <div class="col-auto col-lg-4">
    <label for="inputPassword6" class="col-form-label" >Course Duration :</label>
  </div>
  <div class="col-auto col-lg-8">
    <input type="text" id="inputPassword6" name="duration" value="<%= course.getDuration() %>" class="form-control" aria-describedby="passwordHelpInline">
  <div class="text-danger"><%= request.getAttribute("durationError") != null ? request.getAttribute("durationError") : "" %></div>
  </div>
</div>

<div class="row g-3 align-items-center">
  <div class="col-auto col-lg-4">
    <label for="inputPassword6" class="col-form-label" >Category :</label>
  </div>
  <div class="col-auto col-lg-8">
   <select class="form-select" aria-label="Default select example" name="category">
	 <!--  <option selected>Select Category</option> -->
	 <option value="Health" <%= "Health".equals(course.getCategory()) ? "selected" : "" %>>Health</option>
	    <option value="Education" <%= "Education".equals(course.getCategory()) ? "selected" : "" %>>Education</option>
	    <option value="Sports" <%= "Sports".equals(course.getCategory()) ? "selected" : "" %>>Sports</option>
	    <option value="fun" <%= "fun".equals(course.getCategory()) ? "selected" : "" %>>fun</option>
</select>
  </div>
</div>

<div class="row g-3 align-items-center">
  <div class="col-auto col-lg-4">
    <label for="inputPassword6" class="col-form-label">Available?</label>
  </div>
   <div class="col-auto col-lg-2">  
        <input class="form-check-input" type="radio" name="pref"  value="Online" <%= "Online".equals(course.getPref()) ? "checked" : "" %> id="prefOnline">
        <%
        String p=course.getPref(); 
        loggerClass.writeToLog(p);
        %>
        
        <label class="form-check-label" for="prefOnline">Online</label>
    </div>
    <div class="col-auto col-lg-2">
        <input class="form-check-input" type="radio" name="pref" value="Offline" <%= "Offline".equals(course.getPref()) ? "checked" : "" %> id="prefOffline">
        <label class="form-check-label" for="prefOffline">Offline</label>
    
    </div>
     <div class="col-auto col-lg-2">
        <input class="form-check-input" type="radio" name="pref" value="Both" <%= "Both".equals(course.getPref()) ? "checked" : "" %> id="prefBoth">
        <label class="form-check-label" for="prefBoth">Both</label>
    </div>
    
</div>
<div class="row g-3 align-items-center">
  <div class="col-auto col-lg-4">
    <label for="inputPassword6" class="col-form-label" >Course Fee :</label>
  </div>
  <div class="col-auto col-lg-8">
    <input type="text" id="inputPassword6" name="fees" value="<%= course.getFees() %>" class="form-control" aria-describedby="passwordHelpInline">
  <div class="text-danger"><%= request.getAttribute("feesError") != null ? request.getAttribute("feesError") : "" %></div>
  </div>
</div>

<div class="row g-3 align-items-center">
  <div class="col-auto col-lg-4">
    <label for="inputPassword6" class="col-form-label">Faculty :</label>
  </div>
  <div class="col-auto col-lg-8">
    <input type="text" id="inputPassord6" name="faculty" value="<%= course.getFaculty() %>" class="form-control" aria-describedby="passwordHelpInline">
    <div class="text-danger"><%= request.getAttribute("facultyError") != null ? request.getAttribute("facultyError") : "" %></div>
  </div>
</div>


<div class="row g-3 align-items-center">
  <div class="col-auto col-lg-4">
    <label for="inputPassword6" class="col-form-label">IT Category?</label>
  </div>
  <div class="col-auto col-lg-8">
    <input type="checkbox" id="inputPassword6" name="IT"<%= course.getITCategory() == true ? "checked":"" %>>

  </div>
</div>

<div class="row g-3 align-items-centerr">
  <div class="col-auto col-lg-4">
    <label for="inputPassword6" class="col-form-label"></label>
  </div>
  <div class="col-auto col-lg-8">
   <input type="submit" value="Update Course" name="">
  </div>
</div>

</div>
</form>
</body>
</html>
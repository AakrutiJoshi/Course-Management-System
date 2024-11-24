
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Login</title>
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
    <a class="nav-link active" aria-current="page" href="http://localhost:8080/CourseManagement/indexPrev.jsp#" style="color: white; font-size: 17px;">Home</a>
       
    
    
    <div class="collapse navbar-collapse" id="navbarSupportedContent">    
    </div>
  </div>
</nav>


<h4>STUDENT REGISTRATION PAGE</h4>
<form method="post" action="studentRegisterServlet">
<div class="container forn-dlt">
<div class="row g-3 align-items-center">
  <div class="col-auto col-lg-4">
    <label for="inputPassword6" class="col-form-label"> Name :</label>
  </div>
  <div class="col-auto col-lg-8">
    <input type="text" id="inputPassord6" name="username" class="form-control" aria-describedby="passwordHelpInline">
        <div class="text-danger"><%= request.getAttribute("nameError") != null ? request.getAttribute("nameError") : "" %></div>
  </div>
 </div> 
 <div class="row g-3 align-items-center">
  <div class="col-auto col-lg-4">
    <label for="inputPassword6" class="col-form-label"> Date of birth :</label>
  </div>
  <div class="col-auto col-lg-8">
    <input type="date" id="inputPassord6" name="dob" class="form-control" aria-describedby="passwordHelpInline">
        <div class="text-danger"><%= request.getAttribute("dateError") != null ? request.getAttribute("dateError") : "" %></div>
  </div>
 </div> 
  
  <div class="row g-3 align-items-center">
  <div class="col-auto col-lg-4">
    <label for="inputPassword6" class="col-form-label"> Email :</label>
  </div>
  <div class="col-auto col-lg-8">
    <input type="email" id="inputPassord6" name="email" class="form-control" aria-describedby="passwordHelpInline">
        <div class="text-danger"><%= request.getAttribute("emailError") != null ? request.getAttribute("emailError") : "" %></div>
  </div>
 </div> 
  
  <div class="row g-3 align-items-center">
  <div class="col-auto col-lg-4">
    <label for="inputPassword6" class="col-form-label"> Password :</label>
  </div>
  <div class="col-auto col-lg-8">
    <input type="password" id="inputPassord6" name="password" class="form-control" aria-describedby="passwordHelpInline">
        <div class="text-danger"><%= request.getAttribute("passwordError") != null ? request.getAttribute("passwordError") : "" %></div>
  </div>
</div>
    <label for="inputPassword6" class="col-form-label"> </label>
  <div style="text-align: center;">
    <button type="submit" >Register</button>
  </div>
</div>
</form>

</body>
</html>
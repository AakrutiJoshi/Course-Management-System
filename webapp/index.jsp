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
            <h4 style="color: white; ">WELCOME ADMIN PORTAL</h4>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">    
            </div>
        </div>
    </nav>
<table>
    <div class="container text-center my-5">
        <div class="row g-3 justify-content-center">
            <div class="col-auto col-lg-4">
                <a href="http://localhost:8080/CourseManagement/regisration.jsp" class="text-decoration-none text-dark">
                    <img src="reg.png" alt="Course Management" class="img-fluid mb-3">
                    <h2>Sign up</h2>
                </a>
            </div>
            <div class="col-auto col-lg-4">
                <a href="http://localhost:8080/CourseManagement/login.jsp" class="text-decoration-none text-dark">
                    <img src="login.png" alt="Course Management" style="height: 13rem" class="img-fluid mb-3 mt-3">
                    <h2>Login</h2>
                </a>
            </div>
        </div>
    </div>
</table>

</body>
</html>

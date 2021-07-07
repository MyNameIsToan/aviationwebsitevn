<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <meta property="og:url"  			   content="https://aviationwebsite.herokuapp.com/viewphoto?photo=${model.getPhotoID()}" />
		<meta property="og:type"               content="website" />
		<meta property="og:title"              content="${model.airlines} | ${model.aircraft} | ${model.registration}" />
		<meta property="og:description"        content="" />
		<meta property="og:image"              content="data:image/jpeg;base64,${model.getPhoto()}" />
        <title>Aviation Website</title>
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link rel="stylesheet" href="./views/web/ViewPhoto/css/styles.css" />
    </head>
    <body>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="./homepage">AVIATION WEBSITE</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="./homepage">Home</a></li>
                        <%String condition = String.valueOf(request.getAttribute("condition"));
  							if(condition.equals("0")){%>
                       			 <li class="nav-item"><a class="nav-link" href="./views/web/Login/index.jsp">Upload</a></li>
                       			 <li class="nav-item"><a class="nav-link" href="./views/web/Login/index.jsp">Sign in</a></li>
                       			 <li class="nav-item"><a class="nav-link" href="./views/web/Register/index.html">Sign up</a></li>
                       		<%}else{%>
                       			 <li class="nav-item"><a class="nav-link" href="./views/web/Upload/index.jsp">Upload</a></li>
                       			 <li class="nav-item"><a class="nav-link" href="./Profile">Profile</a></li>
                       			 <li class="nav-item"><a class="nav-link" href="./screener-login">Screener</a></li>
                       			 <li class="nav-item"><a class="nav-link" href="./Signout">Sign out</a></li>
                       		<%}%>
                    </ul>
                     <form action="./Search" method="post">
                    	<input type="text" name="search" width="5em"/>
                    	<input type="submit" value="Search"/>
                    </form>
                </div>
            </div>
        </nav>
        <!-- Header-->
        <header class="bg-dark py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="text-center text-white">
                    <h1 class="display-4 fw-bolder">AVIATION WEBSITE</h1>
                    <p class="lead fw-normal text-white-50 mb-0">AN AVIATION PHOTOS SYSTEM</p>
                </div>
            </div>
        </header>
        <!-- Section-->
        <section class="py-5">
        	<img id="fullphoto"src="data:image/jpeg;base64,${model.getPhoto()}" width="100%"/><br/>
        	<p>
        	 		${model.username}<br/>
                    ${model.airlines}<br/>
                    ${model.aircraft}<br/>
                    ${model.registration}<br/>
                    ${model.location}<br/>
                    Taken Date: ${model.getTakenDate()} <br/>
					Upload Date: ${model.getUploadDate()} <br/>
                    Like: ${model.like}&nbsp;&nbsp;&nbsp;View: ${model.view}
        	</p>
        	<form action="./viewphoto" method="post">
    				<input type="hidden" name="photo" value="${model.getPhotoID()}"/>  
    				<input type="submit" name="submit"value="Like" class="text-center btn btn-outline-dark mt-auto">             
				</form>
        </section>
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white"></p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>

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
        <title>Aviation Website</title>
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link rel="stylesheet" href="./views/screener/Photo/css/styles.css" />
<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 15px;
}
</style>
    </head>
    <body>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="./homepage">AVIATION WEBSITE</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                         		 <li class="nav-item"><a class="nav-link active" aria-current="page" href="./screener-allphoto">Photos</a></li>
                       			 <li class="nav-item"><a class="nav-link" href="./screener-controll">Queue</a></li>
                       			 <li class="nav-item"><a class="nav-link" href="./screener-signout">Sign out</a></li>
                    </ul>
                    <form action="./screener-queue-search" method="post">
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
            <div class="container px-4 px-lg-5 mt-5">
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
               <table style="width: 100%">
		<c:forEach var="item" items="${model.getListResult()}">
			<tr>
				<td><img src="data:image/jpeg;base64,${item.getPhoto()}" width="320px" height="180px"/>
				</td>
				<td>
					${item.username}<br/>
					${item.airlines}<br/>
					${item.aircraft}<br/>
					${item.registration}<br/>
					${item.location}<br/>
					Taken Date: ${item.getTakenDate()} <br/>
					Upload Date: ${item.getUploadDate()}
				<div class="text-start"><a class="btn btn-outline-dark mt-auto" href="./screener-queuephoto?photo=${item.getPhotoID()}">View Photo</a></div><br/>
				<form action="./acceptedphoto" method="post">
    				<input type="hidden" name="username" value="${item.username}"/>  
    				<input type="hidden" name="photo" value="${item.getPhoto()}"/>  
    				<input type="hidden" name="airlines" value="${item.airlines}"/>  
    				<input type="hidden" name="aircraft" value="${item.aircraft}"/>  
    				<input type="hidden" name="registration" value="${item.registration}"/>     
    				<input type="hidden" name="location" value="${item.location}"/>   
    				<input type="hidden" name="takendate" value="${item.getTakenDate()}"/>  
    				<input type="hidden" name="uploaddate" value="${item.getUploadDate()}"/>               
    				<input type="submit" name="submit"value="Accept" class="text-center btn btn-outline-dark mt-auto">    
    				<input type="submit" name="submit"value="Delete" class="text-center btn btn-outline-dark mt-auto">             
				</form>
				</td>
			</tr>
		</c:forEach>
	</table>
                </div>
            </div>
        </section>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>

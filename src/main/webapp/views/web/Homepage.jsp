<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8 ">
<title>Homepage</title>
<style>
*{
	margin:0;
	padding:0;
	width: 100%;
}

.card {
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  transition: 0.3s;
  width: 20%;
  border-radius: 5px;
  margin-left: 50px;
  margin-top: 40px;
}

.card:hover {
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}

img {
  border-radius: 5px 5px 0 0;
}
.container {
  padding: 2px 16px;
}
.WebName{
	width: 100%;
	height: 20em;
	text-align: center;
	background-color:  #b3b3b3;
	margin-top:2em;
}
.WebName h1{
	display: block;
  	margin-left: auto;
  	margin-right: auto;
	color: white;
	line-height: 5em;
	font-size: 60px;
	font-family: arial;
}
.content{
	width: 100%;
	margin-left: 20px;
}
.content .card{
	display:inline-table;
}
.footer{
	width: 100%;
	height: 5em;
	background-color: black;
	margin-top: 2em;
}
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #333;
}

li a {
  float: left;
  display: inline-block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  width: 5em;
}

li a:hover {
  background-color: #111;
}
</style>
</head>
<body>
	<ul>
  		<li><a href="#">Home</a></li>
  		<%String condition = String.valueOf(request.getAttribute("condition"));
  			if(condition.equals("0")){%>
  				<li><a href="./views/web/Login/index.jsp">Upload</a></li>
  				<li><a href="./views/web/Login/index.jsp">Sign in</a></li>
  		 		<li><a href="./views/web/Register/index.html">Sign up</a></li>
  			<%}else{%>
  				<li><a href="./views/web/Upload/index.jsp">Upload</a></li>
  				<li><a href="#">Profile</a></li>
  				<li><a href="./Signout">Sign out</a></li>
  			<%}%>
	</ul>
	<diV class="header">
		<div class="WebName">
			<h1>AVIATION WEBSITE</h1>
		</div> 
	</div>
	<div class="content">
	<c:forEach var="item" items="${model.getListPhotos()}">
		<div class="card">
  			<img src="./image/${item.getPhoto()}" alt="Avatar" style="width:100%">
  			<div class="container">
   				<p>
					${item.username}<br/>
					${item.airlines}<br/>
					${item.aircraft}<br/>
					${item.registration}<br/>
					${item.location}<br/>
					Taken Date: ${item.getTakenDate()} <br/>
					Upload Date: ${item.getUploadDate()} <br/>
					Like: ${item.getLike()}&nbsp;&nbsp;&nbsp;View: ${item.getView()}
				</p> 
  			</div>
		</div>		
	</c:forEach>
	</div>
	<div class="footer"></div>
</body>
</html>
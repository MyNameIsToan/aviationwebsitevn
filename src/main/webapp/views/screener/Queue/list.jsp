<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8 ">
<title>Queue</title>
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
	<table style="width: 100%">
		<c:forEach var="item" items="${model.getListResult()}">
			<tr>
				<td><img src="./image/${item.getPhoto()}" width="320px" height="180px"/>
				</td>
				<td>
					${item.username}<br/>
					${item.airlines}<br/>
					${item.aircraft}<br/>
					${item.registration}<br/>
					${item.location}<br/>
					Taken Date: ${item.getTakenDate()} <br/>
					Upload Date: ${item.getUploadDate()}
				<form action="./acceptedphoto" method="post">
    				<input type="hidden" name="username" value="${item.username}"/>  
    				<input type="hidden" name="photo" value="${item.getPhoto()}"/>  
    				<input type="hidden" name="airlines" value="${item.airlines}"/>  
    				<input type="hidden" name="aircraft" value="${item.aircraft}"/>  
    				<input type="hidden" name="registration" value="${item.registration}"/>     
    				<input type="hidden" name="location" value="${item.location}"/>   
    				<input type="hidden" name="takendate" value="${item.getTakenDate()}"/>  
    				<input type="hidden" name="uploaddate" value="${item.getUploadDate()}"/>               
    				<input type="submit" name="submit"value="Accept" style="background-color: blue; color: white;">    
    				<input type="submit" name="submit"value="Delete" style="background-color: red; color: white;">             
				</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>

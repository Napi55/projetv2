<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>
  
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="/WEB-INF/user-list.css">
	
		<style><%@include file="/WEB-INF/header2.css"%></style>
<style><%@include file="/WEB-INF/header.css"%></style>
<style><%@include file="/WEB-INF/acueilAdmin.css"%></style>
</head>
<body>

	<header>
	<jsp:include page="header.jsp"/>

<br>
<br>
<br>	
  <br>	  
 <ul id="serv">
<li class="service"><a class="active" href="acueilAdmin2">Home</a></li>
  <li class="service"><a href="ListTeachers">List of Teachers</a></li>
  <li class="service"><a href="listUser">List of Students</a></li>
  <li class="service"><a href="listAdmin">List of Admins</a></li>
  
</ul>
<br>

		
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Admin</h3>
			<hr>
			

	<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New Admin</a>
			</div>
			
	
		
					
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Email</th>
						<th>Username</th>
						<th>Password</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="admin1" items="${listUser}">

						<tr>
							<td><c:out value="${admin1.id}" /></td>
							<td><c:out value="${admin1.name}" /></td>
							<td><c:out value="${admin1.email}" /></td>
							<td><c:out value="${admin1.username}" /></td>
							<td><c:out value="${admin1.password}" /></td>
							<td>
								<c:if test="${!empty sessionScope.admin}">
							<a href="editAdmin?id=<c:out value='${admin1.id}' />"><input  class="input" type="button" value="Edit"></a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								 <a href="deleteAdmin?id=<c:out value='${admin1.id}' />"><input  class="input" type="button" value="Delete"></a>
							</c:if>
							
							
							
						
							
							
							
							
							
							
							
							</td>
						</tr>
					</c:forEach>
		
				</tbody>

			</table>
			
		</div>
	
	</div>
	
</body>
</html>
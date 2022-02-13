

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
</head>
<body>

	<header>

	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${user != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${user == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${user != null}">
            			Edit User
            		</c:if>
						<c:if test="${user == null}">
            			Add New User
            		</c:if>
					</h2>
				</caption>

				<c:if test="${user != null}">
					<input type="hidden" name="idAT" value="<c:out value='${user.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Firstname</label> <input type="text"
						value="<c:out value='${user.Firstname}' />" class="form-control"
						name="Firstname" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Lastname</label> <input type="text"
						value="<c:out value='${user.lastname}' />" class="form-control"
						name="Lastname">
				</fieldset>

				<fieldset class="form-group">
					<label>Email</label> <input type="text"
						value="<c:out value='${user.email}' />" class="form-control"
						name="emailAT">
				</fieldset>
					<fieldset class="form-group">
					<label>UserName</label> <input type="text"
						value="<c:out value='${user.username}' />" class="form-control"
						name="UsernameAT">
				</fieldset>
					<fieldset class="form-group">
					<label>Password</label> <input type="text"
						value="<c:out value='${user.password}' />" class="form-control"
						name="Password">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
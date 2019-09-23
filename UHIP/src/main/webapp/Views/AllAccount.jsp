<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" charset="utf8"
	src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<script type="text/javascript">
	$(function() {
		$("#alladmin").dataTable();
	})
</script>
<script>
	function showAlert() {
		confirm("Are You Sure To Activate ");
	}
</script>
<script>
	function showAlertt() {
		confirm("Are You Sure To Deactivate ");
	}
</script>
<%@ include file ="Menu.jsp" %>
</head>

<body>
	<center>
		<h3>${status}</h3>
	</center>
	<table class="display" id="alladmin" style="width: 100%" bgcolor="gold">
		<thead>
			<tr>
				<th>SNO</th>
				<th>FIRST NAME</th>
				<th>LAST NAME</th>
				<th>EMAIL</th>
				<th>DOB</th>
				<th>ROLE</th>
				<th>ACTION</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${allaccount}" var="a">
				<tr>
					<td><c:out value="${a.userAccId}"></c:out></td>
					<td><c:out value="${a.firstName}"></c:out></td>
					<td><c:out value="${a.lastName}"></c:out></td>
					<td><c:out value="${a.email}"></c:out></td>
					<td><c:out value="${a.dob }"></c:out></td>
					<td><c:out value="${a.role}"></c:out>
					<td> 
					<a href="/edit?id=${a.userAccId}">Edit </a>
					<c:if test="${a.active=='Y'}">
							<a href="/deactivate?id=${a.userAccId}" onclick="showAlert()">Deactivate</a>
						</c:if> <c:if test="${a.active=='N'}">
							<a href="/activate?id=${a.userAccId}" onclick="showAlertt()">Activate</a>
						</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
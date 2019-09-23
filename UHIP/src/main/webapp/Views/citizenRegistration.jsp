<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
body {
height: 200px;
  background-color: red; /* For browsers that do not support gradients */
  background-image: linear-gradient(to right, red , yellow); /* Standard syntax (must be last) */
  
}
</style>
<style>
h1 {
  background-color: green;
}
</style>
</head>
<h1 align="left" >Citizen Registration</h1>
<body>
	<form:form action="/Registration" method="POST"
		modelAttribute="citizenregistration" >
		<table align="left" height="60%" width="60%">
			<tr>
				<td>First Name</td>
				<td><form:input path="firstName" /></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><form:input path="lastName" /></td>
			</tr>
			<tr>
				<td>Mail</td>
				<td><form:input path="email"></form:input></td>
			</tr>
			<tr>
				<td>Plan Name</td>
				<td><form:input path="planName"></form:input></td>
			</tr>
			
			<tr>
				<td>SSN</td>
				<td><form:input path="ssn"></form:input></td>
			</tr>
			<tr>
				<td>ADDRESS</td>
				<td><form:input path="address" /></td>
			</tr>
			<tr>
				<td>INCOME</td>
				<td><form:input path="income"></form:input></td>
			</tr>
			<td id="dob">DOB</td>
			<td><form:input path="Dob"></form:input></td>
			<tr>
			<td>GENDER</td>
			<td><form:checkboxes items="${genders}" path="gender"></form:checkboxes></td>
			</tr>
			<tr>
			<td>KIDS</td>
			<td><form:input path="kids"></form:input></td>
			</tr>
			<tr>
			<td align="center" colspan="1"><input type="submit" value="submit" id="btn" ></td>
			</tr>
		</table>
	</form:form>
</body>
</html>
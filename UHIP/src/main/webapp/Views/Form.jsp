<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>


<script
	srs="https:ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#emailid").blur(function() {
			var email = $("#emailid").val();
			$.ajax({
				type : "GET",
				url : "/emailvalid/" + email,
				success : function(response) {
					$("#errMsg").html(response);
				}
			});
		});
	});
</script>
<script>
	$(function() {
		$("#datePicker").datepicker();
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@ include file ="Menu.jsp" %>
</head>
<center>${Success}</center>
<body>
	<h1>
		<center>Enter Account Details</center>
	</h1>
	
	<form:form action="/Register" method="POST" id="form"
		modelAttribute="useraccountmodel">
		<table border="1" color="blue" align="center" height="60%" width="50%">
			<tr>
				<td>FirstName</td>
				<td><form:input path="firstName"></form:input></td>
			</tr>
			<tr>
				<td>LastName</td>
				<td><form:input path="lastName"></form:input></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><form:input path="email" id="emailid"></form:input></td>
				<td><font color="red"><span id="errMsg"></span></font></td>
			</tr>
			<tr>
				<td>password</td>
				<td><form:input path="password"></form:input></td>
			</tr>
			<tr>
				<td>Dob</td>
				<td><form:input path="dob" id="datePicker" /></td>
			</tr>
			<tr>
				<td>Gender</td>
				<td><form:checkboxes items="${genders}" path="gender"></form:checkboxes></td>
			</tr>
			<tr>
				<td>SSN</td>
				<td><form:input path="ssn"></form:input></td>
			</tr>
			<tr>
				<td>Role</td>
				<td><form:select items="${roles}" path="role"></form:select></td>
			</tr>
			<tr>
			<td colspan="2"><a href="/allaccount">GetALLAccount</a></td>
			</tr>
			<tr>
				<td><input type="reset" value="reset"></td>
				<td><input type="submit" value="Registers" id="register"></td>
			</tr>
		</table>
	</form:form>

	<script>
		$(document).ready(function() {
			$("#form").validate({
				rules : {
					firstName : 'required',
					lastName : 'required',
					gender : 'required',
					ssn : 'required',
					role : 'required',
					dob : 'required',
					email: 'required',
					password: 'required'
					
					
				},
				messages : {

					firstName : 'First Name required',
					lastName : 'Last Name required',
					ssn  : 'ssn required',
					role : 'role required',
					dob  : 'Dob required',
					gender : 'gender required',
					email :'email reqired',
					password:'password required'
				}
			});
		});
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<TITLE>Assignment 5</TITLE>

<link rel="stylesheet" href="invitationStyle.css">

<script type="text/javascript" src="invitationValidatorJSP.js">
	
</script>
</head>
<body>

	<h1>INVITATION</h1>
	<p id="one">Please fill out the below information for the
		invitation</p>

	<form name="createEvent" method="post" action="eventInfo.jsp">
		
		<div>
			<label>User Name: </label> <input type="text" name="username"
				placeholder="Required"><br> <br>
		</div>
		
		<div>
			<label>Event Name: </label> <input type="text" name="eventname"
				placeholder="Required"><br> <br>
		</div>

		<div>
			<label>Date: </label> <input type="date" name="date"><br>
			<br>
		</div>

		<div>
			<label>Time: </label> <input type="time" name="time"><br>
			<br>
		</div>

		<div>
			<label> Location: </label> <input type="text" name="location"
				placeholder="Required"><br> <br>
		</div>

		<div>
			<label>Event Description: </label>
			<textarea name="description" rows="4" cols="50"
				placeholder="Enter details here..."></textarea>
			<br> <br>
		</div>

		<input type="submit" value="Submit" style='margin-right: 16px'
			name="submitbutton" onSubmit="invitationValidatorJSP" onClick="return validateForm()"> 
		
		<input type="reset" value="Reset" onSubmit="createEvent" name="resetbutton">
	</form>
	

</body>
</html>
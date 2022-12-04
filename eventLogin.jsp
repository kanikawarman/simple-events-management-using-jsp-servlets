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

<h1>Event Login</h1>

	<form name="eventLogin" method="POST" action="${pageContext.request.contextPath}/UserValidate">

		<section class="left-section">
			<div>
				
				<p id="one">Login to view events</p>
			</div>
			<div id="left-form" class="form fade-in-element">


				<div>
					<label>User Name: </label> <input type="text" name="username"
						placeholder="Required"><br> <br>
				</div>

				<div>
					<label>Password: </label> <input type="password" name="pwd"
						placeholder="Required"><br> <br>
				</div>
			
				<input type="submit" name="action" value="Login"
					style='margin-right: 16px' name="loginbutton" onSubmit="invitationValidatorJSP" onClick="return validateLogin()">
				<input type="reset" value="Reset" onSubmit="eventLogin"
					name="resetlogin">
			</div>
		</section>

		<section class="right-section">

			<div id="right-form" class="form form-hide" style = "position:relative; top:-60px;">

				<div>
					<p id="one">New User?</p>
				</div>
				<div>
					<label>User Name: </label> <input type="text" name="uname"
						placeholder="Required"><br> <br>
				</div>

				<div>
					<label>Password: </label> <input type="password" name="password"
						placeholder="Required"><br> <br>
				</div>

				<div>
					<label>Confirm Password: </label> <input type="password"
						name="confirmpwd" placeholder="Required"><br> <br>
				</div>
				
				<input type="submit" name="action" value="Register"
					style='margin-right: 16px' name="registerbutton" onSubmit="invitationValidatorJSP" onClick="return validateRegister()">
				<input type="reset" value="Reset" onSubmit="eventLogin"
					name="resetlogin">

			</div>
	
	</section>
	</form>


</body>
</html>
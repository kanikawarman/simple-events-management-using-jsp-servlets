<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import = "websys.eventBeans"%>
	<%@page import="java.sql.*,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<TITLE>Assignment 5</TITLE>

<link rel="stylesheet" href="invitationStyle.css">

</head>
<body>

<jsp:useBean id="eventbean" scope="session" class="websys.eventBeans" />
	<jsp:setProperty name="eventbean" property="*" />
 <%-- <% eventbean.insert();%>  --%>

	<h1>Event Details</h1>
	<p id="one" style = "position:relative; top: 30px">Confirm the details to register the event</p>
	<br>
	
	<form name="eventInfo" method="post" action="${pageContext.request.contextPath}/UserValidate">

		<div>
			<label><b>Event Name: </b></label>
			<jsp:getProperty name="eventbean" property="eventname"/>
			<br>
		</div>

		<div>
			<label><b>Date: </b></label>
			<jsp:getProperty name="eventbean" property="date"/>
			<br>
		</div>

		<div>
			<label><b>Time: </b></label>
			<jsp:getProperty name="eventbean" property="time"/>
			<br>
		</div>

		<div>
			<label><b>Location: </b></label>
			<jsp:getProperty name="eventbean" property="location"/>
			<br>
		</div>

		<div>
			<label><b>Event Description: </b></label>
		<jsp:getProperty name="eventbean" property="description"/>
			<br>
		</div>
		
		<div>
			<label><b>User name: </b></label>
		<jsp:getProperty name="eventbean" property="username"/>
			<br>
		</div>

		<p id="two">Click on Submit to register the event OR click on Edit Event to edit the details.</p>

		<input type="submit" value="Submit" style='margin-right: 16px' name="action"> 
		<input type="submit" value="Edit Event" name="editevent" formaction="createEvent.jsp">
			
	</form>
</body>
</html>

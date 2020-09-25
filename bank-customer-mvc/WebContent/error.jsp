<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Errors</title>
</head>
<h2>Errors:</h2>
<%
	Object error = request.getAttribute("errors");
	String errs = "";
	if (error != null) {
		errs = (String) error;
	}
%>
<body>
	<h3>Please provide valid input for the fields mentioned below</h3>
	<br />
	<font color="red"> <%=errs%></font>
	<br/>
	<br/>
		<a href="html/personalinfo.html">Back</a>
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%-- <%= id = session.getAttribute("id") %> --%>

<%
	Object pID = session.getAttribute("id");
	request.setAttribute("pid", pID);
%>

<body>
	<%-- <h3>Your Id is : ${id}</h3> --%>


	<form method="get" action="/bankcustomer/BankInfo">
		<input type="hidden" id="pid" name="pid" value="${id}" />

		<table>
			<tr>
				<td><label for="bank">Select your bank</label></td>
				<td><select id="bank" name="bank">
						<option value="American Savings">American Savings</option>
						<option value="Fifth Third">Fifth Third</option>
						<option value="Ohio Savings">Ohio Savings Bank</option>
						<option value="State Bank">State Bank</option>
						<option value="Key Bank">Key Bank</option>
				</select></td>
			</tr>
			<tr>
				<td><label for="account">Account no.</label></td>
				<td><input type="text" name="account" id="account"></td>
			</tr>
			<tr>
				<td><label for="ssn">SSN</label></td>
				<td><input type="text" name="ssn" id="ssn"></td>
			</tr>

		</table>
		<br> <br> <input type=submit name="submit" value="submit" />
	</form>
</body>
</html>
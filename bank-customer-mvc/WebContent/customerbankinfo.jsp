<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Info</title>
</head>
<body>
	<br />
	<font color="blue"><h2>Customer's Bank Info</h2></font>

	<h3> First Name : ${person.firstName}</h3>
	<h3> Last name : ${person.lastName}</h3>
	<h3> Gender : ${person.gender}</h3>
	<h3> Address : ${person.address}</h3>
	<h3> City : ${person.city}</h3>
	<h3> State: ${person.state}</h3>
	<h3> Country : ${person.country}</h3>
	<h3> Account No : ${person.accountNo}</h3>
	<h3> Bank name : ${person.bankName}</h3>
	<h3> SSN: xxxxx${person.ssn}</h3>
	
	<h3>Customer's Phone: ${person.phone}</h3>


</body>
</html>
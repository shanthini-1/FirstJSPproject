<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.chainsys.springmvc.pojo.Employee"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Fetch All Employee</title>
</head>
<body>
<div>
	<a href="index.jsp"> Index Page</a>
	</div>
	<div>
	<table>
		<thead>' Employee '</thead>
		<%
		Employee Emp = new Employee()request.getAttribute("result");
		%>
		<tr>
			<td><%=emp.getEmp_id()%></td>
			<td><%=emp.getFirst_name()%></td>
			<td><%=emp.getLast_name()%></td>
			<td><%=emp.getEmail()%></td>
			<td><%=emp.getHire_date()%></td>
			<td><%=emp.getJob_id()%></td>
			<td><%=emp.getSalary()%></td>
		</tr>
		</table>
</body>
</html>
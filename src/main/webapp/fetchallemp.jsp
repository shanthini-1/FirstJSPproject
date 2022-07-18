<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List,java.util.ArrayList"%>
<%@ page import="com.chainsys.jspproject.pojo.Employee"%>
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
		<thead>'list of all Employees'</thead>
		<%
		List<Employee> allEmployee = (ArrayList<Employee>)request.getAttribute("emplist");
		for (Employee emp: allEmployee) {%>
		<tr>
			<td><%=emp.getEmp_id()%></td>
			<td><%=emp.getFirst_name()%></td>
			<td><%=emp.getLast_name()%></td>
			<td><%=emp.getEmail()%></td>
			<td><%=emp.getHire_date()%></td>
			<td><%=emp.getJob_id()%></td>
			<td><%=emp.getSalary()%></td>
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>
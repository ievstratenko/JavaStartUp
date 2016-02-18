<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Person List</title>
</head>
<body>
	<table>
		<tr>
			<td>ID</td>
			<td>Name</td>
			<td>Age</td>
		</tr>
		<c:forEach items="${list}" var="person">
			<tr>
				<td>${person.id}</td>
				<td>${person.name}</td>
				<td>${person.age}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
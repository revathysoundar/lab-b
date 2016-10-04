<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%--
	On this page we list all the users.
	
	Model:
	- List<User> users
	
 --%>
<html>
<head>
	<%@ include file="parts/head.jsp" %>
</head>
<body>
<div class="nav">
	<a href="<c:url value="/"/>">Home</a>
</div>
<h1>
	All the Users
</h1>

<div>
 Sort by:
 <a href="<c:url value="/users?sort=firstName"/>">First name</a>
 <a href="<c:url value="/users?sort=lastName"/>">Last name</a>
 <a href="<c:url value="/users?sort=email"/>">Email</a>
 <a href="<c:url value="/users"/>">Unsorted</a>
</div>

<table>
	<tr>
		<th>Name</th><th>Email</th><th>Roles</th>
	</tr>
	<c:forEach var="user" items="${users}" >
		<tr>
			<td><a href="<c:url value="/users/${user.id}"/>">${ user.firstName } ${ user.lastName }</a></td>
			<td>${ user.email }</td>
		</tr>
	</c:forEach>
</table>
<div class="action-bar">
	<a class="add-btn" href="<c:url value="/users/create"/>">Add a user</a>
</div>

</body>
</html>

<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>  

<rapid:override name="content">
	<%
		if (session.getAttribute("username") == null) {
			String message = "Login before you create a post!";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/_view/login.jsp").forward(request, response);
		}
	%> 
	 
	<head>
	<title> Edit Profile</title>
	</head>
    <h1>Edit Profile</h1>
    <body>	
		<form action = "${pageContext.servletContext.contextPath}/editProfile" method="post" >
			
			<td class="label">Name: </td>
			<br>
			<td><input type="text" name="name" placeholder="Your name here..." size="24" value="${loginModel.name}" /></td>
			<br><br>
			
			<td class="label">Bio:</td>
			<br>
			<textarea name="bio" placeholder="Write your bio here..." rows="2" cols="64"></textarea>
			<br><br>
			
			<input type="Submit" name="submit" value="Save Changes" class="nav-item nav-link" href= http://localhost:8081/project_database/profile>
			<a class="nav-item nav-link" type=button href=http://localhost:8081/project_database/profile></i> Return to Profile</a>
			

</form>
	</body>


</rapid:override>  

<%@ include file="base.jsp" %> 
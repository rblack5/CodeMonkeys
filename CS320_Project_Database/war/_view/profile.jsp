<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>  

<rapid:override name="content"> 
	<%
		if (session.getAttribute("username") == null) {
			String message = "Login before you view your profile!";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/_view/login.jsp").forward(request, response);
		}
	%>
	<!-- Icon Library -->
	<script src="https://kit.fontawesome.com/15a68e8958.js" crossorigin="anonymous"></script>

	<h1>
	<tr>
	<td class="nav-item nav-link" href="/project_database/profile"><i class="fas fa-user-circle"></i></td>
	<td>${user.username}</td>
	</h1>
	
	<br>
	<p>Bio: ${user.bio}</p>

	<!-- Edit Profile -->
	<a class="nav-item nav-link" type=button href=http://localhost:8081/project_database/editProfile><i class="fas fa-plus-square"></i> Edit Profile</a>

	
</rapid:override>  

<%@ include file="base.jsp" %> 
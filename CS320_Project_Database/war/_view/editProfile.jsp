<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>  

<rapid:override name="content">
	<%
		if (session.getAttribute("username") == null) {
			String message = "Login before you edit your profile!";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/_view/login.jsp").forward(request, response);
		}
	%> 
	 
	<head>
	<title> Edit Profile</title>
	</head>

 	<div class="content-section">
	<form action = "${pageContext.servletContext.contextPath}/editProfile" method="post" >
		<fieldset class = "form-group">
                <legend class="border-bottom mb-4">Edit Profile</legend>
			
			<div class="label mb-2 mt-1">Username: </div>
			<table>
				<tr>
					<td><input type="text" name="username" placeholder="Enter New Username" size="50" value="${username}" maxlength="20" pattern=".{3,}" 
							title="Must be at least 3 characters"/></td>
				</tr>
			</table>
			
			<div class="label mb-2 mt-3">Bio: </div>
			<table>
				<tr>
					<td><textarea name="bio" placeholder="Write your bio here..." rows="4" cols="53" value="${UserModel.bio}"></textarea></td>
				</tr>
			</table>
			
			<div class="label mb-2 mt-3">Password: </div>
			<table>
				<tr>
					<td><input type="password" name="password" id="password" placeholder="Enter New Password" size="50" value="${UserModel.password}" maxlength="20" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{5,}" 
							title="Must contain at least 1 uppercase letter, 1 lowercase letter, 1 number, and be at least 5 characters long"/>
					<span id="eye" onClick="toggleEye()" >
						<i class="fas fa-eye"></i>
						</span>


</td>
				</tr>
			</table>

				<div class="form-group mt-4">
				<button class="btn btn-outline-info" type="Submit" value="Save Changes">Save Changes</button>
			</div>
				

<script>
function toggleEye() {
  	var pass = document.getElementById("password");
  	if (pass.type == "password") {
  	  pass.type = "text";
	document.getElementById("eye").style.color = "blue";
  	}else{
  	  pass.type = "password";
	document.getElementById("eye").style.color = "black";
  	}
}

</script>

		</fieldset>

		<a class="nav-item nav-link" type=button href=http://localhost:8081/project_database/profile></i> Return to Profile</a>

</form>
	</div>


</rapid:override>  

<%@ include file="base.jsp" %> 

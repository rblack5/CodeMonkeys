<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

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
				
			<c:if test="${! empty errorMessage}">
				<div class="error mb-3" style="color:Red;">${errorMessage}</div>
			</c:if>


				<div class="label mb-2 mt-1">Change Username: </div>
				<table>
					<tr>
						<td><input type="text" name="username" placeholder="Enter New Username" size="50" value="${editProfileName}" maxlength="20" pattern=".{3,}" 
								title="Must be at least 3 characters"/></td>
					</tr>
				</table>
				
				<div class="label mb-2 mt-3">Create Your Bio: </div>
				<table>
					<tr>
						<td><textarea name="bio" placeholder="Write your bio here..." rows="4" cols="53">${editProfileBio}</textarea></td>
					</tr>
				</table>
				
				<div class="label mb-2 mt-3">Enter a New Password: </div>
				
				<table>
					<tr>
						<td><input autocomplete="off" type="password" name="code1" id="code1" placeholder="Enter New Password" size="40" value="" maxlength="20" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{5,}" 
								title="Must contain at least 1 uppercase letter, 1 lowercase letter, 1 number, and be at least 5 characters long"/>
						<span id="eye" onClick="toggleEye()" >
							<i class="fas fa-eye"></i>
							</span>
						</td>
					</tr>
				</table>
	

				<div class="label mb-2 mt-3">Confirm New Password: </div>
				<table>
					<tr>
						<td><input autocomplete="off" type="password" name="password2" id="password2" size="40" value="${registerPassword2}" placeholder="Confirm Password" maxlength="20" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{5,}" 
						title="Must contain at least 1 uppercase letter, 1 lowercase letter, 1 number, and be at least 5 characters long"/>
						</td>
					</tr>
				</table>
				<div class="label mb-2 mt-3">Post Appearance: </div>
				
				<c:set var = "accountTheme" value = "${user.accountTheme}"/>
				<c:set var = "postTheme" value = "${user.postTheme}"/>
				<c:set var = "adminStatus" value = "${user.adminStatus}"/>
				
				<c:set var = "light" value = "light"/>
				<c:set var = "dark" value = "dark"/>
				<c:set var = "fire" value = "fire"/>
				<c:set var = "gold" value = "gold"/>
       
				<table>
						<tr>
							<td>
								<div>
									<div class = "row w-100 m-0 p-0">
										<c:if test="${postTheme == light}">
											<div class = "pr-3"> Light
												<input type="radio" id = "check1" name="postTheme" value = "light" checked/>
											</div>
											<div class = "pr-3"> Dark
												<input type="radio" id = "check2" name="postTheme" value = "dark"/>
											</div>
											<div class = "pr-3"> Fire
												<input type="radio" id = "check3" name="postTheme" value = "fire" />
											</div>
											<c:if test="${adminStatus == true}">
												<div class = "pr-3"> Gold
													<input type="radio" id = "check4" name="postTheme" value = "gold" />
												</div>
											</c:if>
										</c:if>
										
										<c:if test="${postTheme == dark}">
											<div class = "pr-3"> Light
												<input type="radio" id = "check1" name="postTheme" value = "light"/>
											</div>
											<div class = "pr-3"> Dark
												<input type="radio" id = "check2" name="postTheme" value = "dark" checked/>
											</div>
											<div class = "pr-3"> Fire
												<input type="radio" id = "check3" name="postTheme" value = "fire" />
											</div>
											<c:if test="${adminStatus == true}">
												<div class = "pr-3"> Gold
													<input type="radio" id = "check4" name="postTheme" value = "gold" />
												</div>
											</c:if>
											
										</c:if>
										
										<c:if test="${postTheme == fire}">
											<div class = "pr-3"> Light
												<input type="radio" id = "check1" name="postTheme" value = "light"/>
											</div>
											<div class = "pr-3"> Dark
												<input type="radio" id = "check2" name="postTheme" value = "dark"/>
											</div>
											<div class = "pr-3"> Fire
												<input type="radio" id = "check3" name="postTheme" value = "fire" checked/>
											</div>
											<c:if test="${adminStatus == true}">
												<div class = "pr-3"> Gold
													<input type="radio" id = "check4" name="postTheme" value = "gold" />
												</div>
											</c:if>
										</c:if>
										
										<c:if test="${postTheme == gold}">
											<div class = "pr-3"> Light
												<input type="radio" id = "check1" name="postTheme" value = "light"/>
											</div>
											<div class = "pr-3"> Dark
												<input type="radio" id = "check2" name="postTheme" value = "dark"/>
											</div>
											<div class = "pr-3"> Fire
												<input type="radio" id = "check3" name="postTheme" value = "fire"/>
											</div>
											<c:if test="${adminStatus == true}">
												<div class = "pr-3"> Gold
													<input type="radio" id = "check4" name="postTheme" value = "gold" checked/>
												</div>
											</c:if>
										</c:if>
										
										
									</div>
								</div>
							</td>
						</tr>

				</table>
		

				
				<div class="label mb-2 mt-3">Personal Theme: </div>

				<table>
					<tr>
						<td>
							<div>
								<div class = "row w-100 m-0 p-0">
									<c:if test="${accountTheme == light}">
										<div class = "pr-3"> Light
											<input type="radio" id = "check1" name="accountTheme" value = "light" checked/>
										</div>
										
										<div class = "pr-3"> Dark
											<input type="radio" id = "check2" name="accountTheme" value = "dark"/>
										</div>
									</c:if>
									
									<c:if test="${accountTheme == dark}">
										<div class = "pr-3"> Light
											<input type="radio" id = "check1" name="accountTheme" value = "light"/>
										</div>
										
										<div class = "pr-3"> Dark
											<input type="radio" id = "check2" name="accountTheme" value = "dark" checked/>
										</div>
									</c:if>
								</div>							
							</div>
						</td>
					</tr>

				</table>


				<br><br>
				<span class="form-group mt-4">
					<button class="btn btn-outline-info" type="Submit" value="Save Changes">Save Changes</button>
				</span>
					
				<span class="form-group mt-4">
					<button class="btn btn-outline-info" type="button" value="Discard Changes" onclick="discardConfirmation()" >Discard Changes
					</button>
				</span>

			
	
	<script>

	function discardConfirmation() {
		if(confirm("Changes are not saved.\nAre you sure want to continue?") == true){
		window.location.href="http://localhost:8081/project_database/profile";
		}
	}


	function toggleEye() {
	  	var pass = document.getElementById("password1");
		var pass2 = document.getElementById("password2");
	  	if (pass.type == "password") {
	  	  pass.type = "text";
		  pass2.type = "text";
		document.getElementById("eye").style.color = "blue";
	  	}else{
	  	  pass.type = "password";
		  pass2.type = "password";
		document.getElementById("eye").style.color = "black";
	  	}
	}
	
	</script>
	
			</fieldset>
	
		<!-- 	<a class="nav-item nav-link" type=button href=http://localhost:8081/project_database/profile></i> Return to Profile</a>  -->
	
		</form>

		<form action = "${pageContext.servletContext.contextPath}/uploadImage" method="post" enctype="multipart/form-data">
			<div class="label mb-2 mt-3"> Profile Image: </div>
				<tr>
					<td><input type="file" name="userImage" size="50"/></td>
				</tr>
				<button class="btn btn-outline-info" type="Submit" value="Save Image">Save Image</button>
		</form>
	</div>


</rapid:override>  

<%@ include file="base.jsp" %> 

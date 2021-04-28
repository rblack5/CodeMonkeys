<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<rapid:override name="content">

    <head>
	<title> Account Registration </title>
	</head>
    
    <div class="content-section">
		<form action = "${pageContext.servletContext.contextPath}/register" method="post">
			<fieldset class = "form-group">
				<legend class="border-bottom mb-4">Create Account</legend>
				
				<c:if test="${! empty errorMessage}">
					<div class="error mb-3" style="color:Red;">${errorMessage}</div>
				</c:if>
				
				<div class="label mb-2 mt-1">Username: </div>
				 <table>
					<tr>
						<td><input type="text" name="username" size="50" value="${registerUsername}" placeholder="Username" maxlength="20"/></td>
					</tr>
				</table>
				
				<div class="label mb-2 mt-3">Password: </div>
				 <table>
					<tr>
						<td><input type="password" name="password" size="50" value="${registerPassword}" placeholder="Password" maxlength="20"/></td>
					</tr>
				</table>
				
				<div class="label mb-2 mt-3">Confirm Password: </div>
				 <table>
					<tr>
						<td><input type="password" name="password2" size="50" value="${registerPassword2}" placeholder="Confirm Password" maxlength="20"/></td>
					</tr>
				</table>
				
			</fieldset>
			
			<div class="form-group mt-4">
				<button class="btn btn-outline-info" type="Submit" name="submit" value="Create your Account">Create your Account</button>
			</div>

			<div class="border-top pt-3">
	            <small class="text-muted">
	                Already have an account?<a class = "ml-2" href=http://localhost:8081/project_database/login><span class="link">Click here</span></a> to login. 
	            </small>
        	</div>
			
		</form>
	</div>

       
   



</rapid:override>  

<%@ include file="base.jsp" %> 
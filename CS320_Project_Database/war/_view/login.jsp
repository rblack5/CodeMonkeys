<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<rapid:override name="content">

	<head>
	<title> Login </title>
	</head>
	<!--  
 <h1>Login Page</h1>
     <body>	

		<c:if test="${! empty errorMessage}">
			<div class="error" style="color:Red;">${errorMessage}</div>
		</c:if>
		
		<c:if test="${! empty message}">
			<div class="error">${message}</div>
		</c:if>

		<form action = "${pageContext.servletContext.contextPath}/login" method="post">
			<br>
			<div class="label">Username: </div>
			<table>
				<tr>
					<td><input type="text" name="email" size="12" value="${loginModel.username}" placeholder="Email" /></td>
				</tr>
			</table>
			<div class="label">Password: </div>
			<table>
				<tr>
					
					<td><input type="password" name="password" size="12" value="${loginModel.password}" placeholder="Password" /></td>
				</tr>
			</table>
			
			<br>
			<input type="Submit" name="submit" value="Login">
			
			<br><br>
			
			
			Don't have an account? <a href=http://localhost:8081/project_database/register><span class="link">Click here</span></a> to register. 			
			
		</form>
	</body>
	-->
	
	<div class="content-section">
        <form action = "${pageContext.servletContext.contextPath}/login" method="post">
            <fieldset class = "form-group">
                <legend class="border-bottom mb-4">Login</legend>
                <div class="label mb-2 mt-1">Username: </div>
                <table>
					<tr>
						<td><input type="text" name="username" size="50" value="${loginModel.username}" placeholder="Username" /></td>
					</tr>
				</table>
				<div class="label mb-2 mt-3">Password:</div>
				 <table>
					<tr>
						<td><input type="password" name="password" size="50" value="${loginModel.password}" placeholder="Password" /></td>
					</tr>
				</table>
            </fieldset>
            <div class="form-group mt-4">
                <button class="btn btn-outline-info" type="submit" value="Login">Login</button>
                <small class="text-muted ml-2">
                    <a href="{% url 'password_reset' %}">Forgot Password?</a>
                </small>
            </div>
        </form>
        <div class="border-top pt-3">
            <small class="text-muted">
                Need An Account?<a class = "ml-2" href=http://localhost:8081/project_database/register><span class="link">Click here</span></a> to register. 
            </small>
        </div>
    </div>

    
</rapid:override>  

<%@ include file="base.jsp" %> 
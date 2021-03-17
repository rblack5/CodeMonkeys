<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>  

<rapid:override name="content">
	<head>
	<title> Login </title>
	</head>
    <h1>Login Page</h1>
    <body>	
		<f:if test="${! empty errorMessage}">
			<div class="error" style="color:red">${errorMessage}</div>
		</f:if>
		
		<form action = "${pageContext.servletContext.contextPath}/login" method="post">
			<br>
			<table>
				<tr>
					<td class="label">Email: </td>
					<td><input type="text" name="email" size="12" value="${loginModel.email}" placeholder="Email" /></td>
				</tr>
				<tr>
					<td class="label">Password: </td>
					<td><input type="password" name="password" size="12" value="${loginModel.password}" placeholder="Password" /></td>
				</tr>
			</table>
			
			<br>
			<input type="Submit" name="submit" value="Login">
			
			<br><br>
			
			
			Don't have an account? <a href=http://localhost:8081/project_database/register><span class="link">Click here</span></a> to register. 			
			
    				


		</form>
	</body>
</rapid:override>  

<%@ include file="base.jsp" %> 
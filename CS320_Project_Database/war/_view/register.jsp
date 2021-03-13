<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>  

<rapid:override name="content">  
    <head>
	<title> Account Registration </title>
	</head>
    <h1>Create a New Account</h1>
    <body>	
		<form action = "${pageContext.servletContext.contextPath}/register" method="post">
			
			<td class="label">Enter you Email address: </td>
			<br>
			<td><input type="email" name="email" placeholder="Email" size="24" value="${loginModel.email}" /></td>
			<br><br>
			
			<td class="label">Create your password:</td>
			<br>
			<td><input type="password" name="password" placeholder="Password" size="24" value="${loginModel.password}" /></td>
			
			<br>
			<td class="label">Confirm your password:</td>
			<br>
			<td><input type="password" name="password2" placeholder="Confirm Password" size="24" value="${loginModel.password2}" /></td>
						


			<br><br>
				
			<input type="Submit" name="submit" value="Create your Account">


			<br><br>
			<p>Already have an account? <a href=http://localhost:8081/project_database/login><span class="link">Click here</span></a> to sign in.</p>
			
		</form>
	</body>


</rapid:override>  

<%@ include file="base.jsp" %> 
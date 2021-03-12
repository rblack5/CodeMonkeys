<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>  

<rapid:override name="content">
	<head>
	<title> Login </title>
	</head>
    <h1>Login Page</h1>
    <body>	
		<form action = "${pageContext.servletContext.contextPath}/login" method="post">
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
			<input type="Submit" name="submit" value="Login">
		</form>
	</body>
</rapid:override>  

<%@ include file="base.jsp" %> 
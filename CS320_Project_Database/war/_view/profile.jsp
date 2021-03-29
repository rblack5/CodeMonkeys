<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>  

<rapid:override name="content"> 
	<%
		if (session.getAttribute("username") == null) {
			String message = "Login before you view your profile!";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/_view/login.jsp").forward(request, response);
		}
	%>
	 
    <h1>Profile Page</h1>
    <body>This is the profile page</body>
</rapid:override>  

<%@ include file="base.jsp" %> 
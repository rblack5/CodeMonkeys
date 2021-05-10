<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>  

<rapid:override name="content">
	<%
  		// we want to set the attributes to false, and clear the username
		
		if (session.getAttribute("username") != null) {
			String message = "Logout Successful!";
			request.setAttribute("message", message);
		}
		else {
			String message = "You cannot logout if you are not logged in!";
			request.setAttribute("message", message);
		}
	
		Boolean loggedIn = false;
		session.setAttribute("username", null);
		session.setAttribute("user", null);
		session.removeAttribute("intUserID");
		session.removeAttribute("userID");
		session.setAttribute("adminStatus", "false");
		request.setAttribute("loggedIn", loggedIn);
		session.setAttribute("userID", 0);
		
		request.getRequestDispatcher("/_view/login.jsp").forward(request, response);
		
	%>   
    <h1>Logout Page</h1>
    <body>This is the logout page</body>
</rapid:override>  

<%@ include file="base.jsp" %> 
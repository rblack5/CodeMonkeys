<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<rapid:override name="content">
	<%
		if (session.getAttribute("username") == null) {
			String message = "Login before you create a post!";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/_view/login.jsp").forward(request, response);
		}
	%> 
	 
	<head>
		<title> Edit Post </title>
	</head>
	
    <div class="content-section">
		<form action = "${pageContext.servletContext.contextPath}/editPost" method="post">
			<fieldset class = "form-group">
                <legend class="border-bottom mb-4">Edit Post</legend>
				
				<c:if test="${! empty errorMessage}">
					<div class="error mb-2" style="color:Red;">${errorMessage}</div>
				</c:if>
				
				<div class="label mb-2 mt-1">Title: </div>
				<table>
					<tr>
						<td><input type="text" name="title" placeholder="Title your Post" size="50" value="${title}" /></td>
					</tr>
				</table>
				
				<div class="label mb-2 mt-3">Body: </div>
				<table>
					<tr>
						<td><textarea name="body" placeholder="Write your post here..." rows="4" cols="53" value="${body}"></textarea></td>
					</tr>
				</table>
				
				<div class="form-group mt-4">
					<button class="btn btn-outline-info" type="Submit" value="Create Post!">Update Post!</button>
				</div>
				
			</fieldset>
		</form>
	</div>

</rapid:override>  

<%@ include file="base.jsp" %> 
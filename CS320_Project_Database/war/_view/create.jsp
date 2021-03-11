<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>  

<rapid:override name="content">  
	<head>
	<title> Create </title>
	</head>
    <h1>Create a Post</h1>
    <body>	
		<form action = "${pageContext.servletContext.contextPath}/create" method="post">
			
			<td class="label">Title: </td>
			<br>
			<td><input type="text" name="title" placeholder="Title your Post" size="24" value="${loginModel.title}" /></td>
			<br><br>
			
			<td class="label">Body:</td>
			<br>
			<textarea name="body" placeholder="Write your post here..." rows="4" cols="50"></textarea>
			<br><br>
				
			<input type="Submit" name="submit" value="Create Post!">
		</form>
	</body>


</rapid:override>  

<%@ include file="base.jsp" %> 
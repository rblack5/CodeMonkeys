<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<rapid:override name="content"> 

	<!-- Icon Library -->
	<script src="https://kit.fontawesome.com/15a68e8958.js" crossorigin="anonymous"></script>

	<h1>
		<img class ="rounded-circle article-img" src= "${pageContext.request.contextPath}/_view/images/default.jpg">
		${user.username}
	</h1>
	
	<c:set var = "userID" value = "${user.userID}"/>
	<c:set var = "adminStatus" value = "${adminStatus}"/>
	
	<br>
	<div class ="media content-section" style= "height: 250px"> Bio: ${user.bio}</div>

	<!-- Edit Profile -->
	<c:if test="${userMatch == true}">
		<a class = "ml-2 btn btn-warning btn-sm mt-1 mb-1" href=http://localhost:8081/project_database/editProfile>
			<input class="" type = "submit" value = "Edit Profile" style = "all: unset; color: white !important;">
		</a>
	</c:if>
	
	<c:if test="${(sessionScope.userID == userID) || (sessionScope.adminStatus == true)}">
		<div class = "row w-100 m-0">
			<form action = "/project_database/deleteUser">
				<a class = "ml-2 btn btn-danger btn-sm mt-1 mb-1">
					<input type = "hidden" id = "userID" name = "userID" value = "${user.userID}">
					<input class="" type = "submit" value = "Delete User" style = "all: unset; color: white !important;">
				</a>
			</form>
		</div>
	</c:if>
	
</rapid:override>  

<%@ include file="base.jsp" %> 
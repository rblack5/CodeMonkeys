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
	<h4>Date Joined: ${user.dateJoined}</h4>
	<br>
	<h4>Bio:</h4>
	<div class ="media content-section" style= "word-break: break-word; white-space: pre-wrap;">${user.bio}</div>

	<!-- Edit Profile -->
	<div class = "row w-100 m-0 pb-3">
		<c:if test="${userMatch == true}">
			<div class = "pr-3">
				<a class = "btn btn-warning btn-md mt-1 mb-1" href=http://localhost:8081/project_database/editProfile>
					<input class="" type = "submit" value = "Edit Profile" style = "all: unset; color: white !important;">
				</a>
			</div>
		</c:if>
		
		<c:if test="${(sessionScope.userID == userID) || (sessionScope.adminStatus == true)}">
				<form action = "/project_database/deleteUser">
					<div class = "pr-3">
						<a class = "btn btn-danger btn-md mt-1 mb-1">
							<input type = "hidden" id = "userID" name = "userID" value = "${user.userID}">
							<input class="" type = "submit" value = "Delete Account" style = "all: unset; color: white !important;">
						</a>
					</div>
				</form>
		</c:if>
		
		<c:if test="${! empty pageNumberMinus}">
			<form action = "/project_database/profile">
				<div class = "pr-3">
					<a class = "btn btn-danger btn-md mt-1 mb-1">
						<input type = "hidden" id = "userID" name = "userID" value = "${userID}">
						<input type = "hidden" id = "page" name = "page" value = "${pageNumberMinus}">
					    <input type = "submit" value = "Prev Page" style = "all: unset; cursor:pointer; color:white;">
					</a>
				</div>
			</form>
		</c:if>
		
		<c:if test="${! empty pageNumberPlus}">
			<form action = "/project_database/profile">
				<div class = "">
					<a class = "btn btn-success btn-md mt-1 mb-1">
						<input type = "hidden" id = "userID" name = "userID" value = "${userID}">
						<input type = "hidden" id = "page" name = "page" value = "${pageNumberPlus}">
					    <input type = "submit" value = "Next Page" style = "all: unset; cursor:pointer; color:white;">
					</a>
				</div>
			</form>
		</c:if>
	</div>
	
		<c:forEach items="${posts}" var="post">
		<article class="media content-section" style = "${post.backgroundStyle}">
	    	<img class ="rounded-circle article-img" src= "${pageContext.request.contextPath}/_view/images/default.jpg">
	    	<div class="media-body" style = "word-break: break-word;">
				<form action = "/project_database/profile">
				    	<div class="article-metadata">
				        	<a class="mr-2" href="/project_database/profile">

								<input type = "hidden" id = "userID" name = "userID" value = "${post.userID}">
					    		<input type = "submit" value = "${post.username}" style = "${post.linkStyle}">
							</a>
				        	<small class="" style = "${post.textStyle}">${post.dateCreated}</small>
				    	</div>
				</form>
				
				<!--  User ID is => ${post.userID} -->
			    <form action = "/project_database/post">
				    <div>
				    	<h2>
					    	<a class="article-title" href="/project_database/post">
					    		<input type = "hidden" id = "postID" name = "postID" value = "${post.postID}">
				    			<input class="article-title" type = "submit" value = "${post.title}" style = "${post.textStyle}"> <!--  Variable can go here -->
						    </a>
						</h2>
					</div>
			    </form>

			</div>
		</article>	
	</c:forEach>
	
	<div class = "row w-100 m-0 pb-5">

		
	</div>
	
</rapid:override>  

<%@ include file="base.jsp" %> 
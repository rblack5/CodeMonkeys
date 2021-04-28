<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<rapid:override name="content"> 

	<c:if test="${! empty loggedInMessage}">
		<div class="" style = "font-size: 1.7rem; color: black; margin-bottom: 16px;"> ${loggedInMessage}</div>
	</c:if>
	
	<c:if test="${! empty message}">
		<div class="" style = "font-size: 1.7rem; color: black; margin-bottom: 16px;"> ${message}</div>
	</c:if>
	<form action = "/project_database/profile">
	<article class="media content-section">
	    <img class ="rounded-circle article-img" src= "${pageContext.request.contextPath}/_view/images/default.jpg">
	    
	    <div class="media-body">
		    <div class="article-metadata">
		        <a class="mr-2" href="/project_database/profile">
					<input type = "hidden" id = "userID" name = "userID" value = "${post.userID}">
		    		<input type = "submit" value = "${post.username}" style = "all: unset">
				</a>
				
		        <small class="text-muted">${post.dateCreated} <b>Post ID: ${post.postID} User ID: ${post.userID}</b></small>
		        
		     	<c:set var = "authorID" value = "${post.userID}"/>
		        <c:set var = "userID" value = "${userID}"/>
		        <c:set var = "adminStatus" value = "${adminStatus}"/>
				
				<form action = "/project_database/editPost">
				
			        <c:if test="${(authorID == userID) || (adminStatus == true)}">
						<div>
							<a class = "btn btn-secondary btn-sm mt-1 mb-1">
								<input type = "hidden" id = "postID" name = "postID" value = "${post}">
								<input class="" type = "submit" value = "Update" style = "all: unset">
							</a>
							<a class = "btn btn-danger btn-sm mt-1 mb-1" href="{% url 'post-delete' object.id %}">Delete</a>
						</div>
			        </c:if>
			        
		        </form>	
		        
		    </div>
	    
		    <h2 class="article-title">${post.title}</h2>
		    <p class="article-content">${post.body}</p>
	    </div>
	</article>
	</form>
</rapid:override>  

<%@ include file="base.jsp" %> 
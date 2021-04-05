<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<rapid:override name="content"> 

	<c:if test="${! empty loggedInMessage}">
		<div class="" style = "font-size: 1.7rem; color: black; margin-bottom: 16px;"> ${loggedInMessage}</div>
	</c:if>
	
	<c:if test="${! empty message}">
		<div class="" style = "font-size: 1.7rem; color: black; margin-bottom: 16px;"> ${message}</div>
	</c:if>
	
	<article class="media content-section">
	    <img class ="rounded-circle article-img" src= "${pageContext.request.contextPath}/_view/images/default.jpg">
	    
	    <div class="media-body">
		    <div class="article-metadata">
		        <a class="mr-2" href="{% url 'user-posts' object.author.username %}">The User ID of this post is => ${post.userID}</a>

		        <small class="text-muted">${post.postID}</small>
		        
		        <!--  
		            <div>
		                <a class = "btn btn-secondary btn-sm mt-1 mb-1" href="{% url 'post-update' object.id %}">Update</a>
		                <a class = "btn btn-danger btn-sm mt-1 mb-1" href="{% url 'post-delete' object.id %}">Delete</a>
		            </div>
		        -->
		        
		    </div>
	    
		    <h2 class="article-title">${post.title}</h2>
		    <p class="article-content">${post.body}</p>
	    </div>
	</article>
	
</rapid:override>  

<%@ include file="base.jsp" %> 
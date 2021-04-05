<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<rapid:override name="content"> 

	<c:if test="${! empty loggedInMessage}">
		<div class="" style = "font-size: 1.7rem; color: black; margin-bottom: 16px;"> ${loggedInMessage}</div>
	</c:if>
	
	<c:if test="${! empty message}">
		<div class="" style = "font-size: 1.7rem; color: black; margin-bottom: 16px;"> ${message}</div>
	</c:if>

	
	
	<c:forEach items="${posts}" var="post">
		<article class="media content-section">
	    <img class ="rounded-circle article-img" src= "${pageContext.request.contextPath}/_view/images/default.jpg">
	    <div class="media-body">
	    <div class="article-metadata">
	        <a class="mr-2" href="{% url 'user-posts' post.author.username %}">Default User</a>
	        <small class="text-muted">3/21/2021</small>
	    </div>
	    
	    <h2><a class="article-title" href="{% url 'post-detail' post.id %}">${post.title}</a></h2>
	    </div>
		</article >
	</c:forEach>
 
    
</rapid:override>  

<%@ include file="base.jsp" %> 
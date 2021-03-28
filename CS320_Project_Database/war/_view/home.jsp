<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<rapid:override name="content"> 

	<c:if test="${! empty loggedInMessage}">
		<div class="" style = "font-size: 1.7rem; color: black; margin-bottom: 16px;"> ${loggedInMessage}</div>
	</c:if>

	<article class="media content-section">
	    <img class ="rounded-circle article-img" src= "${pageContext.request.contextPath}/_view/images/gand.jpg">
	    <div class="media-body">
	    <div class="article-metadata">
	        <a class="mr-2" href="{% url 'user-posts' post.author.username %}">darkloor</a>
	        <!--  to get rid of the exact time using '|date:FdY' which the FdY is from tags from the docs django project for the date time call-->
	        <small class="text-muted">3/21/2021</small>
	    </div>
	    
	    <h2><a class="article-title" href="{% url 'post-detail' post.id %}">Dogs Are Better</a></h2>
	    </div>
	</article>
	
	<article class="media content-section">
	    <img class ="rounded-circle article-img" src= "${pageContext.request.contextPath}/_view/images/cat.jpg">
	    <div class="media-body">
	    <div class="article-metadata">
	        <a class="mr-2" href="{% url 'user-posts' post.author.username %}">snyder</a>
	        <!--  to get rid of the exact time using '|date:FdY' which the FdY is from tags from the docs django project for the date time call-->
	        <small class="text-muted">3/20/2021</small>
	    </div>
	    
	    <h2><a class="article-title" href="{% url 'post-detail' post.id %}">Cats Are Great</a></h2>
	    </div>
	</article>
	
	<article class="media content-section">
	    <img class ="rounded-circle article-img" src= "${pageContext.request.contextPath}/_view/images/spinach.jpg">
	    <div class="media-body">
	    <div class="article-metadata">
	        <a class="mr-2" href="{% url 'user-posts' post.author.username %}">spinach</a>
	        <!--  to get rid of the exact time using '|date:FdY' which the FdY is from tags from the docs django project for the date time call-->
	        <small class="text-muted">3/19/2021</small>
	    </div>
	    
	    <h2><a class="article-title" href="{% url 'post-detail' post.id %}">Eat Your Greens</a></h2>
	    </div>
	</article>
	
	<article class="media content-section">
	    <img class ="rounded-circle article-img" src= "${pageContext.request.contextPath}/_view/images/rollercoaster.jpg">
	    <div class="media-body">
	    <div class="article-metadata">
	        <a class="mr-2" href="{% url 'user-posts' post.author.username %}">Spunky</a>
	        <!--  to get rid of the exact time using '|date:FdY' which the FdY is from tags from the docs django project for the date time call-->
	        <small class="text-muted">3/19/2021</small>
	    </div>
	    
	    <h2><a class="article-title" href="{% url 'post-detail' post.id %}">Cool Roller Coaster</a></h2>
	    </div>
	</article>
 
    
</rapid:override>  

<%@ include file="base.jsp" %> 
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<rapid:override name="content"> 

	<c:if test="${! empty loggedInMessage}">
		<div class="media message-content-section bg-steel p-3" style = "font-size: 1.6rem; background-color: #5f788a; color: #fbfcfd; font-weight: bold; margin-bottom: 20px;"> ${loggedInMessage}</div>
	</c:if>
	
	<c:if test="${! empty message}">
			<div class="media message-content-section bg-steel p-3" style = "font-size: 1.6rem; background-color: #5f788a; color: #fbfcfd; font-weight: bold; margin-bottom: 20px;"> ${message}</div>
	</c:if>
	
	<div class = "row w-100 m-0 p-0">
		<form action = "/project_database/search">
		    	<div class = "row w-100 m-1 pb-0">
					<input autocomplete="off" id="search" type = "text" name = "search" value = "" placeholder="Search..." size="50" maxlength="100" class = "media content-section">
					<div class = "pl-3">
						<a class = "btn btn-success btn-md mt-1 mb-0">
			    			<input type = "submit" value = "Search" style = "all: unset; cursor:pointer; color:white;">
			    		</a>
			    	</div>
		    	</div>
		</form>
		
		<form action = "/project_database/sort">
			<div class = "row w-100 m-1 pb-0">
				<div class = "pl-2">
					<a class = "btn btn-info btn-md mt-1 mb-0">
						<input type = "hidden" name = "sort" value = "newest">
		    			<input type = "submit" value = "Newest" style = "all: unset; cursor:pointer; color:white;">
		    		</a>
		    	</div>
		    </div>
		</form>
		
		<form action = "/project_database/sort">
			<div class = "row w-100 m-1 pb-0">
				<div class = "pl-2">
					<a class = "btn btn-secondary btn-md mt-1 mb-0">
						<input type = "hidden" name = "sort" value = "oldest">
		    			<input type = "submit" value = "Oldest" style = "all: unset; cursor:pointer; color:white;">
		    		</a>
		    	</div>
		    </div>
		</form>
		
		
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
		<c:if test="${! empty pageNumberMinus}">
			<form action = "/project_database/home">
				<div class = "pr-3">
					<a class = "btn btn-danger btn-md mt-1 mb-0">
						<input type = "hidden" id = "page" name = "page" value = "${pageNumberMinus}">
					    <input type = "submit" value = "Prev Page" style = "all: unset; cursor:pointer; color:white;">
					</a>
				</div>
			</form>
		</c:if>
		
		<c:if test="${! empty pageNumberPlus}">
			<form action = "/project_database/home">
				<div class = "">
					<a class = "btn btn-success btn-md mt-1 mb-0">
						<input type = "hidden" id = "page" name = "page" value = "${pageNumberPlus}">
					    <input type = "submit" value = "Next Page" style = "all: unset; cursor:pointer; color:white;">
					</a>
				</div>
			</form>
		</c:if>
		
	</div>
 
    
</rapid:override>  

<%@ include file="base.jsp" %> 
<!-- {% load static %} -->

<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid"  %>

<html>
  	<%
  		// if the user is logged in, we want to set the the attribute loggedIn to true
		if (session.getAttribute("username") == null) {
			Boolean loggedIn = true;
			request.setAttribute("loggedIn", loggedIn);
		}
	%> 




    <head>
        <!-- Bootstrap Stuff -->
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <!-- Styles for Page Link -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/_view/styles/main.css" type="text/css">
		<!-- Icon Library -->
	<script src="https://kit.fontawesome.com/15a68e8958.js" crossorigin="anonymous"></script>
	<script src="jquery-3.5.1.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    </head>

    <body>
    	<header class="site-header">
        	<nav class="navbar navbar-expand-md navbar-dark bg-steel fixed-top">
            	<div class="container">
	                <a class="navbar-brand mr-4" href="/project_database/home">Student Database</a>
	                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggle" aria-controls="navbarToggle" aria-expanded="false" aria-label="Toggle navigation">
	                <span class="navbar-toggler-icon"></span>
	                </button>
	                <div class="collapse navbar-collapse" id="navbarToggle">
	                	<div class="navbar-nav mr-auto">
		                    <a class="nav-item nav-link" href="/project_database/home"><i class="fas fa-home"></i> Home</a>
		                    <a class="nav-item nav-link" href="/project_database/about"><i class="fas fa-info"></i> About</a>
	                    </div>
	                  <!-- navbar Right Side -->
	                    <div class="navbar-nav">
	                        <!-- {% if user.is_authenticated %} -->
							<!-- create a post -->
							<c:if test="${empty loggedIn }">
	                        	<a class="nav-item nav-link" href="/project_database/create"><i class="fas fa-plus-square"></i> Create a Post</a>
	                        </c:if>
							<!-- profile -->
							<c:if test="${empty loggedIn }">
	                        	<a class="nav-item nav-link" href="/project_database/profile"><i class="fas fa-user-circle"></i> ${username}</a>
	                        </c:if>
							<!-- logout-->
							<c:if test="${empty loggedIn}">
								<a class="nav-item nav-link" href="/project_database/logout"><i class="fas fa-door-open"></i> Logout</a>
							</c:if>
	                    	<!-- {% else %} -->
							<!-- login-->
							<c:if test="${!empty loggedIn}">
	                        	<a class="nav-item nav-link" href="/project_database/login"><i class="fas fa-sign-in-alt"></i> Login</a>
	                        </c:if>
							<!-- register -->
							<c:if test="${!empty loggedIn}">
	                        	<a class="nav-item nav-link" href="/project_database/register"><i class="fas fa-clipboard-list"></i> Register</a>
							</c:if>
	                   	
	               		</div>
	            	</div>
	        	</div>
	     	</nav>
         </header>
        
        <main role="main" class="container">
            <div class="row">
	            <div class="col-md-8">
	              <!--***********************************  Other non-base HTML ******************************************* -->
	               
	                <rapid:block name="content">
		            	base_body_content
		        	</rapid:block>

	              <!--***********************************  End of non-base HTML ******************************************* -->
	              
	            </div>

	            <div class="col-md-4">
	                <div class="content-section">
		                <h3>Student Database</h3>
		                <p class='text-muted'>Filler</p>
	                    <ul class="list-group">
		                    <li class="list-group-item list-group-item-light">Student Database</li>
		                    <li class="list-group-item list-group-item-light">etc...</li>
		                    <li class="list-group-item list-group-item-light">etc...</li>
		                    <li class="list-group-item list-group-item-light">etc...</li>
	                    </ul>
	                </div>
	            </div>
            </div>
        </main>
        <!-- Bootstrap Stuff -->
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </body>
</html>



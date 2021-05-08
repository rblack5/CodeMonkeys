<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<rapid:override name="content">

	<head>
	<title> Login </title>
	</head>
	
	<div class="content-section" >
        <form action = "${pageContext.servletContext.contextPath}/login" method="post">
            	<fieldset class = "form-group">
                <legend class="border-bottom mb-4">Login</legend>
                <c:if test="${! empty message}">
					<div class="error mb-2" style="font-weight: bold;">${message}</div>
				</c:if>
				
				<c:if test="${! empty errorMessage}">
					<div class="error mb-2" style="color:Red;">${errorMessage}</div>
				</c:if>
				
                <div class="label mb-2 mt-1">Username: </div>
                <table>
					<tr>
						<td><input type="text" name="username" size="50" value="${loginUsername}" placeholder="Username" maxlength="20"/></td>
					</tr>
				</table>
				<div class="label mb-2 mt-3">Password:</div>
				 <table>
					<tr>
						<td><input type="password" name="password"  id="password"  size="50" value="${loginPassword}" placeholder="Password" maxlength="20"/>
						<span id="eye" onClick="toggleEye()" >
						<i class="fas fa-eye"></i>
						</span>
</td>
</tr>
				</table>

<script>
function toggleEye() {
  	var pass = document.getElementById("password");
  	if (pass.type == "password") {
  	  pass.type = "text";
	document.getElementById("eye").style.color = "blue";
  	}else{
  	  pass.type = "password";
	document.getElementById("eye").style.color = "black";
  	}
}

</script>
					
            </fieldset>
            <div class="form-group mt-4">
                <button class="btn btn-outline-info" type="submit" value="Login">Login</button>
                <small class="text-muted ml-2">
                    <a href=http://localhost:8081/project_database/register onclick="myFunction()">Forgot Password?</a>
                </small>
            </div>
        </form>


	<script>
	function myFunction() {
		alert("That sucks, too bad :( \n\n\nContact rgartrell@ycp.edu for help");
	}
	</script>


        <div class="border-top pt-3">
            <small class="text-muted">
                Need An Account?<a class = "ml-2" href=http://localhost:8081/project_database/register><span class="link">Click here</span></a> to register. 
            </small>
        </div>
    </div>
    
</rapid:override>  

<%@ include file="base.jsp" %> 
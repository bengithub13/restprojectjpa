<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%-- i had to enable EL manually for jsp to display model data else it only disaplys ${}.  it is caused by old jsp 1.2 descriptor in web.xml --%>
<html>
<%@ page isELIgnored="false" %>
<body>
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>
 <!--  spring default logout url -->
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
<!-- always past the csrf token in post, put -->
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
 
 <!-- note i had to go to project properties and target runtimes and select checkbox for Apache tomcat 7 else i get  error PageContect can not be resolved -->
	<!-- check username -->
	<c:if test="${pageContext.request.userPrincipal.name != null}">
	  <h2>
		Welcome : ${pageContext.request.userPrincipal.name} | <a href="javascript:formSubmit()"> Logout</a>
	  </h2>
	</c:if>

<!--  spring security custom taglib which will conditionally display message inside the sec:authorize tags passing access atttribute -->
<!--  sec:authorize is used to determine whether its content should be evaluated or not. -->
	<%--sec:authorize access="isFullyAuthenticated()"--%>
	<%--	<h2># This user is login by username / password.</h2>--%>
	<%-- sec:authoriz--%>
 	<sec:authorize access="isRememberMe()">
	<%--sec:authorize access="isAuthenticated()"--%>
		<h2># This user is login by "Remember Me Cookies".</h2>
	</sec:authorize>
</body>
</html>
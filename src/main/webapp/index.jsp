<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<!DOCTYPE html>
<html lang="${language}">
<head>
	<meta charset="utf-8">
	<title>index</title>

	<link rel="shortcut icon" href="assets/images/gt_favicon.png">
	
	<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/font-awesome.min.css">

	<!-- Custom styles for our template -->
	<link rel="stylesheet" href="assets/css/bootstrap-theme.css" media="screen" >
	<link rel="stylesheet" href="assets/css/main.css">

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="assets/js/html5shiv.js"></script>
	<script src="assets/js/respond.min.js"></script>
	<![endif]-->
</head>

<body class="home">
	<!-- Fixed navbar -->
	<div class="navbar navbar-inverse navbar-fixed-top headroom" >
		<div class="container">
			<div class="navbar-header">
				<!-- Button for smallest screens -->
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"><span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
				<a class="navbar-brand" href="index.jsp"><img src="assets/images/logo.png" alt="Progressus HTML5 template"></a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-right">
					<c:if test="${empty sessionScope.userId }">
					<li>
						<form method="get" action="controller">
						 	<input type="hidden" name="command" value="SIGN_IN_PAGE" /> 
							<button type="submit" class="btn">
								<fmt:message key="login.label.signIn" />
							</button>
						</form>
					</li>
					</c:if>
					
					<c:if test="${not empty sessionScope.userId }">
					<li>
						<form method="get" action="controller">
						 	<input type="hidden" name="command" value="SIGN_OUT" /> 
							<button type="submit" class="btn">
								SIGN OUT
							</button>
						</form>
					</li>
					<li>
						<form method="get" action="controller">
						 	<input type="hidden" name="command" value="PROFILE_PAGE" /> 
							<button type="submit" class="btn">
								${sessionScope.nickname}
							</button>
						</form>
					</li>
					</c:if>
					<li>
						<form>
				            <select id="language" name="language" onchange="submit()"  class="btn">
				                <option value="en" ${language == 'en' ? 'selected' : ''}  ><fmt:message key="login.label.eng" /></option>
				                <option value="ru" ${language == 'ru' ? 'selected' : ''}  ><fmt:message key="login.label.ru" /></option>
				            </select>
				        </form>
					</li>
				</ul>
			</div><!--/.nav-collapse -->
		</div>
	</div> 

	
	<!-- Header -->
	<header id="head">
		<div class="container">
			<div class="row">
				<h1 class="lead"><fmt:message key="login.label.welcome" /></h1>
				<p class="tagline"><fmt:message key="login.label.onSite" /></p>
				<form method="get" action="controller">
				 	<input type="hidden" name="command" value="GET_AVAILABLE_COURSE" /> 
					<button type="submit" class="btn btn-default btn-lg">
						COURSE
					</button>
				</form>

			</div>
		</div>
	</header>
	<!-- /Header -->

	<!-- Intro -->
	<div class="container text-center">
		<br>
		<c:if test="${empty requestScope.COURSES }">
			<h2 class="thin">##################################################</h2>
			<p class="text-muted"><fmt:message key="login.label.username" /></p>
		</c:if>

		<c:if test="${not empty requestScope.COURSES }">
			<c:forEach var="course" items="${requestScope.COURSES}" >
				<h2 class="thin">${course.getTitle()}</h2>
				<p class="text-muted">${course.getContent()}</p>
				<hr/>
			</c:forEach>
		</c:if>
	</div>
	


<footer id="footer" class="top-space">
		<div class="footer1">
			<div class="container">
				<div class="row">
					<div class="col-md-3 widget">
						<h3 class="widget-title">Contact</h3>
						<div class="widget-body">
							<p>+375 29 ### 64 38<br>
								<a href="mailto:#">leha.shatskiy@gmail.com</a><br>
								<br>
								Minsk
							</p>	
						</div>
					</div>
					<div class="col-md-3 widget">
						<h3 class="widget-title">Follow me</h3>
						<div class="widget-body">
							<p class="follow-me-icons">
								<a href=""><i class="fa fa-twitter fa-2"></i></a>
								<a href=""><i class="fa fa-dribbble fa-2"></i></a>
								<a href=""><i class="fa fa-github fa-2"></i></a>
								<a href=""><i class="fa fa-facebook fa-2"></i></a>
							</p>	
						</div>
					</div>
				</div> <!-- /row of widgets -->
			</div>
		</div>

		<div class="footer2">
			<div class="container">
				<div class="widget-body">
					<p class="text">
						Copyright &copy; 2017, Shatskiy Alex. 
					</p>
				</div>
			</div>
		</div>

	</footer>	
		




	<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="assets/js/headroom.min.js"></script>
	<script src="assets/js/jQuery.headroom.min.js"></script>
	<script src="assets/js/template.js"></script>
</body>
</html>
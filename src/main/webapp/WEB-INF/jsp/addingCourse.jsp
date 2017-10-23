<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" errorPage="WEB-INF/jsp/error.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport"    content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author"      content="Sergey Pozhilov (GetTemplate.com)">
	
	<title>signIn</title>

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

<body>
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
								SIGN IN
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
				</ul>
			</div><!--/.nav-collapse -->
		</div>
	</div> 
	<!-- /.navbar -->

	<header id="head" class="secondary"></header>

	<!-- container -->
	<div class="container">
		<ol class="breadcrumb">
			<li>
				<c:if test="${not empty requestScope.errorMessage }">
					<div class="alert alert-warning">
						<c:out value="${requestScope.errorMessage}"></c:out>
					</div>
				</c:if>
			</li>
		</ol>

		<div class="row">
			
			<!-- Article main content -->
			<article class="col-xs-12 maincontent">
				
				<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
					<div class="panel panel-default">
						<div class="panel-body">
							<h3 class="thin text-center">Add course</h3>
							<hr>						
							<form form method="post" action="controller">
								<input type="hidden" name="command" value="ADD_COURSE" />
								<div class="top-margin">
									<label>Title <span class="text-danger">*</span></label>
									<textarea name="title" cols = "50" rows="1" class="form-control" required="required"></textarea></p>
								</div>
								<div class="top-margin">
									<label>Content <span class="text-danger">*</span></label>
									<textarea name="content" cols = "50" rows="7" class="form-control" required="required"></textarea>
								</div>
							<hr>
								<div class="col-lg-4 text-right">
									<button class="btn btn-action" type="submit">ADD</button>
								</div>
							</form>
						</div>
					</div>
				</div>	
			</article>
			<!-- /Article -->
		</div>

	</div>	<!-- /container -->
	
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
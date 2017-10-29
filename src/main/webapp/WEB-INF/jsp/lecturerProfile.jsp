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
	
	<title>lecturer</title>

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

		<div class="row">
			
			<!-- Article main content -->
			<article class="col-sm-8 maincontent">
				<ol class="breadcrumb">
					<li>
						<c:if test="${not empty requestScope.errorMessage }">
							<div class="alert alert-warning">
								<c:out value="${requestScope.errorMessage}"></c:out>
							</div>
						</c:if>
						<c:if test="${not empty requestScope.message }">
							<div class="alert alert-success">
								<c:out value="${requestScope.message}"></c:out>
							</div>
						</c:if>
					</li>
				</ol>
			
				<c:if test="${not empty requestScope.coursesAll }">
					<c:forEach var="course" items="${requestScope.coursesAll}" >
						<h2 class="thin">${course.getTitle()}</h2>
						<p class="text-muted">${course.getContent()}</p>
						<hr/>
						<form method="post" action="controller">
						 	<input type="hidden" name="command" value="ADD_LECTURER_COURSE" />
						 	<input type="hidden" name="courseId" value="${course.getCourseId()}" />
							<button type="submit" class="btn">
								ADD LECTURER COURSE
							</button>
						</form>
					</c:forEach>
				</c:if>
				
				<c:if test="${not empty requestScope.coursesLecturer }">
					<c:forEach var="course" items="${requestScope.coursesLecturer}" >
						<h2 class="thin">${course.getTitle()}</h2>
						<p class="text-muted">${course.getContent()}</p>
						<ul class="nav navbar-nav pull-right">
							<li>
								<form method="post" action="controller">
								 	<input type="hidden" name="command" value="DELETE_LECTURER_COURSE" />
								 	<input type="hidden" name="courseId" value="${course.getCourseId()}" />
									<button type="submit" class="btn">
										DELETE
									</button>
								</form>
							</li>
							<li>
								<form method="get" action="controller">
								 	<input type="hidden" name="command" value="GET_STUDENT_STUDY" />
								 	<input type="hidden" name="courseId" value="${course.getCourseId()}" />
								 	<input type="hidden" name="title" value="${course.getTitle()}" />
									<button type="submit" class="btn">
										STUDENT STUDY
									</button>
								</form>
							</li>
							<li>
								<form method="get" action="controller">
								 	<input type="hidden" name="command" value="GET_STUDENT_FINISH" />
								 	<input type="hidden" name="courseId" value="${course.getCourseId()}" />
								 	<input type="hidden" name="title" value="${course.getTitle()}" />
									<button type="submit" class="btn">
										STUDENT FINISH
									</button>
								</form>
							</li>
						</ul>
						<hr>
					</c:forEach>
				</c:if>
				
				<c:if test="${not empty requestScope.coursesAvailable}">
					<c:forEach var="course" items="${requestScope.coursesAvailable}" >
						<h2 class="thin">${course.getTitle()}</h2>
						<p class="text-muted">${course.getContent()}</p>
						<hr/>
						<form method="get" action="controller">
						 	<input type="hidden" name="command" value="GET_ALL_LECTURER_COURSE" />
						 	<input type="hidden" name="courseId" value="${course.getCourseId()}" />
						 	<input type="hidden" name="title" value="${course.getTitle()}" />
							<button type="submit" class="btn">
								ENROLL COURSE LECTURERS
							</button>
						</form>
					</c:forEach>
				</c:if>
				
				<c:if test="${not empty requestScope.lecturersCourse}">
					<h2>${requestScope.title}</h2>
					<hr/>
					<c:forEach var="lecturer" items="${requestScope.lecturersCourse}" >
						<h2 class="thin">${lecturer.getNickname()}</h2>
						<hr/>
						<ul class="nav navbar-nav pull-right">
							<li>
								<form method="get" action="controller">
								 	<input type="hidden" name="command" value="GET_ALL_COURSE_LECTURER" /> 
								 	<input type="hidden" name="lecturerId" value="${lecturer.getUserId()}" /> 
									<button type="submit" class="btn">
										GET ALL COURSES LECTURER
									</button>
								</form>
							</li>
							<li>
								<form method="post" action="controller">
								 	<input type="hidden" name="command" value="ENROLL_FOR_COURSE" /> 
								 	<input type="hidden" name="lecturerId" value="${lecturer.getUserId()}" />
								 	<input type="hidden" name="courseId" value="${requestScope.courseId}" /> 
									<button type="submit" class="btn">
										ENROLL
									</button>
								</form>
							</li>
						</ul>
					</c:forEach>
				</c:if>
				<c:if test="${not empty requestScope.studentsStudy}">
					<h2>${requestScope.title}</h2>
					<hr/>
					<c:forEach var="student" items="${requestScope.studentsStudy}" >
						<h2 class="thin">${student.getNickname()}</h2>
						<hr/>
						<ul class="nav navbar-nav pull-right">
							<li>
								<form method="post" action="controller">
								 	<input type="hidden" name="command" value="RATE_STUDENT_PAGE" /> 
								 	<input type="hidden" name="userId" value="${student.getUserId()}" />
								 	<input type="hidden" name="courseId" value="${requestScope.courseId}" />
								 	<input type="hidden" name="title" value="${requestScope.title}" /> 
								 	<input type="hidden" name="nickname" value="${student.getNickname()}" /> 
									<button type="submit" class="btn">
										RATE_PAGE
									</button>
								</form>
							</li>
						</ul>
					</c:forEach>
				</c:if>
				
				<c:if test="${not empty requestScope.studentsFinish}">
					<h2>${requestScope.title}</h2>
					<hr/>
					<c:forEach var="student" items="${requestScope.studentsFinish}" >
						<h2 class="thin">${student.getNickname()}</h2>
						<hr/>
						<ul class="nav navbar-nav pull-right">
							<li>
								
							</li>
						</ul>
					</c:forEach>
				</c:if>
				
				<c:if test="${not empty requestScope.coursesDiploma}">
					<c:forEach var="course" items="${requestScope.coursesDiploma}" >
						<h2 class="thin">${course.getTitle()}</h2>
						<p class="text-muted">${course.getContent()}</p>
						<hr/>
						<form method="get" action="controller">
						 	<input type="hidden" name="command" value="GET_DIPLOMA" />
						 	<input type="hidden" name="courseId" value="${course.getCourseId()}" />
						 	<input type="hidden" name="title" value="${course.getTitle()}" />
							<button type="submit" class="btn">
								GET dIPOMA
							</button>
						</form>
					</c:forEach>
				</c:if>
				
				<c:if test="${not empty requestScope.diploma}">
					
					<h2 class="thin">${requestScope.title}</h2>
					<br>
					<c:if test="${not empty requestScope.diploma.getComment()}">
						<p class="text-muted">Comment: ${requestScope.diploma.getComment()}</p>
						<br>
						<p class="text-muted">Rating: ${requestScope.diploma.getRating()}</p>
					</c:if>
					<c:if test="${empty requestScope.diploma.getComment()}">
						<p class="text-muted">You study this course</p>
					</c:if>
				</c:if>
				
			</article>
			<!-- /Article -->
			
			<!-- Sidebar -->
			<aside class="col-sm-4 sidebar sidebar-right">

				<div class="widget">
					<form  method="get" action="controller">
						<input type="hidden" name="command" value="SEARCH_AVAILABLE_COURSE" /> 
				  		<input type="text" name="titleOrContent" class="form-control">
				  		
						<button type="submit" class="btn">SEARCH</button>
					</form>
					<hr>

					<ul class="list-unstyled list-spaces">
						<li>
							<form method="get" action="controller">
							 	<input type="hidden" name="command" value="PROFILE_PAGE" /> 
								<button type="submit" class="btn">
									ALL COURSE
								</button>
							</form>
						</li>
						<li>
							<form method="get" action="controller">
							 	<input type="hidden" name="command" value="ADD_COURSE_PAGE" /> 
								<button type="submit" class="btn">
									ADD COURSE
								</button>
							</form>
						</li>
						<li>
							<form method="get" action="controller">
							 	<input type="hidden" name="command" value="GET_ALL_COURSE_LECTURER" /> 
								<button type="submit" class="btn">
									GET ALL COURSE LECTURER
								</button>
							</form>
						</li>
						<li>
							<form method="get" action="controller">
							 	<input type="hidden" name="command" value="GET_AVAILABLE_COURSE" /> 
								<button type="submit" class="btn">
									GET AVAILABLE COURSE
								</button>
							</form>
						</li>
						<li>
							<form method="get" action="controller">
							 	<input type="hidden" name="command" value="GET_COURSE_STUDY" /> 
								<button type="submit" class="btn">
									GET COURSE study
								</button>
							</form>
						</li>
						<li>
							<form method="get" action="controller">
							 	<input type="hidden" name="command" value="GET_COURSE_FINISH" /> 
								<button type="submit" class="btn">
									GET COURSE finish
								</button>
							</form>
						</li>
					</ul>
					<br>
					<br>
					<br>
					<br>
					<br>
				</div>

			</aside>
			<!-- /Sidebar -->

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
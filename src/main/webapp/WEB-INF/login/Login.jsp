<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- shamelessly lifted from bootstrap example -->
<html>
<head>
<title>Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/sticky-footer.css" rel="stylesheet">
<!-- page specific css -->
<link href="/css/login.css" rel="stylesheet">


<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body>
  <br/>
	<div class="container">
		<s:if test="hasActionErrors()">
			<s:iterator value="getActionErrors().iterator()" status="errStatus">
				<div class="row">
					<div
						class="alert alert-danger alert-dismissible col-xs-10 col-xs-offset-1 col-md-6 col-md-offset-3"
						role="alert">
						<button type="button" class="close" data-dismiss="alert">
							<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
						</button>
						<s:property />
					</div>
				</div>
			</s:iterator>
		</s:if>
		<div class="row">
			<div class="col-xs-4 col-xs-offset-4 col-md-2 col-md-offset-5">
				<img class="img-responsive" id="g-img"
					src="/images/login/google_base.png">
			</div>
		</div>
		<div class="row">
      <div class="col-xs-4 col-xs-offset-4 col-md-2 col-md-offset-5">
				<p class="or">OR</p>
			</div>
		</div>
		<div class="row">
      <div class="col-xs-4 col-xs-offset-4 col-md-2 col-md-offset-5">
				<button id="selfLogin" class="btn btn-primary btn-lg">Sign
					Up/Login</button>
			</div>
		</div>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="/js/jquery.min.js"></script>
  <script src="/js/bootstrap.min.js"></script>
	<script type="text/javascript">
    var authGoogleURL = '<s:property value="%{googleAuthURL}" />';
  </script>
	<script src="/js/login.js"></script>
</body>
</html>

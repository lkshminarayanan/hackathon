<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Clean-India : Authorize</title>

<!-- Bootstrap -->
<link href="/css/bootstrap.min.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<br/>
	<div class="container">
    <div class="row">
    <div class="col-sm-10 col-sm-offset-1">
    <h1 class="center">Clean-India : </h1>
    </br>
    <s:if test="!hasActionErrors()">
    Authentication Success!!</br>
    Redirecting to application...
    
    </s:if><s:else>
					<s:iterator value="getActionErrors().iterator()" status="errStatus">
						<div class="row">
							<div
								class="alert alert-danger col-xs-10 col-xs-offset-1 col-md-6 col-md-offset-3"
								role="alert">
							
								<s:property />
							</div>
						</div>
					</s:iterator>
					<br/>
    <button id="backButton" class="btn btn-primary bt-lg">Go Back</button>
    </s:else>
    </div>
    </div>
    <!-- EO Row -->
  </div>
  <script type="text/javascript">
  <s:if test="hasActionErrors()">
  var err = true;
  </s:if>
  <s:else>
  var err = false;
  </s:else>
  var domain = '<s:text name="global.domain"/>';
  var returnUrl = '<s:property value="%{returnURL}" />';
  </script>
   <script src="/js/jquery.min.js"></script>
  <script src="/js/loginSuccess.js"></script>
</body>
</html>

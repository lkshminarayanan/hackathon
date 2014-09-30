<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Clean-India</title>

<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/sticky-footer.css" rel="stylesheet">
<!-- page specific css -->
<link href="/css/index.css" rel="stylesheet">
<link href="/css/map.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
  <%@ include file="header.jsp"%>
	<div class="container content">
		<!-- Map Row -->
		<div class="row">

      <div id="map-div" class="col-xs-12 col-sm-8">
        <div id="map-canvas"></div>
      </div>
      <div id="map-list-div" class="col-xs-6 col-sm-4 hidden-xs">
				<s:if test="%{userSignedIn != true}">
					<div class="alert alert-info alert-dismissible" role="alert">
						<button type="button" class="close" data-dismiss="alert">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<strong>Hi Guest!</strong> To plan or report a spotfix, please Login.
					</div>
				</s:if>
				<div id="map-list" class="list-group">
          <div class="list-group-item">
            <h4>What do you want to do with a Spot-fix?</h4>
						<div id="radioChoices" class="btn-group btn-group-justified" data-toggle="buttons">
							<label class="btn btn-default active"> <input
								type="radio" name="options" id="option1" checked> View
							</label><label class='btn btn-default <s:if test="%{userSignedIn != true}">disabled</s:if>'> <input type="radio"
								name="options" id="option2"> Create
							</label><label class='btn btn-default <s:if test="%{userSignedIn != true}">disabled</s:if>'><input type="radio"
								name="options" id="option3">Report
							</label>
						</div>
					</div>
					<div class="list-group-item">
						<span id="qn"><h4>Where do you want to do a spot-fix?</h4></span>
            <p>
              <div id="spotfixInput">
                <input id="locationAutoComplete" type="text" class="form-control" size="20" placeholder="Start typing your locality">
              </div>
              <div id="spotfixText">
              <span id="spotfixTextLocation"></span>
              <a href="#"><span id="change-addr" class="pull-right">&times;</span></a>
              </div>
            </p>
          </div>
        </div>
      </div>
    </div>
    <!-- EO Row -->
	</div>


	<%@ include file="footer.jsp"%>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<!-- <script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> -->
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA30NggOxjykJqEbR6Snf1A-vHBybPc3uA&v=3.exp&libraries=places"></script>
	<script src="/js/index.js"></script>
  <script src="/js/map.js"></script>
</body>
</html>

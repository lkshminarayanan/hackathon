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
       
       
       <s:if test="%{pageType.toString()=='HOME'}">
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
								type="radio" name="options" value="1" checked> Search
							</label><label class='btn btn-default <s:if test="%{userSignedIn != true}">disabled</s:if>'> <input type="radio"
								name="options" value="2"> Plan
							</label><label class='btn btn-default <s:if test="%{userSignedIn != true}">disabled</s:if>'><input type="radio"
								name="options" value="3">Report
							</label>
						</div>
					</div>
					<div class="list-group-item">
						<h4 id="qn">Your Locality : </h4>
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
          <div id="s1" class="list-group-item">
            <div class="create">
              <h4>Plan a Spot-fix</h4>
              <p>
              Adjust the marker in the map to point the exact spot to be fixed.<br/><br/>
              <button type="button" class="btn btn-default" id="addSpot">Add Spot</button>
              </p>
            </div>
          </div>
        </div>
        
        <div id="spot-list" class="list-group">
          
        </div>
        </s:if>
        
       
       
        <s:if test="%{pageType.toString()=='VIEW'}">
        <div id="map-list" class="list-group">
          <div class="list-group-item">
            <h4>At </h4> 
            <p><s:property value="location.locationName"/></p>
          </div>
          <div class="list-group-item">
            <h4><s:if test="%{spotfix.wasInThePast()}">Was </s:if>On</h4>
            <p><s:property value="spotfix.presentableTime"/></p>
          </div>
          <div class="list-group-item">
            <h4>Description</h4>
            <p><s:property value="spotfix.message"/></p>
          </div>
          <s:if test="%{spotfix.wasInThePast()}">
              <div class="list-group-item">
                <h4>Attendance last time</h4>
                <p>
                  <s:property value="spotfix.numOfPeopleSignedUp" /><br/>
                  <button type="button" class="btn btn-default" id="planSpot">Plan another on the same spot</button>
                </p>
                </div>
          </s:if>
          <s:else>
							<div class="list-group-item">
								<h4>People Signedup : Target</h4>
								<p>
									<span id="numOfPeopleSignedUp"><s:property value="spotfix.numOfPeopleSignedUp" /></span>
									 / 
									<s:property value="spotfix.target"/>
									<br />
								  <div class="progress">
									 <div class="progress-bar" role="progressbar" aria-valuenow="0"
										  aria-valuemin="0" aria-valuemax="100" id="goalProgress"></div>
								  </div>
								  <span id="joinSpotSpan">
								  <button type="button" class="btn btn-default" id="joinSpot">I will attend</button>
								  </span>
								</p>
							</div>
						</s:else>
						<div class="list-group-item">
						<div id="fb-root"></div>
							<a class="twitter-share-button" href="https://twitter.com/share">
								Tweet </a>
							
              <span class="fb-share-button"></span>

						</div>
        </div>
        </s:if>
      
      
      
      </div>
    </div>
    <!-- EO Row -->
	</div>

  <!-- Create spot-fix modal -->
	<div class="modal fade" id="createModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Plan a Spotfix</h4>
				</div>
				
					<form class="form-horizontal" role="form" action="createSpotfix" method="post"> 
					<input type="hidden" name="lat" id="lat">
          <input type="hidden" name="lng" id="lng">
          <input type="hidden" name="locationId" id="locationId">
					<div class="modal-body">
						<div class="form-group">
							<label for="location" class="col-sm-2 control-label">Location</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="location"
									name="locationName">
							</div>
						</div>
            <div class="form-group">
              <label for="date" class="col-sm-2 control-label">Date&time</label>
              <div class="col-sm-10">
                <input type="date" class="form-control" id="date" min="2000-01-02"
                  name="date" >
              </div>
            </div>
            <div class="form-group">
              <label for="goal" class="col-sm-2 control-label">Target Attendance</label>
              <div class="col-sm-10">
                <input type="number" class="form-control" id="goal" min="2"
                  name="target" >
              </div>
            </div>
            <div class="form-group">
              <label for="message" class="col-sm-2 control-label">Description</label>
              <div class="col-sm-10">
                <textarea rows="5" class="form-control" id="message"
                  name="message"></textarea>
              </div>
            </div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Go Back</button>
					<button type="submit" class="btn btn-primary">Submit Plan</button>
				</div>
				</form>
			</div>
		</div>
	</div>



	<%@ include file="footer.jsp"%>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<!-- <script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> -->
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA30NggOxjykJqEbR6Snf1A-vHBybPc3uA&v=3.exp&libraries=places"></script>
	<script src="/js/map.js"></script>
	<s:if test="%{pageType.toString()=='HOME'}">
	<script src="/js/index.js"></script>
	</s:if>
	<s:if test="%{pageType.toString()=='VIEW'}">
	<script type="text/javascript">
	 var achieved = <s:property value="spotfix.numOfPeopleSignedUp" />;
	 var goal = <s:property value="spotfix.target" />;
	 var locationId = <s:property value="spotfix.locationId"/>;
	 var lat = <s:property value="location.lat" />;
	 var lng = <s:property value="location.lng" />;
	 var locationName = "<s:property value='location.locationName' />";
	</script>
	<script src="/js/view.js"></script>
	<script type="text/javascript">
        window.twttr = (function(d, s, id) {
            var t, js, fjs = d.getElementsByTagName(s)[0];
            if (d.getElementById(id)) {
                return

                

            }
            js = d.createElement(s);
            js.id = id;
            js.src = "https://platform.twitter.com/widgets.js";
            fjs.parentNode.insertBefore(js, fjs);
            return window.twttr || (t = {
                _e : [],
                ready : function(f) {
                    t._e.push(f)
                }
            })
        }(document, "script", "twitter-wjs"));
    </script>
    <script>
    
                    (function(d, s, id) {
                        var js, fjs = d.getElementsByTagName(s)[0];
                        if (d.getElementById(id))
                            return;
                        js = d.createElement(s);
                        js.id = id;
                        js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&appId=267202773302968&version=v2.0";
                        fjs.parentNode.insertBefore(js, fjs);
                    }(document, 'script', 'facebook-jssdk'));
                </script>
	</s:if>
</body>
</html>

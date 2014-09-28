<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<header class="navbar navbar-default navbar-fixed-top navbar-inverse"
	role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/theholygrail"><div id="title">
					Clean India</div></a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li <s:if test="%{pageType.toString()=='HOME'}">class="active"</s:if>>
					<a href="home">Home</a>
				</li>
				<li <s:if test="%{pagetype.toString()=='SEARCH'}">class="active"</s:if>><a
					href="home">Search Spotfix</a></li>
				<li <s:if test="%{pageType.toString()=='CREATE'}">class="active"</s:if>><a
					href="home">Create Spotfix</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<s:if test="%{userSignedIn == true}">
					<li><img id="dp" alt="DP"
						src='<s:property value="%{#session.user.getImgUrl()}"/>'>&nbsp;</li>
					<li class="dropdown set-min-width-220"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"> <s:property
								value="%{#session.user.getFancyDisplayName()}" /> <b
							class="caret"></b></a>
						<ul class="dropdown-menu set-full-width">
							<li><a class="dummy-link" href="#">Edit Account
							</a></li>
							<li class="divider"></li>
							<li><a href="/logout">Logout</a></li>
							<li></li>
						</ul></li>
				</s:if>
				<s:else>
					<li class="dropdown set-min-width-220"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown">Hi Guest!
							Signup/Login <b class="caret"></b>
					</a>
						<ul class="dropdown-menu set-full-width">

							<li>
								<div class="text-center">
									<img class="img-responsive auto-align" id="g-img"
										src="/images/login/google_base.png">
								</div>
							</li>
							<li class="divider"></li>
							<li>
								<div class="text-center">
									<button id="selfLogin" class="btn btn-primary btn-lg disabled">Sign
										Up/Login</button>
								</div>
							</li>
						</ul></li>
				</s:else>
			</ul>
		</div>
	</div>
</header>
<script type="text/javascript">
    var authGoogleURL = '<s:property value="%{googleAuthURL}" />';
</script>
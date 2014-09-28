$(document).ready(function() {
	$("#g-img").hover(function(){
		$(this).attr("src", "/images/login/google_hover.png");
	},
	function(){
		$(this).attr("src", "/images/login/google_base.png");
	});
	$("#g-img").mousedown(function(){
		$(this).attr("src", "/images/login/google_press.png");
	});
	$("#g-img").mouseup(function(){
		$(this).attr("src", "/images/login/google_hover.png");
	});
	
	$("#g-img").click(function(){
		//login handled by google
		openLoginWindow('google');
	});
	
	$("#selfLogin").click(function(){
		//login handled by site
		openLoginWindow('self');
	});
});

var openLoginWindow = function(module){
	window.name= "playTHGWindow";
	var link = "";
	var winHandle;
	if(module == 'google'){
		link = authGoogleURL.replace(/amp;/g,'');
		console.log(link,authGoogleURL);
		//open the link in a new sizable window.
		winHandle = window.open(link,'','width=500,height=800,scrollbars=yes,menubar=yes,status=yes,resizable=yes,directories=false,location=false,left=150,top=100');
	
	} else if (module == 'self'){
		link = "/loginSelf";
		//open the link in a new sizable window.
		winHandle = window.open(link,'','width=400,height=350,scrollbars=yes,menubar=yes,status=yes,resizable=yes,directories=false,location=false,left=150,top=150');
	} else {
		//unreachable. unless somebody tweaks something.
		alert("Welldone bro!");
	}
	return;
}
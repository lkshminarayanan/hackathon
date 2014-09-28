$(document).ready(function() {
    $('.dummy-link').click(function(e){
        e.preventDefault();
    });
    
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
        //openLoginWindow('self');
    });
});

var openLoginWindow = function(module){
    window.name= "cleanindia";
    var link = "";
    var width = 580;
    var height = 600;
    var x = (screen.width - width)/2;
    var y = 60;
    var options = 'width=' + width + ',height=' + height 
                  + ',scrollbars=yes,menubar=yes,status=yes,resizable=yes,directories=false,location=false'
                  + ',left='+x+',top='+y;
    var winHandle;
    if(module == 'google'){
        link = authGoogleURL.replace(/amp;/g,'');
        console.log(link,authGoogleURL);
        //open the link in a new sizable window.
        winHandle = window.open(link,'',options);
    
    } else if (module == 'self'){
        link = "/loginSelf";
        //open the link in a new sizable window.
        winHandle = window.open(link,'',options);
    }
    return;
}
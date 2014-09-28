$(document).ready(function(){
    var link;
    var wait = 0;
    link = domain + returnUrl;
    if(!err){
        wait = .5;
        window.open(link, "cleanindia");
        setTimeout(function() {
            window.close();
        }, (wait * 1000));
    } else {
        
    }
    $("#backButton").click(function(){
        window.open(link, "cleanindia");
        window.close();
    });
});
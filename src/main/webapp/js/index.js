var operationType = 1;
var locationMarker;
var map = initializeMap();

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

var postLocationChoiceProcessing = function(place){
    switch(parseInt(operationType, 10)){
    case 1:
        /* search for nearby spotfixes */
        //TODO: proper ajax request
        /* mockups for now */
        alert("search not fully implemented. But a similar view will be generated");
        $(".create").hide();
        var header = '<div class="list-group-item">\
            <div class="search">\
            <h4>Nearby upcoming spotfixes : </h4>\
            </div>\
            </div>';
        var spot1 = '<a target="_blank" class="list-group-item" href="http://clean-india.appspot.com/viewSpotfix?s=1">\
            <div class="search">\
            <h4>Venkadatri Layout</h4>\
            </div>\
            </a>';
        var lat1 = 12.897425755377089;
        var lng1 = 77.59559085872195;
        var spot2 = '<a target="_blank" class="list-group-item" href="http://clean-india.appspot.com/viewSpotfix?s=2">\
            <div class="search">\
            <h4>Near Apollo hospital Road</h4>\
            </div>\
            </a>';
        var lat2 = 12.89706568726406;
        var lng2 = 77.59718411415565;
        $("#spot-list").html(header + spot1 + spot2);
        $("#spot-list").show();
        var latLng1 = new google.maps.LatLng(lat1,lng1);
        var latLng2 = new google.maps.LatLng(lat2,lng2);
        var bounds = new google.maps.LatLngBounds();
        bounds.extend(latLng1);
        bounds.extend(latLng2);
        map.fitBounds(bounds);
        addMarker(map, latLng1, "Venkadatri Layout");
        addMarker(map, latLng1, "Near Apollo hospital Road");
        break;
    case 2:
        $("#search").hide();
        /* create a spotfix */
        locationMarker.setDraggable(true);
        $("#s1").show();
        $("#s1 .create").show();
        /* set the address in modal box */
        $("#location").val(place.formatted_address); 
        break;
    case 3:
        /* post a report */
        break;
    default:
        break;
    }
}

Date.prototype.toDateInputValue = (function() {
    var local = new Date(this);
    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
    return local.toJSON().slice(0,10);
});

$(document).ready(function() {
    var autocomplete = initializeAutoComplete(map);
    var bounds = new google.maps.LatLngBounds();
    var infoWindow = new google.maps.InfoWindow();
    var markerData = [];

    /* retriveSpotfixes(map, bounds, infoWindow, markerData, 0); */

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

    $("input[name='options']").change(function(e){
        operationType = e.currentTarget.value;
        if(autocomplete.getPlace() !== undefined){
            postLocationChoiceProcessing(autocomplete.getPlace());
        }
    });

    $("#addSpot").click(function(){
        $('#date').val(new Date().toDateInputValue());
        $('#date').attr('min', $('#date').val());
        $("#lat").val(locationMarker.getPosition().lat());
        $("#lng").val(locationMarker.getPosition().lng());
        $('#createModal').modal({
            backdrop: 'static'
        });
    });
});
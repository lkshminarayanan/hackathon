var locationMarker;

Date.prototype.toDateInputValue = (function() {
    var local = new Date(this);
    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
    return local.toJSON().slice(0,10);
});

var updateProgressBar = function(){
    var progress = ((achieved/goal)*100).toFixed(2);
    $("#goalProgress").css("width", progress+"%" );
    $("#goalProgress").attr("aria-valuenow", progress);
}

$(document).ready(function() {
    var latLng = new google.maps.LatLng(lat,lng);
    var map = initializeMap(latLng);
    var infoWindow = new google.maps.InfoWindow();
    updateProgressBar();
    addMarker(map, latLng, locationName);
    
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
    
    $("#joinSpot").click(function(e){
        e.preventDefault();
        achieved++;
        $("#numOfPeopleSignedUp").text(achieved);
        $("#joinSpotSpan").html("You are going!!");
        updateProgressBar();
    });
    
    $("#planSpot").click(function(e){
        e.preventDefault();
        $('#date').val(new Date().toDateInputValue());
        $('#date').attr('min', $('#date').val());
        $("#location").val(locationName);
        $("#locationId").val(locationId);
        $('#createModal').modal({
            backdrop: 'static'
        });
    });
});
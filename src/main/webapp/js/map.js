function initializeMap() {
    
    /* set map's height dynamically */
    $("#map-canvas").height(.75 * $(window).height());
    /* and adjust it on window resizing */
    $(window).resize(function() {
        $("#map-canvas").height(.75 * $(window).height());
    });
    
    var mapOptions = {
            mapTypeControl: false,
            maxZoom: 18,
            minZoom: 1,
            panControl: false,
            streetViewControl : false,
            zoom: 4,
            zoomControlOptions : {
                style: "SMALL"
            }
    };
    var map = new google.maps.Map(document.getElementById('map-canvas'),
            mapOptions);
    
    /* center map on india */
    var country = "India";
    var geocoder = new google.maps.Geocoder();
    
    geocoder.geocode( {'address' : country}, function(results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
            map.panTo(results[0].geometry.location);
        }
    });
    
    return map;
}

var initializeAutoComplete = function(map){
    
    /* initialize the textbox */
    var autocomplete = new google.maps.places.Autocomplete(
            /** @type {HTMLInputElement} */
            (document.getElementById('locationAutoComplete')),
            { 
                types: ['geocode'],
                componentRestrictions: {
                    country: 'IN'
                }
            }
    );

    google.maps.event.addListener(autocomplete, 'place_changed', function() {
        var place = autocomplete.getPlace();
        $("#spotfixTextLocation").text(place.formatted_address);
        $("#spotfixText").show();
        $("#spotfixInput").hide();
        console.log(place);
        console.log(place.geometry.location);
        map.panTo(place.geometry.location);
        map.setZoom(15);
        addMarker(map, place.geometry.location, place.address_components[0].long_name);
    });
    
    /* register the 'x' everytime */
    $("#change-addr").click(function(e){
        e.preventDefault();
        $("#spotfixText").hide();
        $("#spotfixTextLocation").text("");
        $("#spotfixInput").val("");
        $("#spotfixInput").show();
    });
    
    return autocomplete;
};

String.prototype.toPrintableTime = function(){
    var time = new Date(0);
    time.setUTCSeconds(parseInt(this, 10));
    
    var options = {
            weekday: "long", year: "numeric", month: "short",
            day: "numeric", hour: "2-digit", minute: "2-digit"
    };
    return time.toLocaleTimeString("en-us", options);
}

var addMarker = function(map, location, title){
    var marker = new google.maps.Marker({
        map:map,
        draggable:true,
        animation: google.maps.Animation.DROP,
        position: location,
        title: title,
        draggable:false
    }); 
};

$(document).ready(function(){
    var map = initializeMap();
    var autocomplete = initializeAutoComplete(map);
    var bounds = new google.maps.LatLngBounds();
    var infoWindow = new google.maps.InfoWindow();
    var markerData = [];

    /* retriveSpotfixes(map, bounds, infoWindow, markerData, 0); */
});
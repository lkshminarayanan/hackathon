function initializeMap(center) {
    
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
    
    if(center === undefined){
        /* center map on india */
        var country = "India";
        var geocoder = new google.maps.Geocoder();

        geocoder.geocode( {'address' : country}, function(results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                map.panTo(results[0].geometry.location);
            }
        });
    } else {
        map.setZoom(15);
        map.panTo(center);
    }
    
    return map;
}

var initializeAutoComplete = function(map){
    /* initialize the textbox */
    var tBox = document.getElementById('locationAutoComplete');
    var autocomplete = new google.maps.places.Autocomplete(tBox,{ 
        types: ['geocode'],
        componentRestrictions: {
            country: 'IN'
        }
    });

    google.maps.event.addListener(autocomplete, 'place_changed', function() {
        var place = autocomplete.getPlace();
        $("#spotfixTextLocation").text(place.formatted_address);
        $("#spotfixText").show();
        $("#spotfixInput").hide();
        map.panTo(place.geometry.location);
        map.setZoom(15);
        locationMarker = addMarker(map, place.geometry.location, place.address_components[0].long_name, place.icon);
        postLocationChoiceProcessing(place);
    });
    
    /* register the 'x' everytime */
    $("#change-addr").click(function(e){
        e.preventDefault();
        locationMarker.setMap(null);
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

var addMarker = function(map, location, title, icon){
    var options = {
        map:map,
        draggable:true,
        animation: google.maps.Animation.DROP,
        position: location,
        title: title,
        draggable:false
    }
    if(icon !== undefined){
        options.icon = icon;
    }
    var marker = new google.maps.Marker(options); 
    return marker;
};
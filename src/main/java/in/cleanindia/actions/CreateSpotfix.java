package in.cleanindia.actions;

import in.cleanindia.models.Location;
import in.cleanindia.models.Spotfix;
import in.cleanindia.models.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateSpotfix extends BasicAction {

    private static final long serialVersionUID = -8396089452293667071L;
    
    private Date date;
    private String locationName;
    private String message;
    private double lat;
    private double lng;
    private long target;
    private long locationId;
    private String viewUrl;
    
    public void setDate(String _date) throws ParseException {
        date = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(_date);
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setTarget(long target) {
        this.target = target;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public String getViewUrl() {
        return viewUrl;
    }
    
    public String execute() throws Exception {
        if(locationId == 0 && lat == 0 & lng == 0){
            return HOME;
        } else if(locationId == 0) {
            Location location = new Location(locationName, lat, lng);
            locationId = location.getId();
        }
        String userId = ((User)sessionMap.get("user")).getId();
        Spotfix spotfix = new Spotfix(userId, date, locationId, target, message);
        viewUrl = "viewSpotfix?s="+spotfix.getId();
        return SUCCESS;
    }

}

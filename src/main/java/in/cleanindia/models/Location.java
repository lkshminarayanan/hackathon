package in.cleanindia.models;

import in.lkshminarayanan.utils.DataHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

/**
 * 
 * @author lkshminarayanan
 * 
 * Class to represent a Location
 * 
 */

public class Location implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 2064112920130405338L;

    private long id;
    private String locationName;
    private double lat;
    private double lng;
    ArrayList<Spotfix> spotfixes;

    private static transient DataHandler datahandler = new DataHandler();

    public Location(){ }

    public Location(long _id){
        Location _loc = Location.getLocation(_id);
        id = _id;
        locationName = _loc.locationName;
        lat = _loc.lat;
        lng = _loc.lng;
        spotfixes = Spotfix.getSpotfixListInLocation(_id);
    }

    public Location(long _id, String _name, double _lat, double _lng){
        id = _id;
        locationName = _name;
        lat = _lat;
        lng = _lng;
        spotfixes = Spotfix.getSpotfixListInLocation(_id);
    }

    public Location(String _name, double _lat, double _lng){
        locationName = _name;
        lat = _lat;
        lng = _lng;
        id = Location.datahandler.getNextAutoIncrementValue("Location", "id");
        storeLocation();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String location) {
        this.locationName = location;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public ArrayList<Spotfix> getSpotfixes() {
        return spotfixes;
    }

    public void setSpotfixes(ArrayList<Spotfix> spotfixes) {
        this.spotfixes = spotfixes;
    }

    public int getTotalSpotfixes(){
        return spotfixes.size();
    }

    private static Entity locationToEntity(Location loc){
        Entity entity = new Entity("Location", loc.id);
        entity.setProperty("id", loc.id);
        entity.setProperty("locationName", loc.locationName);
        entity.setProperty("lat", loc.lat);
        entity.setProperty("lng", loc.lng);
        return entity;
    }

    private static Location entityToLocation(Entity entity){
        return new Location((long)entity.getProperty("id"),
                (String)entity.getProperty("locationName"),
                (double)entity.getProperty("lat"),
                (double)entity.getProperty("lng")
                );
    }

    private static Location getLocation(long _id){
        Query query = new Query("Location").setFilter(FilterOperator.EQUAL.of("id", _id));
        List<Entity> locations = Location.datahandler.executeQuery(query);
        return entityToLocation(locations.get(0));
    }

    public void storeLocation(){
        Entity entity = Location.locationToEntity(this);
        Location.datahandler.insert(entity);
    }
    
    public void addSpotFix(Spotfix sf){
        if(spotfixes == null){
            spotfixes = new ArrayList<Spotfix>();
        }
        spotfixes.add(sf);
    }
}

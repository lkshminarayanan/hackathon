package in.cleanindia.models;

import in.lkshminarayanan.utils.DataHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.SortDirection;

public class Spotfix {

    private long id;
    private String ownerId;
    private Date   plannedDate;
    /* Num of people signed up for upcoming spotfix */
    private long numOfPeopleSignedUp;
    private long target;
    private long locationId;
    private String message;

    private static transient DataHandler datahandler = new DataHandler();

    public Spotfix() {}

    public Spotfix(long _id){
        Spotfix sf = Spotfix.getSpotfix(_id);
        id = _id;
        ownerId = sf.ownerId;
        plannedDate = sf.plannedDate;
        locationId = sf.locationId;
        numOfPeopleSignedUp = sf.numOfPeopleSignedUp;
        message = sf.message;
        target = sf.target;
    }

    public Spotfix(String _ownerId,
                   Date _plannedDate,
                   long _locationId,
                   long _target,
                   String _message){
        id                  = Spotfix.datahandler.getNextAutoIncrementValue("Spotfix", "id");
        ownerId             = _ownerId;
        plannedDate         = _plannedDate;
        locationId          = _locationId;
        numOfPeopleSignedUp = 1; /*the owner*/
        target              = _target;
        message             = _message;
        storeSpotfix();
    }

    public Spotfix(long _id,
            String _ownerId,
            Date _plannedDate,
            long _noOfPeople,
            long _locationId,
            String _message) {
        id                  = _id;
        ownerId             = _ownerId;
        plannedDate         = _plannedDate;
        numOfPeopleSignedUp = _noOfPeople;
        locationId          = _locationId;
        message             = _message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Date getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(Date plannedDate) {
        this.plannedDate = plannedDate;
    }

    public long getNumOfPeopleSignedUp() {
        return numOfPeopleSignedUp;
    }

    public void setNumOfPeopleSignedUp(long numOfPeopleSignedUp) {
        this.numOfPeopleSignedUp = numOfPeopleSignedUp;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public long getTarget() {
        return target;
    }

    public void setTarget(long target) {
        this.target = target;
    }

    public String getPresentableTime(){
        return new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH).format(plannedDate);
    }
    
    public boolean wasInThePast(){
        Date now = new Date();
        Calendar cal_now = Calendar.getInstance();
        Calendar cal_planned = Calendar.getInstance();
        cal_now.setTime(now);
        cal_planned.setTime(plannedDate);

        if(cal_planned.after(cal_now)){
            return true;
        } else {
            return false;
        }
    }

    private static Spotfix entityToSpotfix(Entity entity){
        Spotfix sf = new Spotfix();
        sf.setId((Long)entity.getProperty("id"));
        sf.setOwnerId((String)entity.getProperty("ownerId"));
        sf.setPlannedDate((Date)entity.getProperty("plannedDate"));
        sf.setNumOfPeopleSignedUp((Long)entity.getProperty("numOfPeopleSignedUp"));
        sf.setLocationId((Long)entity.getProperty("locationId"));
        sf.setMessage((String)entity.getProperty("message"));
        sf.setTarget((Long)entity.getProperty("target"));
        return sf;
    }

    private static Entity spotfixToEntity(Spotfix sf){
        /* create key with id */
        Entity spotfix = new Entity("Spotfix", sf.id);
        spotfix.setProperty("id", sf.id);
        spotfix.setProperty("ownerId", sf.ownerId);
        spotfix.setProperty("plannedDate", sf.plannedDate);
        spotfix.setProperty("numOfPeopleSignedUp", sf.numOfPeopleSignedUp);
        spotfix.setProperty("locationId", sf.locationId);
        spotfix.setProperty("message", sf.message);
        spotfix.setProperty("target", sf.target);
        return spotfix;
    }

    public void storeSpotfix(){
        Entity spotfix = spotfixToEntity(this);
        Spotfix.datahandler.insert(spotfix);
    }

    public static Spotfix getSpotfix(long id){
        Query query = new Query("Spotfix").setFilter(FilterOperator.EQUAL.of("id", id));
        List<Entity> spotfix = Spotfix.datahandler.executeQuery(query);
        return entityToSpotfix(spotfix.get(0));
    }
    
    public static ArrayList<Spotfix> getSpotfixListOwnedBy(String ownerId){
        Query query = new Query("Spotfix").setFilter(FilterOperator.EQUAL.of("ownerId", ownerId));
        List<Entity> spotfixes = Spotfix.datahandler.executeQuery(query);
        ArrayList<Spotfix> sfs = new ArrayList<Spotfix>();
        for(Entity s : spotfixes){
            sfs.add(Spotfix.entityToSpotfix(s));
        }
        return sfs;
    }
    
    public static ArrayList<Spotfix> getSpotfixListInLocation(long locationId){
        Query query = new Query("Spotfix")
                            .setFilter(FilterOperator.EQUAL.of("locationId", locationId))
                            .addSort("plannedDate", SortDirection.DESCENDING);
        List<Entity> spotfixes = Spotfix.datahandler.executeQuery(query);
        ArrayList<Spotfix> sfs = new ArrayList<Spotfix>();
        for(Entity s : spotfixes){
            sfs.add(Spotfix.entityToSpotfix(s));
        }
        return sfs;
    }
}

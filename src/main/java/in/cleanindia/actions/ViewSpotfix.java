package in.cleanindia.actions;

import in.cleanindia.models.Location;
import in.cleanindia.models.Spotfix;

public class ViewSpotfix extends BasicAction {

    private static final long serialVersionUID = -4917051110894490309L;

    private int l;
    private int s;
    private Location location;
    /* recent or upcoming spotfix */
    private Spotfix spotfix;

    public ViewSpotfix(){
        pageType = pageTypes.VIEW;
    }

    public void setL(int l) {
        this.l = l;
    }

    public void setS(int s) {
        this.s = s;
    }

    public Location getLocation() {
        return location;
    }

    public Spotfix getSpotfix() {
        return spotfix;
    }

    public String execute() throws Exception {
        if(l==0 && s==0){
            return HOME;
            
        } else if( s!=0 ){
            /* higher preference to spotfix */
            spotfix = new Spotfix(s);
            location = new Location(spotfix.getLocationId());
            System.out.println(spotfix.getTarget());
        } else {
            location = new Location(l);
            spotfix = location.getSpotfixes().get(0);
        
        }
        return SUCCESS;
    }

}

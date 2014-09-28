package in.cleanindia.models;

import java.io.Serializable;
import java.util.List;

import in.lkshminarayanan.utils.DataHandler;

import com.google.api.services.oauth2.model.Userinfoplus;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

/**
 * 
 * @author lkshminarayanan
 *
 * abstraction over google's Userinfo class.
 * Might be useful if we start supporting other sites.
 * 
 */

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2064112920130405338L;

	private enum AuthType{GOOGLE};
	
	private AuthType authType;
	private String id;
	private String emailid;
	private String givenName;
	private String fullName;
	private String familyName;
	private String gender;
	private String imgUrl;
	private String link;

	private static transient DataHandler datahandler = new DataHandler();

	public AuthType getAuthType() {
		return authType;
	}

	public void setAuthType(AuthType authType) {
		this.authType = authType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	public String getFancyDisplayName(){
		String name = "";
		if(gender.equalsIgnoreCase("male"))
			name += "Mr.";
		else if(gender.equalsIgnoreCase("female"))
			name += "Ms.";
		name += givenName;
		return name;
	}
	
	public String getUniqueUserId(){
		return "GOOGLE"+id;
	}

	public User(){
	
	}

	public User(Userinfoplus user){
		authType      = AuthType.GOOGLE;
		id 			  = user.getId();
		emailid		  = user.getEmail();
		givenName	  = user.getGivenName();
		familyName	  = user.getFamilyName();
		fullName 	  = user.getName();
		gender		  = user.getGender();
		imgUrl		  = user.getPicture();
		link		  = user.getLink();
		
		if(isNewUser(emailid)){
			storeUser();
		}
	}

	public void storeUser(){
		//creating entity in User table with emailid as key
		Entity user = new Entity("User", emailid);
		user.setProperty("authType", authType==AuthType.GOOGLE?0:0);
		user.setProperty("id", id);
		user.setProperty("emailid", emailid);
		user.setProperty("givenName", givenName);
		user.setProperty("familyName", familyName);
		user.setProperty("fullName", fullName);
		user.setProperty("gender", gender);
		user.setProperty("imgUrl", imgUrl);
		user.setProperty("link", link);
		User.datahandler.insert(user);
	}
	
	public boolean isNewUser(String emailid){
		Query query = new Query("User").setFilter(FilterOperator.EQUAL.of("emailid", emailid));
		List<Entity> user = User.datahandler.executeQuery(query);
		if(user.size() > 0)
			return false;
		else
			return true;
	}
}

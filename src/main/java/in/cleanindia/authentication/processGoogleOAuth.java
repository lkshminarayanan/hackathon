package in.cleanindia.authentication;

import in.cleanindia.models.User;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.oauth2.model.Userinfoplus;

public class processGoogleOAuth extends ProcessLogin{

	/**
	 * Used for validation and registration!!
	 */
	private static final long serialVersionUID = 7400667104347361300L;
	//params handling reply from server
	private String code;
	private String error;
	private int state;

	public processGoogleOAuth(){ 	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setState(String state) {
		this.state = Integer.parseInt(state);
	}

	@Override
	public void validate(){
	}

	@Override
	public String execute() throws Exception {
		//the main job. Sign in
		if(error != null && error.equals("access_denied")){
			//error access denied. 
			//TODO : decide what-to-do
		    addActionError(getText("google.rejectedoauth"));
			return SUCCESS;
		}
		Credential cred;
		try{
			cred = GoogleAuthHelper.getCredentials(code, state);
		} catch (GoogleAuthHelper.NoRefreshTokenException ex){
			ex.printStackTrace();
			returnURL = ex.getAuthorizationUrl();
			return SUCCESS;
		}
		Userinfoplus userInfo = GoogleAuthHelper.getUserInfo(cred);

		User user = new User(userInfo);
		sessionMap.put("user", user);
		
		return SUCCESS;
	}
}

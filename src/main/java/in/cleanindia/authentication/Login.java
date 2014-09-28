package in.cleanindia.authentication;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class Login extends ActionSupport implements SessionAware{

    /**
     * 
     */
    private static final long serialVersionUID = -3620839083358559645L;
    private SessionMap<String,Object> sessionMap;
    private String googleAuthURL;

    @Override
    public void setSession(Map<String, Object> session) {
        this.sessionMap = (SessionMap<String,Object>)session;
    }

    public String getGoogleAuthURL() {
        if(googleAuthURL == null)
            return "";
        return googleAuthURL;
    }

    public void setGoogleAuthURL(String googleAuthURL) {
        this.googleAuthURL = googleAuthURL;
    }

    /*
     * set various auth urls and do necessary in-house-keeping here
     */
    public String execute() throws Exception {
        setGoogleAuthURL(GoogleAuthHelper.getAuthorizationUrl("", GoogleAuthHelper.AUTO));
        //redirect to login page
        return SUCCESS;
    }

    public String logout() throws Exception {
        //log out and invalidate the user
        sessionMap.invalidate();
        return SUCCESS;
    }
}
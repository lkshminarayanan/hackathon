package in.cleanindia.actions;

import in.cleanindia.authentication.GoogleAuthHelper;

import java.io.IOException;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public abstract class BasicAction extends ActionSupport implements SessionAware{

    private static final long serialVersionUID = 8287857932994611367L;
    protected SessionMap<String, Object> sessionMap;
    
    protected static final String HOME = "home";
    
    public enum pageTypes{
        HOME,
        VIEW,
        CREATE
    };
    
    protected pageTypes pageType = pageTypes.HOME;

    public pageTypes getPageType() {
        return pageType;
    }

    public boolean isUserSignedIn() {
        return sessionMap.get("user") != null;
    }

    public String getGoogleAuthURL() {
        try {
            return GoogleAuthHelper.getAuthorizationUrl("", GoogleAuthHelper.AUTO);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
            return "";
        }
    }

    @Override
    public void setSession(Map<String, Object> session) {
        sessionMap= (SessionMap<String, Object>) session;
    }

    public String execute() throws Exception {
        return SUCCESS;
    }
}

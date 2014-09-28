package in.cleanindia.authentication;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class ProcessLogin extends ActionSupport implements SessionAware{

    private static final long serialVersionUID = -7602483704426106191L;

    protected SessionMap<String, Object> sessionMap;
    protected String returnURL;
    
    @Override
    public void setSession(Map<String, Object> session) {
        sessionMap= (SessionMap<String, Object>) session;
    }
    
    public String getReturnURL() {
        if(returnURL == null){
            return "";
        }
        return returnURL;
    }

    public String execute() throws Exception {
        returnURL = "/";
        return SUCCESS;
    }
    

}

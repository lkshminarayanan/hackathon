package in.cleanindia.listener;

import javax.servlet.ServletContextEvent;  
import javax.servlet.ServletContextListener;  
   
import ognl.OgnlRuntime;  
   
public class Struts2GAEListener implements ServletContextListener {  
    public void contextInitialized(ServletContextEvent servletContextEvent) {  
        OgnlRuntime.setSecurityManager(null);
        //System.setSecurityManager(null);
    }  
   
    public void contextDestroyed(ServletContextEvent servletContextEvent) {  
    }  
}  
package net.ion.system;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@WebListener
public class SessionListener implements HttpSessionListener {

    private static int totalActiveSessions;
    private final Log log = LogFactory.getLog(this.getClass());  

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        totalActiveSessions++;
        log.warn("sessionCreated - add one session into counter");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        totalActiveSessions--;
        log.debug("sessionDestroyed - deleted one session from counter");
    }
    
}
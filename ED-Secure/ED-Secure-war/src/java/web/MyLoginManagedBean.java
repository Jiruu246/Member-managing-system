package web;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Named(value = "myLoginManagedBean")
@SessionScoped
public class MyLoginManagedBean implements Serializable {

    private static final String LOGOUT = "logout";

    /**
     * Creates a new instance of MyLoginManagedBean
     */
    public MyLoginManagedBean() {
    }

    public String logoutResult() {
        // terminate the session by invalidating the session context
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        try {
            request.logout();
            if (request.isUserInRole("EPLAY-ADMIN")) {
                System.out.println("Yes, user is in EPLAY-ADMIN role");
            }
            else if (request.isUserInRole("EPLAY-USER")) {
                System.out.println("Yes, user is in EPLAY-USER role");
            }
        } catch (Exception ex) {
            // do nothing
            System.out.println("log out ...");
        }
        // terminate the session by invalidating the session context
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.invalidate();
        // terminate the user's login credentials
        return LOGOUT;
    }
    
}

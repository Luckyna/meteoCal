package bakingBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import EJB.interfaces.LoginManager;
import model.UserModel;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Umberto
 */
@Named(value = "login")
@SessionScoped
public class LoginBacking implements Serializable {

    /**
     * Creates a new instance of Login
     */
    public LoginBacking() {
    }

    private static final long serialVersionUID = 7965455427888195913L;

    @Inject
    private CredentialsBacking credentials;

    @Inject
    private LoginManager userManager;

    private UserModel currentUser;

    public void login() throws Exception {

        UserModel user = userManager.findUser(credentials);

        if (user != null) {

            this.currentUser = user;
        }
    }

    public void logout() {

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Goodbye, " + currentUser.getName()));

        currentUser = null;

    }

    public boolean isLoggedIn() {
        return currentUser != null;

    }

    @Produces
//    @LoggedIn
    public UserModel getCurrentUser() {

        return currentUser;

    }

}

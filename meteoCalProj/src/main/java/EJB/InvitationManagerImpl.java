package EJB;

import EJB.interfaces.InvitationManager;
import java.util.List;
import model.Event;
import model.User;

public class InvitationManagerImpl implements InvitationManager {

    @Override
    public boolean createInvitations(List<User> userToInvite, Event event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean createInvitation(User user,Event event) {
        //TODO
        return false;
    }

}

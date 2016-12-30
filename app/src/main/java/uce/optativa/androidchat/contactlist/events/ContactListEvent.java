package uce.optativa.androidchat.contactlist.events;

import uce.optativa.androidchat.entities.User;

/**
 * Created by Alexis on 29/12/2016.
 */
public class ContactListEvent {

    public final static int onContactAdded=0;
    public final static int onContactChanged=1;
    public final static int onContactRemoved=2;

    private User user;
    private int eventType;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
}

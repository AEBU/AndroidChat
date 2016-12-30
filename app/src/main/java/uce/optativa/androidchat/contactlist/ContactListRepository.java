package uce.optativa.androidchat.contactlist;

/**
 * Created by Alexis on 29/12/2016.
 */

public interface ContactListRepository {
    void signOff();
    String getCurrentUserEmail();
    void removeContact(String email);
    void destroyListener();
    void subscribeToContactListEvents();
    void unsubscribeToContactListEvents();
    void changeConnectionStatus(boolean online);

}

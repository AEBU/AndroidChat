package uce.optativa.androidchat.contactlist;

/**
 * Created by Alexis on 29/12/2016.
 */

public interface ContactListInteractor {
    void subscribe();
    void unsubscribe();
    void destroyListener();
    void removeContact(String email);


}

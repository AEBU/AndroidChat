package uce.optativa.androidchat.addcontact;

import uce.optativa.androidchat.addcontact.events.AddContactEvent;

/**
 * Created by Alexis on 30/12/2016.
 */


public interface AddContactPresenter {

    void onShow();
    void onDestroy();

    void addContact(String email);
    void onEventMainThread(AddContactEvent event);
}

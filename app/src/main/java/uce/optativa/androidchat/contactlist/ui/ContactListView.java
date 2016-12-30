package uce.optativa.androidchat.contactlist.ui;

import uce.optativa.androidchat.entities.User;

/**
 * Created by Alexis on 29/12/2016.
 */

public interface ContactListView {
    void onContactAdded(User user);
    void  onContactChanged(User user);
    void onContactRemoved(User user);
}

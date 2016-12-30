package uce.optativa.androidchat.contactlist.ui.adapters;

import uce.optativa.androidchat.entities.User;

/**
 * Created by Alexis on 29/12/2016.
 */

public interface OnItemClickListener {
    void onItemClick(User user);
    void onItemLongClick(User user);

}

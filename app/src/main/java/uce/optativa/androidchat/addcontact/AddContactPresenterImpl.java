package uce.optativa.androidchat.addcontact;

import uce.optativa.androidchat.addcontact.AddContactPresenter;
import uce.optativa.androidchat.addcontact.events.AddContactEvent;
import uce.optativa.androidchat.addcontact.ui.AddContactView;

/**
 * Created by Alexis on 30/12/2016.
 */
public class AddContactPresenterImpl implements AddContactPresenter {
    private AddContactView view;
    public AddContactPresenterImpl(AddContactView view ) {
        this.view = view;
    }

    @Override
    public void onShow() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void addContact(String email) {

    }

    @Override
    public void onEventMainThread(AddContactEvent event) {

    }
}

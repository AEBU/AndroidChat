package uce.optativa.androidchat.addcontact.ui;

/**
 * Created by Alexis on 30/12/2016.
 */

public interface AddContactView{
    void showInput();
    void hideInput();
    void showProgress();
    void hideProgress();

    void contactAdded();
    void contactNotAdded();

}

package uce.optativa.androidchat.login;

/**
 * Created by Alexis on 26/12/2016.
 */

public interface LoginPresenter {
    void onDestroy();
    void checkForAuthenticatedUser();
    void validateLogin(String email,String password);
    void registerNewUser(String email,String password);


}

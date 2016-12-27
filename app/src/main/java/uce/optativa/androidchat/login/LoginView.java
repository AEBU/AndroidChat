package uce.optativa.androidchat.login;

/**
 * Created by Alexis on 26/12/2016.
 */

public interface LoginView  {
    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();

    void handleSignUp();
    void handleSignIn();

    void navigateToMainScreen();
    void loginError(String error);

    void newUserSuccess();
    void newUserError(String error);


}

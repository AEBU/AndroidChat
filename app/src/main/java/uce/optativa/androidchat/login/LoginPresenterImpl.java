package uce.optativa.androidchat.login;

import android.util.Log;

import uce.optativa.androidchat.lib.EventBus;
import uce.optativa.androidchat.lib.GreenRobotEventBus;
import uce.optativa.androidchat.login.events.LoginEvent;

import static uce.optativa.androidchat.login.events.LoginEvent.onFailedToRecoverSession;

/**
 * Created by Alexis on 27/12/2016.
 */

public class LoginPresenterImpl implements LoginPresenter {
    private EventBus eventBus;
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView){
        this.loginView=loginView;
        this.loginInteractor = new LoginInteractorImpl();
        this.eventBus= GreenRobotEventBus.getInstance();
    }


    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {

        loginView=null;
        eventBus.unregister(this);
    }

    @Override
    public void checkForAuthenticatedUser() {
        if  (loginView !=null){
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.checkSession();
    }

    @Override
    public void validateLogin(String email, String password) {
        if (loginView !=null){
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.doSignIn(email,password);
    }

    @Override
    public void registerNewUser(String email, String password) {
        if (loginView !=null){
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.doSignUp(email,password);
    }

    @Override
    public void onEventMainThread(LoginEvent event) {
        switch (event.getEventType()){
            case LoginEvent.onSignInSuccess:
                onSignInSuccess();
                break;
            case LoginEvent.onSignUpError:
                onSignUpError(event.getErrorMessage());
                break;
            case LoginEvent.onSignInError:
                onSignInError(event.getErrorMessage());
                break;
            case LoginEvent.onSignUpSuccess:
                onSignUpSuccess();
                break;
            case onFailedToRecoverSession:
                onFailedToRecoverSession();
                break;

        }
    }

    private void onFailedToRecoverSession() {
        if (loginView !=null){
            loginView.hideProgress();
            loginView.enableInputs();

        }
        Log.e("LoginPresenterImpl","onFailedToRecoverSession");
    }

    private void onSignInSuccess(){
        if (loginView !=null){
            loginView.navigateToMainScreen();
        }
    }

    private void onSignUpSuccess(){
        if (loginView !=null){
            loginView.newUserSuccess();
        }
    }
    private void onSignInError(String error){
        if (loginView !=null){
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.loginError(error);
        }
    }

    private void onSignUpError(String error){
        if (loginView !=null){
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.newUserError(error);
        }
    }
}

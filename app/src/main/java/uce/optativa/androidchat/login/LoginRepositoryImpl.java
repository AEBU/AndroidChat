package uce.optativa.androidchat.login;

import android.util.Log;

import uce.optativa.androidchat.domain.FirebaseHelper;
import uce.optativa.androidchat.lib.EventBus;
import uce.optativa.androidchat.lib.GreenRobotEventBus;
import uce.optativa.androidchat.login.events.LoginEvent;

/**
 * Created by Alexis on 27/12/2016.
 */

public class LoginRepositoryImpl implements LoginRepository {
    private FirebaseHelper helper;

    public LoginRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
    }

    @Override
    public void signUp(String email, String password) {

        //Log.e("LoginRepositoryImpl","signup");
        postEvent(LoginEvent.onSignUpSuccess);
    }

    @Override
    public void signIn(String email, String password) {
       // Log.e("LoginRepositoryImpl","signin");
        postEvent(LoginEvent.onSignInSuccess);
    }

    @Override
    public void checkSession() {
        //Log.e("LoginRepositoryImpl","check session");
        postEvent(LoginEvent.onFailedToRecoverSession);
    }

    private void postEvent(int type,String errorMessage){
        LoginEvent loginEvent=new LoginEvent();
        loginEvent.setEventType(type);
        if (errorMessage != null){
            loginEvent.setErrorMessage(errorMessage);
        }

        EventBus    eventBus= GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);
    }

    private void postEvent(int type){
        postEvent(type,null);
    }
}

package uce.optativa.androidchat.login;

import android.support.annotation.NonNull;
import android.util.Log;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Map;
import java.util.concurrent.Executor;

import uce.optativa.androidchat.domain.FirebaseHelper;
import uce.optativa.androidchat.entities.User;
import uce.optativa.androidchat.lib.EventBus;
import uce.optativa.androidchat.lib.GreenRobotEventBus;
import uce.optativa.androidchat.login.events.LoginEvent;

/**
 * Created by Alexis on 27/12/2016.
 */

public class LoginRepositoryImpl implements LoginRepository {
    private FirebaseHelper helper;
    private Firebase dataReference;
    private Firebase myUserReference;
    private FirebaseAuth firebaseAuth;




    public LoginRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
        this.dataReference=helper.getDataReference();
        this.myUserReference=helper.getMyUserReference();
        this.firebaseAuth=FirebaseAuth.getInstance();
    }

    @Override
    public void signUp(final String email, final String password) {
        dataReference.createUser(email,password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> stringObjectMap) {
                postEvent(LoginEvent.onSignUpSuccess);
                signIn(email,password);

            }
            @Override
            public void onError(FirebaseError firebaseError) {
                postEvent(LoginEvent.onSignUpSuccess,firebaseError.getMessage());
            }
        });
        //Log.e("LoginRepositoryImpl","signup");
    }

    @Override
    public void signIn(String email, String password) {
       // Log.e("LoginRepositoryImpl","signin");
       // postEvent(LoginEvent.onSignInSuccess);

        dataReference.authWithPassword(email,password,new Firebase.AuthResultHandler(){

            @Override
            public void onAuthenticated(AuthData authData) {
                initSignIn();

            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                postEvent(LoginEvent.onSignInError,firebaseError.getMessage());
            }
        });



        }





    private void initSignIn(){
        myUserReference=helper.getDataReference();
        myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User currentUser = dataSnapshot.getValue(User.class);

                if(currentUser == null){
                    String email=helper.getAuthUserEmail();
                    if (email!=null){
                        currentUser=new User();
                        myUserReference.setValue(currentUser);
                    }
                }
                helper.changeUserConnectionStatus(User.ONLINE);
                postEvent(LoginEvent.onSignInSuccess);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


    @Override
    public void checkSession() {
        //Log.e("LoginRepositoryImpl","check session");
        if (dataReference.getAuth()!= null){
            initSignIn();
        }else{
            postEvent(LoginEvent.onFailedToRecoverSession);
        }

    }

    private  void registerNewUser(){
        String email= helper.getAuthUserEmail();
        if (email!= null){
            User currentUser= new User();
            currentUser.setEmail(email);
            myUserReference.setValue(currentUser);
        }
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

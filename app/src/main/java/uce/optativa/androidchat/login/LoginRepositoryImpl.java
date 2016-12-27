package uce.optativa.androidchat.login;

import android.util.Log;

import uce.optativa.androidchat.domain.FirebaseHelper;

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
        Log.e("LoginRepositoryImpl","signup");
    }

    @Override
    public void signIn(String email, String password) {
        Log.e("LoginRepositoryImpl","signin");
    }

    @Override
    public void checkSession() {
        Log.e("LoginRepositoryImpl","check session");
    }
}

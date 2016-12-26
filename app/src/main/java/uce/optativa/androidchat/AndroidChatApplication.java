package uce.optativa.androidchat;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Alexis on 26/12/2016.
 */

public class AndroidChatApplication  extends Application{
    @Override
    public void onCreate(){
        super.onCreate();
        setupFirebase();
    }

    private void setupFirebase() {
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }


}

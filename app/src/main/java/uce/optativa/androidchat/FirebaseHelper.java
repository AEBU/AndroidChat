package uce.optativa.androidchat;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

import java.util.Map;
import java.util.Objects;

/**
 * Created by Alexis on 26/12/2016.
 */

public class FirebaseHelper {
    private Firebase dateReference;
    private final static String SEPARATOR="___";
    private final static String CHATS_PATH ="chats";
    private final static String USERS_PATH ="users";
    private final static String CONTACTS_PATH ="contacts";
    private final static String FIREBASE_URL ="https://android-chat-computronik.firebaseio.com";


    /*
    * tiene una instancia de un objeto definida en este campo
    * */
    private static class SingletonHolder{
        private static final FirebaseHelper INSTANCE = new FirebaseHelper();
    }
    /*
    * Inicializamos el singletoon que devuelve un FirebaseHelper
    * y obtenemos instance de singletonHolder para poder hacer FirebaseHelper.getInstance
    *
    * */
    public  static FirebaseHelper getInstance(){
        return SingletonHolder.INSTANCE;
    }
    /*
      * INicializar el objeto dateReference
      *
      * */
    public  FirebaseHelper(){
        this.dateReference= new Firebase(FIREBASE_URL);

    }
    /*
    * Método get para obtener un dateReferences
    * */
    public Firebase getDataReference(){
        return dateReference;
    }

    /*
    * queremos que nos devuelva el mail de la persona ya autenticada
    * */
    public String getAuthUserEmail() {
        AuthData authData = dateReference.getAuth();
        String email= null;
        if (authData != null ){
            Map<String,Object> providerData = authData.getProviderData();
            email = providerData.get("email").toString();
        }
        return email;
    }

}

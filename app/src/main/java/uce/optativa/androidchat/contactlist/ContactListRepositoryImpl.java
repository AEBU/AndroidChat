package uce.optativa.androidchat.contactlist;

import com.firebase.client.FirebaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;


import uce.optativa.androidchat.contactlist.events.ContactListEvent;
import uce.optativa.androidchat.domain.FirebaseHelper;
import uce.optativa.androidchat.entities.User;
import uce.optativa.androidchat.lib.EventBus;
import uce.optativa.androidchat.lib.GreenRobotEventBus;

import static android.R.attr.type;

/**
 * Created by Alexis on 29/12/2016.
 */
public class ContactListRepositoryImpl implements ContactListRepository {
    private EventBus eventBus;
    private FirebaseHelper helper;
    private ChildEventListener contactEventListener;

    public ContactListRepositoryImpl() {
        this.helper=FirebaseHelper.getInstance();
    }

    @Override
    public void signOff() {
        helper.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return helper.getAuthUserEmail();
    }

    @Override
    public void removeContact(String email) {
        String currentUserEmail= helper.getAuthUserEmail();
        helper.getOneContactReference(currentUserEmail,email).removeValue();
        helper.getOneContactReference(email,currentUserEmail).removeValue();

    }

    @Override
    public void destroyListener() {
        contactEventListener=null;
    }

    @Override
    public void subscribeToContactListEvents() {

        if (contactEventListener == null){
        contactEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                handleContact(dataSnapshot,ContactListEvent.onContactAdded);




            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                handleContact(dataSnapshot,ContactListEvent.onContactChanged);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                handleContact(dataSnapshot,ContactListEvent.onContactRemoved);

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        };
        }
        helper.getMyContactsReference().addChildEventListener(contactEventListener);    }

    private void handleContact(DataSnapshot dataSnapshot, int type) {
        String email= dataSnapshot.getKey();
        email = email.replace("_",".");
        boolean online= ((Boolean) dataSnapshot.getValue()).booleanValue();
        User user = new User();
        user.setEmail(email);
        user.setOnline(online);
        post(type,user);
    }

    private void post(int type, User user) {
        ContactListEvent event= new ContactListEvent();
        event.setEventType(type);
        event.setUser(user);
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(event);

    }


    @Override
    public void unsubscribeToContactListEvents() {
        if (contactEventListener != null){
            helper.getMyContactsReference().removeEventListener(contactEventListener);
        }
    }

    @Override
    public void changeConnectionStatus(boolean online) {

    }
}

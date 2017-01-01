package uce.optativa.androidchat.chat;

import com.firebase.client.Firebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import uce.optativa.androidchat.chat.events.ChatEvent;
import uce.optativa.androidchat.domain.FirebaseHelper;
import uce.optativa.androidchat.entities.ChatMessage;
import uce.optativa.androidchat.lib.EventBus;
import uce.optativa.androidchat.lib.GreenRobotEventBus;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by Alexis on 31/12/2016.
 */
public class ChatRepositoryImpl implements ChatRepository {

    private String recipient;
    private EventBus eventBus;
    private FirebaseHelper helper;
    private ChildEventListener chatEventListener;


    public ChatRepositoryImpl() {
        this.helper=FirebaseHelper.getInstance();
        this.eventBus= GreenRobotEventBus.getInstance();
    }

    @Override
    public void sendMessage(String msg) {
        ChatMessage chatMessage= new ChatMessage();
        chatMessage.setSender(helper.getAuthUserEmail());
        chatMessage.setMsg(msg);
        DatabaseReference chatsReference= helper.getChatsReference(recipient);
        chatsReference.push().setValue(chatMessage);
    }

    @Override
    public void setRecipient(String recipient) {
        this.recipient= recipient;
    }

    @Override
    public void subscribe() {
        if (chatEventListener== null){
            chatEventListener= new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    ChatMessage chatMessage= dataSnapshot.getValue(ChatMessage.class);
                    String msgSenger=chatMessage.getSender();

                    chatMessage.setSentByMe(msgSenger.equals(helper.getAuthUserEmail()));
                    ChatEvent chatEvent= new ChatEvent();
                    chatEvent.setMessage(chatMessage);
                    eventBus.post(chatEvent);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
        }
        helper.getChatsReference(recipient).addChildEventListener(chatEventListener );

    }

    @Override
    public void unsubscribe() {
        if (chatEventListener != null){
            helper.getChatsReference(recipient).removeEventListener(chatEventListener);
        }
    }

    @Override
    public void destroyListener() {
        chatEventListener= null;
    }

    @Override
    public void changeConnectionStatus(boolean online) {
        helper.changeUserConnectionStatus(online);
    }
}

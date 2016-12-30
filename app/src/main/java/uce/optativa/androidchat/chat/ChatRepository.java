package uce.optativa.androidchat.chat;

/**
 * Created by Alexis on 30/12/2016.
 */

public interface ChatRepository {
    void sendMessage(String msg);
    void setRecipient(String recipient);
    void subscribe();
    void unsubscribe();
    void destroyListener();
    void changeConnectionStatus(boolean online);
}

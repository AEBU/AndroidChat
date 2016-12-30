package uce.optativa.androidchat.chat;

import uce.optativa.androidchat.chat.events.ChatEvent;

/**
 * Created by Alexis on 30/12/2016.
 */

public interface ChatPresenter {
    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();


    void setChatRecipient(String recipient);
    void sendMessage(String msg);
    void onEventMainThread(ChatEvent event);
}

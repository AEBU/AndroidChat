package uce.optativa.androidchat.chat.ui;

import uce.optativa.androidchat.entities.ChatMessage;

/**
 * Created by Alexis on 30/12/2016.
 */

public interface ChatView {
    void onMessageReceived(ChatMessage msg);

}

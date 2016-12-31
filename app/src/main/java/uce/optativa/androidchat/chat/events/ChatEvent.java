package uce.optativa.androidchat.chat.events;

import uce.optativa.androidchat.entities.ChatMessage;

/**
 * Created by Alexis on 30/12/2016.
 */
public class ChatEvent {
    ChatMessage message;

    public ChatMessage getMessage() {
        return message;
    }

    public void setMessage(ChatMessage message) {
        this.message = message;
    }
}

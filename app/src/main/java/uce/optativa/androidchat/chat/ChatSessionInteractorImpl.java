package uce.optativa.androidchat.chat;

/**
 * Created by Alexis on 31/12/2016.
 */
public class ChatSessionInteractorImpl implements ChatSessionInteractor {
    ChatRepository chatRepository;

    public ChatSessionInteractorImpl() {

        this.chatRepository = new ChatRepositoryImpl();
    }
    @Override
    public void changeConnectionStatus(boolean online) {

    }
}

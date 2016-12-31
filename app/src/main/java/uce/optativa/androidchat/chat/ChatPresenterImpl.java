package uce.optativa.androidchat.chat;

import org.greenrobot.eventbus.Subscribe;

import uce.optativa.androidchat.chat.events.ChatEvent;
import uce.optativa.androidchat.chat.ui.ChatView;
import uce.optativa.androidchat.entities.User;
import uce.optativa.androidchat.lib.EventBus;
import uce.optativa.androidchat.lib.GreenRobotEventBus;

/**
 * Created by Alexis on 30/12/2016.
 */
public class ChatPresenterImpl implements ChatPresenter {

    private EventBus eventBus;
    private ChatView view;
    private ChatInteractor chatInteractor;
    private ChatSessionInteractor sessionInteractor;


    public ChatPresenterImpl(ChatView view){
        this.view= view;
        this.eventBus= GreenRobotEventBus.getInstance();
        this.chatInteractor= new ChatInteractorImpl();
        this.sessionInteractor= new ChatSessionInteractorImpl();
    }

    @Override
    public void onPause() {
        chatInteractor.unsubscribe();
        sessionInteractor.changeConnectionStatus(User.OFFLINE);
    }

    @Override
    public void onResume() {

        chatInteractor.subscribe();
        sessionInteractor.changeConnectionStatus(User.ONLINE);

    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        chatInteractor.destroyListener();
        view=null;

    }

    @Override
    public void setChatRecipient(String recipient) {
        chatInteractor.setRecipient(recipient);
    }

    @Override
    public void sendMessage(String msg) {
        chatInteractor.setRecipient(msg);

    }

    @Override
    @Subscribe
    public void onEventMainThread(ChatEvent event) {
if (view!= null ){
    view.onMessageReceived(event.getMessage());
}
    }
}

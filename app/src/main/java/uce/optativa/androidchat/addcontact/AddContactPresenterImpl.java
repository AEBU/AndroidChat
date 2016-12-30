package uce.optativa.androidchat.addcontact;

import org.greenrobot.eventbus.Subscribe;

import uce.optativa.androidchat.addcontact.AddContactPresenter;
import uce.optativa.androidchat.addcontact.events.AddContactEvent;
import uce.optativa.androidchat.addcontact.ui.AddContactView;
import uce.optativa.androidchat.lib.EventBus;
import uce.optativa.androidchat.lib.GreenRobotEventBus;

/**
 * Created by Alexis on 30/12/2016.
 */
public class AddContactPresenterImpl implements AddContactPresenter {

    private EventBus eventBus;
    private AddContactView view;
    private AddContactInteractor interactor;
    public AddContactPresenterImpl(AddContactView view ) {
        this.view = view;
        this.eventBus= GreenRobotEventBus.getInstance();
        this.interactor= new AddContactInteractorImpl();
    }

    @Override
    public void onShow() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view= null;
        eventBus.unregister(this);

    }

    @Override
    public void addContact(String email) {
        if (view != null){
            view.hideInput();
            view.showProgress();

        }
        interactor.execute(email);
    }

    @Override
    @Subscribe
    public void onEventMainThread(AddContactEvent event) {
        if (view != null) {
            view.hideProgress();
            view.showInput();

            if (event.isError()) {
                view.contactNotAdded();
            } else {
                view.contactAdded();
            }
        }
    }
}

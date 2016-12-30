package uce.optativa.androidchat.addcontact.events;

/**
 * Created by Alexis on 30/12/2016.
 */
public class AddContactEvent {
    boolean error= false;

    public boolean isError() {
        return error;
    }

    public void setError(boolean errror) {
        this.error = errror;
    }
}

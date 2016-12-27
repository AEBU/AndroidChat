package uce.optativa.androidchat.lib;

/**
 * Created by Alexis on 27/12/2016.
 */

public interface EventBus {

    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}

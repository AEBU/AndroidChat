package uce.optativa.androidchat.chat;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import uce.optativa.androidchat.entities.ChatMessage;

/**
 * Created by Alexis on 30/12/2016.
 */
public class ChatAdapter  extends RecyclerView.Adapter<ChatAdapter.ViewHolder>{
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void add(ChatMessage msg) {
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}

package uce.optativa.androidchat.contactlist.ui.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import uce.optativa.androidchat.R;
import uce.optativa.androidchat.entities.User;

/**
 * Created by Alexis on 29/12/2016.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {


    private List<User> contactList;
    private ImageLoading imageLoader;
    private OnItemClickListener clickListener;

    public ContactListAdapter(List<User> contactList, ImageLoading imageLoading, OnItemClickListener clickListener) {
        this.contactList = contactList;
        this.imageLoader = imageLoading;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_contact, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = contactList.get(position);
        holder.setClickListener(user, clickListener);

        String email = user.getEmail();
        boolean online = user.isOnline();
        String status = online ? "online" : "offline";
        int color = online ? Color.GREEN : Color.RED;

        holder.txtUser.setText(email);
        holder.txtStatus.setText(status);
        holder.txtStatus.setTextColor(color);

        imageLoader.load(holder.imgAvatar, "");
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imgAvatar)
        CircleImageView imgAvatar;
        @Bind(R.id.txtUser)
        TextView txtUser;
        @Bind(R.id.txtStatus)
        TextView txtStatus;

        private View view;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            this.view= view;
        }
        public void setClickListener(final User user,
                                     final OnItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(user);
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onItemLongClick(user);
                    return false;
                }
            });
        }
    }

}

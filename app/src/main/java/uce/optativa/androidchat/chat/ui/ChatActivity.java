package uce.optativa.androidchat.chat.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import uce.optativa.androidchat.R;
import uce.optativa.androidchat.chat.ui.adapters.ChatAdapter;
import uce.optativa.androidchat.chat.ChatPresenter;
import uce.optativa.androidchat.chat.ChatPresenterImpl;
import uce.optativa.androidchat.domain.AvatarHelper;
import uce.optativa.androidchat.entities.ChatMessage;
import uce.optativa.androidchat.lib.GlideImageLoader;
import uce.optativa.androidchat.lib.ImageLoader;

public class ChatActivity extends AppCompatActivity implements ChatView {

    @Bind(R.id.imgAvatar)
    CircleImageView imgAvatar;
    @Bind(R.id.txtUser)
    TextView txtUser;
    @Bind(R.id.txtStatus)
    TextView txtStatus;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.messageRecyclerView)
    RecyclerView messageRecyclerView;
    @Bind(R.id.editTxtMessage)
    EditText editTxtMessage;
    @Bind(R.id.btnSendMessage)
    ImageButton btnSendMessage;


    public final static String EMAIL_KEY = "email";
    public final static String ONLINE_KEY = "online";


    private ChatPresenter chatPresenter;
    private ChatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        setupAdapter();
        setupRecyclerView();

        chatPresenter= new ChatPresenterImpl(this);
        chatPresenter.onCreate();
        setupToolbar(getIntent());
    }

    private void setupAdapter() {
        ChatMessage msg1= new ChatMessage();
        ChatMessage msg2= new ChatMessage();

        msg1.setMsg("hola!");
        msg2.setMsg("Como te va!");
        msg1.setSentByMe(true);
        msg2.setSentByMe(false);
        //new ArrayList<ChatMessage>()
        adapter= new ChatAdapter(this, Arrays.asList(new ChatMessage[]{msg1,msg2}));
    }

    private void setupRecyclerView() {
        messageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageRecyclerView.setAdapter(adapter);
    }
    private void setupToolbar(Intent i ) {
        String recipient=i.getStringExtra(EMAIL_KEY);
        chatPresenter.setChatRecipient(recipient);

        boolean online = i.getBooleanExtra(ONLINE_KEY,false);
        String status = online ? "online" : "offline";
        int color = online ? Color.GREEN : Color.RED;

        txtUser.setText(recipient);
        txtStatus.setText(status);
        txtStatus.setTextColor(color);


        ImageLoader imageLoader=new  GlideImageLoader(getApplicationContext());

        imageLoader.load(imgAvatar, AvatarHelper.getAvatarUrl(recipient));

    }

    @Override
    protected void onDestroy() {
        chatPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        chatPresenter.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        chatPresenter.onResume();
        super.onResume();
    }

    @Override
    public void onMessageReceived(ChatMessage msg) {
        adapter.add(msg);
        messageRecyclerView.scrollToPosition(adapter.getItemCount() - 1);
    }
}

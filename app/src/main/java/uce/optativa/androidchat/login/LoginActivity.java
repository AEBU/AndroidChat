package uce.optativa.androidchat.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uce.optativa.androidchat.R;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.btnSignin)
    Button btnSignIn;
    @Bind(R.id.btnSignup)    Button btnSignUp;
    @Bind(R.id.editTxtEmail)    EditText inputEmail;
    @Bind(R.id.editTxtPassword)    EditText inputPassword;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.layoutMainContainer)
    ProgressBar container;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.btnSignin)
    public void handleSignin(){
        Log.e("AndroidChat",inputEmail.getText().toString());
    }

    @OnClick(R.id.btnSignup)
    public void handleSignup(){
        Log.e("AndroidChat",inputEmail.getText().toString());
    }
}

package uce.optativa.androidchat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
/*
* Uso de Butterknife para inicializar mi editText Email
* Metodo 1
*  */
    @Bind(R.id.editTxtEmail)
    EditText inputEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }
/*
* Uso de Butterknife para inicializar mi editText Email
* Metodo 2
*Vamos a mostrar lo que uingreso el usuario en el campo de email
*  */

    @OnClick(R.id.btnSignin)
    public void handleSignin(){
        Log.e("AndroidChat",inputEmail.getText().toString());
    }
}

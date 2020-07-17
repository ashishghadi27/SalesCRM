package com.asg.root.salescrm.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.asg.root.salescrm.R;
import com.asg.root.salescrm.Views.AuthView;
import com.asg.root.salescrm.presenter.AuthPresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Authentication extends AppCompatActivity implements AuthView {

    private EditText email, password;
    private RelativeLayout login;
    private AuthPresenter authPresenter;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        authPresenter = new AuthPresenter(this);
        dialog = new Dialog(Objects.requireNonNull(this));
        loadDialog("Verifying User...");
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("loggedIn", false);
        if(isLoggedIn){
            toMain();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailstr = email.getText() + "";
                String pass = password.getText() + "";
                if(!TextUtils.isEmpty(emailstr) && !TextUtils.isEmpty(pass))
                    login(emailstr, pass);
                else showDialog("Email or Password Field is Empty", "Dismiss");
            }
        });
    }

    private void login(String email, String pass){
        authPresenter.checkUser(email, pass);
    }

    private void showDialog(String msg1, String msg2){
        final Dialog dialog = new Dialog(Objects.requireNonNull(Authentication.this));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_dialogue);

        TextView message1 = (TextView) dialog.findViewById(R.id.alert_message);
        TextView message2 = (TextView) dialog.findViewById(R.id.alert_message2);
        message1.setText(msg1);
        message2.setText(msg2);

        message2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void onAuthSuccess(JSONObject jsonObject) {
        String check = "";
        try {
            check = jsonObject.getString("message");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(check.equals("Valid User")){
            SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("loggedIn", true);
            editor.apply();
            toMain();
        }
        else showDialog("Invalid email or password", "Try Again");
    }

    @Override
    public void onAuthFailure(String err) {

    }

    @Override
    public void showProgress() {
        dialog.show();
    }

    @Override
    public void hideProgress() {
        dialog.dismiss();
    }

    private void toMain(){
        Intent intent = new Intent(Authentication.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void loadDialog(String msg1){
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.loading);
        dialog.setCancelable(false);
        TextView message1 = (TextView) dialog.findViewById(R.id.alert_message);
        message1.setText(msg1);
    }
}
package com.asg.root.salescrm.Fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.asg.root.salescrm.R;
import com.asg.root.salescrm.Utils.AppUtils;
import com.asg.root.salescrm.Views.DataView;
import com.asg.root.salescrm.presenter.VitalFunctionsPresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Bouncer extends BaseFragment implements DataView {

    private EditText edtEmail;
    private EditText edtEnable;
    private EditText edtHost;
    private EditText edtPort;
    private EditText edtUsername;
    private EditText edtPassword;
    private EditText edtMailBox;
    private EditText edtMailType;
    private EditText edtDeleteLevel;
    private EditText edtSpamHeader;
    private EditText edtNotifyOwner;
    private Button addBouncer;
    private Dialog dialog;
    private VitalFunctionsPresenter vitalFunctionsPresenter;

    public Bouncer() {
        // Required empty public constructor
    }

    public static Bouncer newInstance(String param1, String param2) {
        return new Bouncer();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vitalFunctionsPresenter = new VitalFunctionsPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bouncer, container, false);
        edtEmail = view.findViewById(R.id.email);
        edtEnable = view.findViewById(R.id.enabled);
        edtHost = view.findViewById(R.id.host);
        edtPort = view.findViewById(R.id.port);
        edtUsername = view.findViewById(R.id.username);
        edtPassword = view.findViewById(R.id.password);
        edtMailBox = view.findViewById(R.id.mailbox);
        edtMailType = view.findViewById(R.id.mailType);
        edtDeleteLevel  = view.findViewById(R.id.deleteLevel);
        edtSpamHeader = view.findViewById(R.id.spamHeader);
        edtNotifyOwner = view.findViewById(R.id.notifyOwner);
        addBouncer = view.findViewById(R.id.addBouncer);
        dialog = new Dialog(Objects.requireNonNull(getContext()));
        loadDialog("Adding Bouncer");
        addBouncer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject = createJSONObject();
                if(jsonObject != null)
                    if(AppUtils.isOnline(Objects.requireNonNull(getContext())))
                        vitalFunctionsPresenter.addBouncer(jsonObject);
                    else showDialog("Please Check Your Internet", "Dismiss");
                else showDialog("Some Fields are empty", "Dismiss");
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDataFetched(JSONObject jsonObject) {
        showDialog("Data Inserted Successfully","Dismiss");
    }

    @Override
    public void onDataFetchedError(String err) {
        showDialog("Some Error Occurred","Dismiss");
    }

    @Override
    public void showProgress() {
        dialog.show();
    }

    @Override
    public void hideProgress() {
        dialog.dismiss();
    }


    private JSONObject createJSONObject(){
        String emailStr, enableStr, hostStr, portStr,
                usernameStr, passwordStr, mailboxStr,
                mailtypeStr, deletelevelStr,spamheaderStr, notifyownerSourceStr;
        JSONObject jsonObject = new JSONObject();
        emailStr = edtEmail.getText() + "";
        enableStr = edtEnable.getText() + "";
        hostStr = edtHost.getText() + "";
        portStr = edtPort.getText() + "";
        usernameStr = edtUsername.getText() + "";
        passwordStr = edtPassword.getText() + "";
        mailboxStr = edtMailBox.getText() + "";
        mailtypeStr = edtMailType.getText() + "";
        deletelevelStr = edtDeleteLevel.getText() + "";
       spamheaderStr = edtSpamHeader.getText() + "";
        notifyownerSourceStr = edtNotifyOwner.getText() + "";
        boolean allPresent = !TextUtils.isEmpty(emailStr) &&
                !TextUtils.isEmpty(enableStr) &&
                !TextUtils.isEmpty(hostStr) &&
                !TextUtils.isEmpty(portStr) &&
                !TextUtils.isEmpty(usernameStr) &&
                !TextUtils.isEmpty(passwordStr) &&
                !TextUtils.isEmpty(mailboxStr) &&
                !TextUtils.isEmpty(mailtypeStr) &&
                !TextUtils.isEmpty(deletelevelStr) &&
                !TextUtils.isEmpty(spamheaderStr) &&
                !TextUtils.isEmpty(notifyownerSourceStr);

        if(allPresent){
            try {
                Log.v("IN", "HERE");
                jsonObject.put("EmailAddy", emailStr);
                jsonObject.put("Enabled", enableStr);
                jsonObject.put("host", hostStr);
                jsonObject.put("port", portStr);
                jsonObject.put("username", usernameStr);
                jsonObject.put("password", passwordStr);
                jsonObject.put("mailbox", mailboxStr);
                jsonObject.put("mailtype", mailtypeStr);
                jsonObject.put("DeleteLevel", deletelevelStr);
                jsonObject.put("SpamHeader",spamheaderStr);
                jsonObject.put("NotifyOwner", notifyownerSourceStr);
                return jsonObject;

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    private void showDialog(String msg1, String msg2){
        final Dialog dialog = new Dialog(Objects.requireNonNull(getContext()));
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

    private void loadDialog(String msg1){
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.loading);
        dialog.setCancelable(false);
        TextView message1 = (TextView) dialog.findViewById(R.id.alert_message);
        message1.setText(msg1);
    }
}
package com.asg.root.salescrm.Fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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

public class Responder extends BaseFragment implements DataView {

    private EditText edtEnable;
    private EditText edtName;
    private EditText edtrespDesc;
    private EditText edtOwnerEmail;
    private EditText edtOwnerName;
    private EditText edtreplyEmail;
    private EditText edtMsgList;
    private EditText edtOptMethod;
    private EditText edtOptInRedir;
    private EditText edtOptOutRedir;
    private EditText edtOptInDisp;
    private EditText edtOptOutDisp;
    private EditText edtNotifyOwner;
    private Button addResponder;
    private Dialog dialog;
    private VitalFunctionsPresenter vitalFunctionsPresenter;

    public Responder() {
        // Required empty public constructor
    }

    public static Responder newInstance(String param1, String param2) {
        return new Responder();
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
        View view = inflater.inflate(R.layout.fragment_responder, container, false);
        edtEnable = view.findViewById(R.id.enabled);
        edtName = view.findViewById(R.id.name);
        edtrespDesc = view.findViewById(R.id.respDesc);
        edtOwnerEmail = view.findViewById(R.id.ownerEmail);
        edtOwnerName = view.findViewById(R.id.ownerName);
        edtreplyEmail  = view.findViewById(R.id.replyToEmail);
        edtMsgList = view.findViewById(R.id.messageList);
        edtOptMethod = view.findViewById(R.id.optMethod);
        edtOptInRedir = view.findViewById(R.id.optInRedir);
        edtOptOutRedir = view.findViewById(R.id.optOutRedir);
        edtOptInDisp = view.findViewById(R.id.optInDisp);
        edtOptOutDisp = view.findViewById(R.id.optOutDisp);
        edtNotifyOwner = view.findViewById(R.id.notifyOwner);
        addResponder = view.findViewById(R.id.addResponder);
        dialog = new Dialog(Objects.requireNonNull(getContext()));
        loadDialog("Adding Responder");
        addResponder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject = createJSONObject();
                if(jsonObject != null)
                    if(AppUtils.isOnline(Objects.requireNonNull(getContext())))
                        vitalFunctionsPresenter.addResponder(jsonObject);
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
        String enableStr, nameStr, respDescStr, ownerEmailStr, ownerNameStr, replyEmailStr,
                msgListStr, optMethodStr, optInRedirStr, optOutRedirStr, optInRedirDisplayStr, optOutRedirDisplayStr, notifyOwnerStr;
        JSONObject jsonObject = new JSONObject();
        enableStr = edtEnable.getText() + "";
        nameStr = edtName.getText() + "";
        respDescStr = edtrespDesc.getText() + "";
        ownerEmailStr = edtOwnerEmail.getText() + "";
        ownerNameStr = edtOwnerName.getText() + "";
        replyEmailStr = edtreplyEmail.getText() + "";
        msgListStr = edtMsgList.getText() + "";
        optMethodStr = edtOptMethod.getText() + "";
        optInRedirStr = edtOptInRedir.getText() + "";
        optOutRedirStr = edtOptOutRedir.getText() + "";
        optInRedirDisplayStr = edtOptInDisp.getText() + "";
        optOutRedirDisplayStr = edtOptOutDisp.getText() + "";
        notifyOwnerStr = edtNotifyOwner.getText() + "";
        boolean allPresent = !TextUtils.isEmpty(enableStr) &&
                !TextUtils.isEmpty(nameStr) &&
                !TextUtils.isEmpty(ownerEmailStr) &&
                !TextUtils.isEmpty(replyEmailStr) &&
                !TextUtils.isEmpty(optMethodStr) &&
                !TextUtils.isEmpty(notifyOwnerStr);

        if(allPresent){
            try {
                Log.v("IN", "HERE");
                jsonObject.put("Enabled", enableStr);
                jsonObject.put("Name", nameStr);
                jsonObject.put("ResponderDesc", respDescStr);
                jsonObject.put("OwnerEmail", ownerEmailStr);
                jsonObject.put("OwnerName", ownerNameStr);
                jsonObject.put("ReplyToEmail", replyEmailStr);
                jsonObject.put("MsgList", msgListStr);
                jsonObject.put("OptMethod", optMethodStr);
                jsonObject.put("OptInRedir", optInRedirStr);
                jsonObject.put("OptOutRedir",optOutRedirStr);
                jsonObject.put("OptInDisplay", optInRedirDisplayStr);
                jsonObject.put("OptOutDisplay",optOutRedirDisplayStr);
                jsonObject.put("NotifyOwnerOnSub", notifyOwnerStr);
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
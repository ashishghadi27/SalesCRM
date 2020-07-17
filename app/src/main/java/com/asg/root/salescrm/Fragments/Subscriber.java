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
import com.asg.root.salescrm.Views.DataView;
import com.asg.root.salescrm.presenter.VitalFunctionsPresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Subscriber extends BaseFragment implements DataView {

    private EditText responderId, sentMsg, email, timeJoined,
            realTimeJoined, canReceiveHTML, lastActivity,
            firstName, lastName, ipAdd, referralSource, uniqueCode, confirmed;

    private Button addSubs;
    private VitalFunctionsPresenter vitalFunctionsPresenter;

    public Subscriber() {
        // Required empty public constructor
    }

    public static Subscriber newInstance(String param1, String param2) {
        return new Subscriber();
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
        return inflater.inflate(R.layout.fragment_subscriber, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        responderId = view.findViewById(R.id.responderId);
        sentMsg = view.findViewById(R.id.sentMsg);
        email = view.findViewById(R.id.email);
        timeJoined = view.findViewById(R.id.timeJoined);
        realTimeJoined = view.findViewById(R.id.realTimeJoined);
        canReceiveHTML = view.findViewById(R.id.canReceiveHTML);
        lastActivity = view.findViewById(R.id.lastActivity);
        firstName = view.findViewById(R.id.firstName);
        lastName = view.findViewById(R.id.lastName);
        ipAdd = view.findViewById(R.id.ipAdd);
        referralSource = view.findViewById(R.id.referralSource);
        uniqueCode = view.findViewById(R.id.uniqueCode);
        confirmed = view.findViewById(R.id.confirmed);
        addSubs = view.findViewById(R.id.addSubscriber);

        addSubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject = createJSONObject();
                if(jsonObject != null)
                    vitalFunctionsPresenter.addSubscriber(jsonObject);
                else showDialog("Some Fields are empty","Dismiss");
            }
        });


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

    }

    @Override
    public void hideProgress() {

    }

    private JSONObject createJSONObject(){
        String responderIdStr, sentMsgStr, emailStr, timeJoinedStr,
                realTimeJoinedStr, canReceiveHTMLStr, lastActivityStr,
                firstNameStr, lastNameStr, ipAddStr, referralSourceStr, uniqueCodeStr, confirmedStr;
        JSONObject jsonObject = new JSONObject();
        responderIdStr = responderId.getText() + "";
        sentMsgStr = sentMsg.getText() + "";
        emailStr = email.getText() + "";
        timeJoinedStr = timeJoined.getText() + "";
        realTimeJoinedStr = realTimeJoined.getText() + "";
        canReceiveHTMLStr = canReceiveHTML.getText() + "";
        lastActivityStr = lastActivity.getText() + "";
        firstNameStr = firstName.getText() + "";
        lastNameStr = lastName.getText() + "";
        ipAddStr = ipAdd.getText() + "";
        referralSourceStr = referralSource.getText() + "";
        uniqueCodeStr = uniqueCode.getText() + "";
        confirmedStr = confirmed.getText() + "";

        boolean allPresent = !TextUtils.isEmpty(responderIdStr) &&
                !TextUtils.isEmpty(sentMsgStr) &&
                !TextUtils.isEmpty(emailStr) &&
                !TextUtils.isEmpty(timeJoinedStr) &&
                !TextUtils.isEmpty(realTimeJoinedStr) &&
                !TextUtils.isEmpty(canReceiveHTMLStr) &&
                !TextUtils.isEmpty(lastActivityStr) &&
                !TextUtils.isEmpty(firstNameStr) &&
                !TextUtils.isEmpty(lastNameStr) &&
                !TextUtils.isEmpty(ipAddStr) &&
                !TextUtils.isEmpty(referralSourceStr) &&
                !TextUtils.isEmpty(uniqueCodeStr) &&
                !TextUtils.isEmpty(confirmedStr);

        if(allPresent){
            try {
                Log.v("IN", "HERE");
                jsonObject.put("ResponderID", responderIdStr);
                jsonObject.put("SentMsgs", sentMsgStr);
                jsonObject.put("EmailAddress", emailStr);
                jsonObject.put("TimeJoined", timeJoinedStr);
                jsonObject.put("Real_TimeJoined", realTimeJoinedStr);
                jsonObject.put("CanReceiveHTML", canReceiveHTMLStr);
                jsonObject.put("LastActivity", lastActivityStr);
                jsonObject.put("FirstName", firstNameStr);
                jsonObject.put("LastName", lastNameStr);
                jsonObject.put("IP_Addy", ipAddStr);
                jsonObject.put("ReferralSource", referralSourceStr);
                jsonObject.put("UniqueCode", uniqueCodeStr);
                jsonObject.put("Confirmed", confirmedStr);
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
}
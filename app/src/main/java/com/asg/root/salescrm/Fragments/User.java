package com.asg.root.salescrm.Fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.asg.root.salescrm.App;
import com.asg.root.salescrm.R;
import com.asg.root.salescrm.Utils.AppUtils;
import com.asg.root.salescrm.Views.DataView;
import com.asg.root.salescrm.presenter.VitalFunctionsPresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class User extends Fragment implements DataView {

    private EditText username, fullname, email, mobile, address, region,
            zone, corporate, password;
    private Button addUser;
    private VitalFunctionsPresenter vitalFunctionsPresenter;
    private Dialog dialog;

    public User() {
        // Required empty public constructor
    }

    public static User newInstance(String param1, String param2) {
        return new User();
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
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        username = view.findViewById(R.id.username);
        fullname = view.findViewById(R.id.fullname);
        email = view.findViewById(R.id.email);
        mobile = view.findViewById(R.id.mobile);
        address = view.findViewById(R.id.address);
        region = view.findViewById(R.id.region);
        zone = view.findViewById(R.id.zone);
        corporate = view.findViewById(R.id.corporate);
        password = view.findViewById(R.id.password);
        addUser = view.findViewById(R.id.addUser);
        dialog = new Dialog(Objects.requireNonNull(getContext()));
        loadDialog("Adding User");

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject = createJSONObject();
                if(jsonObject != null)
                    if(AppUtils.isOnline(Objects.requireNonNull(getContext())))
                        vitalFunctionsPresenter.addUSer(jsonObject);
                    else showDialog("Please Check Your Internet", "Dismiss");
                else showDialog("Some Fields are empty", "Dismiss");
            }
        });

    }

    private JSONObject createJSONObject(){
        String usernameStr, fullnameStr, emailStr, mobileStr, addressStr, regionStr,
                zoneStr, corporateStr, passwordStr;
        usernameStr = username.getText() + "";
        fullnameStr = fullname.getText() + "";
        emailStr = email.getText() + "";
        mobileStr = mobile.getText() + "";
        addressStr = address.getText() + "";
        regionStr = region.getText() + "";
        zoneStr = zone.getText() + "";
        corporateStr = corporate.getText() + "";
        passwordStr = password.getText() + "";

        boolean allPresent = !TextUtils.isEmpty(usernameStr) &&
                !TextUtils.isEmpty(fullnameStr) &&
                !TextUtils.isEmpty(emailStr) &&
                !TextUtils.isEmpty(mobileStr) &&
                !TextUtils.isEmpty(addressStr) &&
                !TextUtils.isEmpty(regionStr) &&
                !TextUtils.isEmpty(zoneStr) &&
                !TextUtils.isEmpty(corporateStr) &&
                !TextUtils.isEmpty(passwordStr);

        if(allPresent){
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("username", usernameStr);
                jsonObject.put("fullname", fullnameStr);
                jsonObject.put("email", emailStr);
                jsonObject.put("mobile", mobileStr);
                jsonObject.put("address", addressStr);
                jsonObject.put("region", regionStr);
                jsonObject.put("zone", zoneStr);
                jsonObject.put("corporate", corporateStr);
                jsonObject.put("password", passwordStr);

                return jsonObject;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
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
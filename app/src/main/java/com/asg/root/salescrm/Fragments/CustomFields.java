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

public class CustomFields extends BaseFragment implements DataView {

    private EditText user_attached, resp_attached, email_attached, streetaddress_1, streetaddress_2,
            city, state, zipcode, homephone, best_contact_time, other_offers, full_name, reason,
            income, interest, hours, source, variation;
    private Dialog dialog;
    private Button addCustomField;
    private VitalFunctionsPresenter vitalFunctionsPresenter;


    public CustomFields() {
        // Required empty public constructor
    }

    public static CustomFields newInstance(String param1, String param2) {
        return new CustomFields();
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
        View view = inflater.inflate(R.layout.fragment_custom_fields, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        user_attached = view.findViewById(R.id.user_attached);
        resp_attached = view.findViewById(R.id.resp_attached);
        email_attached = view.findViewById(R.id.email_attached);
        streetaddress_1 = view.findViewById(R.id.streetaddress_1);
        streetaddress_2 = view.findViewById(R.id.streetaddress_2);
        city = view.findViewById(R.id.city);
        state = view.findViewById(R.id.state);
        zipcode = view.findViewById(R.id.zipcode);
        homephone = view.findViewById(R.id.homephone);
        best_contact_time = view.findViewById(R.id.best_contact_time);
        other_offers = view.findViewById(R.id.other_offers);
        full_name = view.findViewById(R.id.full_name);
        reason = view.findViewById(R.id.reason);
        income = view.findViewById(R.id.income);
        interest = view.findViewById(R.id.interest);
        hours = view.findViewById(R.id.hours);
        source = view.findViewById(R.id.source);
        variation = view.findViewById(R.id.variation);
        addCustomField = view.findViewById(R.id.addCustomField);
        dialog = new Dialog(Objects.requireNonNull(getContext()));
        loadDialog("Adding Custom Field");

        addCustomField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject = createJSONObject();
                if(jsonObject != null)
                    if(AppUtils.isOnline(Objects.requireNonNull(getContext())))
                        vitalFunctionsPresenter.addCustomFields(jsonObject);
                    else showDialog("Please Check Your Internet", "Dismiss");
                else showDialog("Some Fields are empty", "Dismiss");
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
        dialog.show();
    }

    @Override
    public void hideProgress() {
        dialog.dismiss();
    }

    private JSONObject createJSONObject(){
        String user_attachedStr, resp_attachedStr, email_attachedStr, streetaddress_1Str, streetaddress_2Str,
                cityStr, stateStr, zipcodeStr, homephoneStr, best_contact_timeStr, other_offersStr, full_nameStr, reasonStr,
                incomeStr, interestStr, hoursStr, sourceStr, variationStr;

        user_attachedStr = user_attached.getText() + "";
        resp_attachedStr = resp_attached.getText() + "";
        email_attachedStr = email_attached.getText() + "";
        streetaddress_1Str = streetaddress_1.getText() + "";
        streetaddress_2Str = streetaddress_2.getText() + "";
        cityStr = city.getText() + "";
        stateStr = state.getText() + "";
        zipcodeStr = zipcode.getText() + "";
        homephoneStr = homephone.getText() + "";
        best_contact_timeStr = best_contact_time.getText() + "";
        other_offersStr = other_offers.getText() + "";
        full_nameStr = full_name.getText() + "";
        reasonStr = reason.getText() + "";
        incomeStr = income.getText() + "";
        interestStr = interest.getText() + "";
        hoursStr = hours.getText() + "";
        sourceStr = source.getText() + "";
        variationStr = variation.getText() + "";

        boolean allPresent = !TextUtils.isEmpty(user_attachedStr) &&
                !TextUtils.isEmpty(resp_attachedStr) &&
                !TextUtils.isEmpty(email_attachedStr) &&
                !TextUtils.isEmpty(cityStr) &&
                !TextUtils.isEmpty(other_offersStr) &&
                !TextUtils.isEmpty(full_nameStr);

        if(allPresent){
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("user_attached", user_attachedStr);
                jsonObject.put("resp_attached", resp_attachedStr);
                jsonObject.put("email_attached", email_attachedStr);
                jsonObject.put("streetaddress_1", streetaddress_1Str);
                jsonObject.put("streetaddress_2", streetaddress_2Str);
                jsonObject.put("city", cityStr);
                jsonObject.put("state", stateStr);
                jsonObject.put("zipcode", zipcodeStr);
                jsonObject.put("homephone", homephoneStr);
                jsonObject.put("best_contact_time", best_contact_timeStr);
                jsonObject.put("other_offers", other_offersStr);
                jsonObject.put("full_name", full_nameStr);
                jsonObject.put("reason", reasonStr);
                jsonObject.put("income", incomeStr);
                jsonObject.put("interest", interestStr);
                jsonObject.put("hours", hoursStr);
                jsonObject.put("source", sourceStr);
                jsonObject.put("variation", variationStr);

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
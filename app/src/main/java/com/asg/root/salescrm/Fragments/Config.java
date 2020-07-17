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

public class Config extends BaseFragment implements DataView {

    private EditText edtMaxSendCount;
    private EditText edtLastActivityTrim;
    private EditText edtRandomStr1;
    private EditText edtRandomStr2;
    private EditText edtRandomTimeStamp;
    private EditText edtAdminUser;
    private EditText edtAdminPass;
    private EditText edtCharSet;
    private EditText edtAutoCallMail;
    private EditText edtAddSubSize;
    private EditText edtSubPerPage;
    private EditText edtSiteCode;
    private EditText edtCheckMail;
    private EditText edtCheckBounces;
    private EditText edttinyMce;
    private EditText edtDailyLimit;
    private EditText edtDailyCount;
    private EditText edtDailyReset;
    private Dialog dialog;

    private Button addConfig;

    private VitalFunctionsPresenter vitalFunctionsPresenter;


    public Config() {
        // Required empty public constructor
    }

    public static Config newInstance(String param1, String param2) {
        return new Config();
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
        View view = inflater.inflate(R.layout.fragment_config, container, false);
        edtMaxSendCount = view.findViewById(R.id.max_send_count);
        edtLastActivityTrim = view.findViewById(R.id.last_activity_trim);
        edtRandomStr1 = view.findViewById(R.id.random_str_1);
        edtRandomStr2 = view.findViewById(R.id.random_str_2);
        edtRandomTimeStamp = view.findViewById(R.id.randomTimestamp);
        edtAdminUser = view.findViewById(R.id.admin_user);
        edtAdminPass = view.findViewById(R.id.admin_pass);
        edtCharSet = view.findViewById(R.id.charset);
        edtAutoCallMail = view.findViewById(R.id.autocall_sendmails);
        edtAddSubSize = view.findViewById(R.id.add_sub_size);
        edtSubPerPage = view.findViewById(R.id.subs_per_page);
        edtSiteCode = view.findViewById(R.id.sitecode);
        edtCheckMail = view.findViewById(R.id.check_mail);
        edtCheckBounces = view.findViewById(R.id.check_bounces);
        edttinyMce = view.findViewById(R.id.tinyMCE);
        edtDailyLimit = view.findViewById(R.id.daily_limit);
        edtDailyCount = view.findViewById(R.id.daily_count);
        edtDailyReset = view.findViewById(R.id.daily_reset);
        addConfig = view.findViewById(R.id.addConfig);
        dialog = new Dialog(Objects.requireNonNull(getContext()));
        loadDialog("Adding Config");
        addConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject = createJSONObject();
                if(jsonObject != null)
                    if(AppUtils.isOnline(Objects.requireNonNull(getContext())))
                        vitalFunctionsPresenter.addConfig(jsonObject);
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
        String maxSendCountStr, lastActivityTrimStr, randomStr1Str, randomStr2Str,
                randomTimeStampStr, adminUserStr, adminPasswordStr,
                charSetStr, autoCallSendMailStr,addSubSizerStr, subPerPageStr,siteCodeStr,
                checkMailStr,checkBouncesStr,tinyMCEStr,dailyLimitStr,dailyCountStr,dailyResetStr;
        JSONObject jsonObject = new JSONObject();
        maxSendCountStr = edtMaxSendCount.getText() + "";
        lastActivityTrimStr = edtLastActivityTrim.getText() + "";
        randomStr1Str = edtRandomStr1.getText() + "";
        randomStr2Str = edtRandomStr2.getText() + "";
        randomTimeStampStr = edtRandomTimeStamp.getText() + "";
        adminUserStr = edtAdminUser.getText() + "";
        adminPasswordStr = edtAdminPass.getText() + "";
        charSetStr = edtCharSet.getText() + "";
        autoCallSendMailStr = edtAutoCallMail.getText() + "";
        addSubSizerStr = edtAddSubSize.getText() + "";
        subPerPageStr = edtSubPerPage.getText() + "";
        siteCodeStr = edtSiteCode.getText() + "";
        checkMailStr = edtCheckMail.getText() + "";
        checkBouncesStr = edtCheckBounces.getText() + "";
        tinyMCEStr = edttinyMce.getText() + "";
        dailyLimitStr = edtDailyLimit.getText() + "";
        dailyCountStr = edtDailyCount.getText() + "";
        dailyResetStr = edtDailyReset.getText() + "";
        boolean allPresent = !TextUtils.isEmpty(maxSendCountStr) &&
                !TextUtils.isEmpty(lastActivityTrimStr) &&
                !TextUtils.isEmpty(randomStr1Str) &&
                !TextUtils.isEmpty(randomStr2Str) &&
                !TextUtils.isEmpty(randomTimeStampStr) &&
                !TextUtils.isEmpty(adminUserStr) &&
                !TextUtils.isEmpty(adminPasswordStr) &&
                !TextUtils.isEmpty(charSetStr) &&
                !TextUtils.isEmpty(autoCallSendMailStr) &&
                !TextUtils.isEmpty(addSubSizerStr) &&
                !TextUtils.isEmpty(subPerPageStr) &&
                !TextUtils.isEmpty(siteCodeStr) &&
                !TextUtils.isEmpty(checkMailStr) &&
                !TextUtils.isEmpty(checkBouncesStr) &&
                !TextUtils.isEmpty(tinyMCEStr) &&
                !TextUtils.isEmpty(dailyLimitStr) &&
                !TextUtils.isEmpty(dailyCountStr) &&
                !TextUtils.isEmpty(dailyResetStr);

        if(allPresent){
            try {
                Log.v("IN", "HERE");
                jsonObject.put("max_send_count", maxSendCountStr);
                jsonObject.put("last_activity_trim", lastActivityTrimStr);
                jsonObject.put("random_str_1", randomStr1Str);
                jsonObject.put("random_str_2", randomStr2Str);
                jsonObject.put("random_timestamp", randomTimeStampStr);
                jsonObject.put("admin_user", adminUserStr);
                jsonObject.put("admin_pass", adminPasswordStr);
                jsonObject.put("charset", charSetStr);
                jsonObject.put("autocall_sendmails", autoCallSendMailStr);
                jsonObject.put("add_sub_size",addSubSizerStr);
                jsonObject.put("subs_per_page", subPerPageStr);
                jsonObject.put("site_code", siteCodeStr);
                jsonObject.put("check_mail", checkMailStr);
                jsonObject.put("check_bounces", checkBouncesStr);
                jsonObject.put("tinyMCE", tinyMCEStr);
                jsonObject.put("daily_limit", dailyLimitStr);
                jsonObject.put("daily_count", dailyCountStr);
                jsonObject.put("daily_reset", dailyResetStr);
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
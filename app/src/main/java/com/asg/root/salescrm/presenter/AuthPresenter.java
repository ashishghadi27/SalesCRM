package com.asg.root.salescrm.presenter;

import com.asg.root.salescrm.Utils.Constants;
import com.asg.root.salescrm.Views.AuthView;
import com.asg.root.salescrm.network.AppNetworkResponse;
import com.asg.root.salescrm.network.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AuthPresenter implements AppNetworkResponse {

    Volley volley;
    AuthView view;

    public AuthPresenter(AuthView view){
        this.view=view;
        this.volley= Volley.getInstance();
    }

    public void checkUser(String email, String pass){
        view.showProgress();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);
            jsonObject.put("password", pass);
            volley.post(Constants.apiLogin, jsonObject, this,Constants.ADD_Login_REQUEST_CODE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResSuccess(JSONObject jsonObject, int reqCode) {
        switch (reqCode){
            case Constants.ADD_Login_REQUEST_CODE:
                view.hideProgress();
                view.onAuthSuccess(jsonObject);
                break;
        }
    }

    @Override
    public void onResFailure(String errCode, String errMsg, int reqCode, JSONObject jsonObject) {
        switch (reqCode){
            case Constants.ADD_Login_REQUEST_CODE:
                view.hideProgress();
                view.onAuthFailure(errMsg);
                break;
        }
    }
}

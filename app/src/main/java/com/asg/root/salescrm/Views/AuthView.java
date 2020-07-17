package com.asg.root.salescrm.Views;

import org.json.JSONObject;

public interface AuthView {

    void onAuthSuccess(JSONObject jsonObject);
    void onAuthFailure(String err);
    void showProgress();
    void hideProgress();
}
